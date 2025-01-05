-- Create composite type for validation results
CREATE TYPE validation_result AS (
    is_valid BOOLEAN,
    error_message TEXT
    );

-- Function to validate card status
CREATE OR REPLACE FUNCTION validate_card_status(p_card_id BIGINT)
RETURNS validation_result AS $$
DECLARE
v_card_status CARD_STATUS;
    v_expiration_date TIMESTAMP;
    result validation_result;
BEGIN
SELECT card_status, expiration_date
INTO v_card_status, v_expiration_date
FROM card
WHERE card_id = p_card_id;

-- Check card expiration
IF v_expiration_date < CURRENT_TIMESTAMP THEN
        result := (FALSE, 'Card is expired')::validation_result;
RETURN result;
END IF;

    -- Check card status
    IF v_card_status IN ('BLOCKED', 'EXPIRED', 'CANCELLED') THEN
        result := (FALSE, 'Card is ' || LOWER(v_card_status::TEXT))::validation_result;
RETURN result;
END IF;

    result := (TRUE, NULL)::validation_result;
RETURN result;
END;
$$ LANGUAGE plpgsql;

-- Function to validate transaction limits
CREATE OR REPLACE FUNCTION validate_transaction_limits(
    p_card_id BIGINT,
    p_transaction_amount DECIMAL,
    p_transaction_type TRANSACTION_TYPE,
    p_transaction_currency CHAR(3)
)
RETURNS validation_result AS $$
DECLARE
v_daily_usage DECIMAL;
    v_monthly_usage DECIMAL;
    v_daily_limit DECIMAL;
    v_monthly_limit DECIMAL;
    v_transaction_count INTEGER;
    v_max_daily_transactions INTEGER;
    v_limit_type LIMIT_TYPE;
    result validation_result;
BEGIN
    -- Set limit type based on transaction type
    v_limit_type := CASE
        WHEN p_transaction_type = 'WITHDRAWAL' THEN 'ATM_WITHDRAWAL'
        ELSE 'DAILY_SPENDING'
END;

    -- Get current daily usage and transaction count
SELECT
    COALESCE(SUM(card_fee_amount), 0),
    COUNT(*)
INTO v_daily_usage, v_transaction_count
FROM card_transaction
WHERE card_id = p_card_id
  AND DATE(card_transaction_timestamp) = CURRENT_DATE
  AND transaction_status = 'COMPLETED';

-- Get current monthly usage
SELECT COALESCE(SUM(card_fee_amount), 0)
INTO v_monthly_usage
FROM card_transaction
WHERE card_id = p_card_id
  AND DATE_TRUNC('month', card_transaction_timestamp) = DATE_TRUNC('month', CURRENT_DATE)
  AND transaction_status = 'COMPLETED';

-- Get limits
SELECT
    cl1.limit_amount,
    cl2.limit_amount,
    COALESCE(cl3.limit_amount, 50)::INTEGER
INTO
    v_daily_limit,
    v_monthly_limit,
    v_max_daily_transactions
FROM card_limit cl1
         LEFT JOIN card_limit cl2 ON cl2.card_id = p_card_id
    AND cl2.limit_type = v_limit_type
    AND cl2.reset_period = 'MONTHLY'
         LEFT JOIN card_limit cl3 ON cl3.card_id = p_card_id
    AND cl3.limit_type = 'DAILY_TRANSACTIONS'
WHERE cl1.card_id = p_card_id
  AND cl1.limit_type = v_limit_type
  AND cl1.reset_period = 'DAILY';

-- Validate transaction count
IF v_transaction_count >= v_max_daily_transactions THEN
        result := (FALSE, 'Maximum daily transaction count exceeded')::validation_result;
RETURN result;
END IF;

    -- Validate daily limit
    IF v_daily_usage + p_transaction_amount > v_daily_limit THEN
        result := (FALSE, 'Transaction exceeds daily limit')::validation_result;
RETURN result;
END IF;

    -- Validate monthly limit
    IF v_monthly_usage + p_transaction_amount > v_monthly_limit THEN
        result := (FALSE, 'Transaction exceeds monthly limit')::validation_result;
RETURN result;
END IF;

    result := (TRUE, NULL)::validation_result;
RETURN result;
END;
$$ LANGUAGE plpgsql;

-- Function to validate merchant category restrictions
CREATE OR REPLACE FUNCTION validate_merchant_restrictions(
    p_card_id BIGINT,
    p_merchant_category_code VARCHAR(4)
)
RETURNS validation_result AS $$
DECLARE
result validation_result;
BEGIN
    -- Check if merchant category is restricted for this card
    IF EXISTS (
        SELECT 1
        FROM card_configuration cc
        WHERE cc.card_id = p_card_id
        AND cc.config_type = 'MERCHANT_CATEGORY_RESTRICTION'
        AND cc.config_value::jsonb ? p_merchant_category_code
    ) THEN
        result := (FALSE, 'Merchant category is restricted')::validation_result;
RETURN result;
END IF;

    result := (TRUE, NULL)::validation_result;
RETURN result;
END;
$$ LANGUAGE plpgsql;

-- Function to validate geographic restrictions
CREATE OR REPLACE FUNCTION validate_geographic_restrictions(
    p_card_id BIGINT,
    p_country_code CHAR(2)
)
RETURNS validation_result AS $$
DECLARE
v_international_enabled BOOLEAN;
    v_allowed_countries JSONB;
    result validation_result;
BEGIN
    -- Check if international transactions are enabled
SELECT config_value INTO v_international_enabled
FROM card_configuration
WHERE card_id = p_card_id
  AND config_type = 'INTERNATIONAL_USAGE_ENABLED';

IF NOT v_international_enabled AND p_country_code != 'US' THEN -- Assuming US is default
        result := (FALSE, 'International transactions are not enabled')::validation_result;
RETURN result;
END IF;

    -- Check country-specific restrictions
SELECT config_value::jsonb INTO v_allowed_countries
FROM card_configuration
WHERE card_id = p_card_id
  AND config_type = 'ALLOWED_COUNTRIES';

IF v_allowed_countries IS NOT NULL AND NOT v_allowed_countries ? p_country_code THEN
        result := (FALSE, 'Transactions not allowed in this country')::validation_result;
RETURN result;
END IF;

    result := (TRUE, NULL)::validation_result;
RETURN result;
END;
$$ LANGUAGE plpgsql;

-- Function to validate card security settings
CREATE OR REPLACE FUNCTION validate_card_security(
    p_card_id BIGINT,
    p_transaction_type TRANSACTION_TYPE,
    p_card_present_flag BOOLEAN,
    p_merchant_category_code VARCHAR(4)
)
RETURNS validation_result AS $$
DECLARE
v_security_status BOOLEAN;
    v_high_risk_merchant BOOLEAN;
    result validation_result;
BEGIN
    -- Check if fraud detection is enabled
SELECT security_status INTO v_security_status
FROM card_security
WHERE card_id = p_card_id
  AND security_feature = 'FRAUD_DETECTION_ENABLED';

IF NOT v_security_status THEN
        result := (FALSE, 'Fraud detection is not enabled')::validation_result;
RETURN result;
END IF;

    -- Additional security checks for non-present transactions
    IF NOT p_card_present_flag THEN
SELECT config_value INTO v_security_status
FROM card_configuration
WHERE card_id = p_card_id
  AND config_type = 'ONLINE_TRANSACTIONS_ENABLED';

IF NOT v_security_status THEN
            result := (FALSE, 'Online transactions are not enabled')::validation_result;
RETURN result;
END IF;
END IF;

    -- Check for high-risk merchant categories
SELECT EXISTS (
    SELECT 1
    FROM config.high_risk_merchants
    WHERE merchant_category_code = p_merchant_category_code
) INTO v_high_risk_merchant;

IF v_high_risk_merchant THEN
        -- Additional validation for high-risk merchants
SELECT security_status INTO v_security_status
FROM card_security
WHERE card_id = p_card_id
  AND security_feature = 'TWO_FACTOR_AUTH';

IF NOT v_security_status THEN
            result := (FALSE, 'Two-factor authentication required for high-risk merchant')::validation_result;
RETURN result;
END IF;
END IF;

    result := (TRUE, NULL)::validation_result;
RETURN result;
END;
$$ LANGUAGE plpgsql;

-- Main transaction validation function
CREATE OR REPLACE FUNCTION validate_card_transaction(
    p_card_id BIGINT,
    p_transaction_amount DECIMAL,
    p_transaction_type TRANSACTION_TYPE,
    p_card_present_flag BOOLEAN,
    p_merchant_category_code VARCHAR(4),
    p_country_code CHAR(2),
    p_transaction_currency CHAR(3)
)
RETURNS validation_result AS $$
DECLARE
v_result validation_result;
BEGIN
    -- Check card status
    v_result := validate_card_status(p_card_id);
    IF NOT v_result.is_valid THEN
        RETURN v_result;
END IF;

    -- Check transaction limits
    v_result := validate_transaction_limits(
        p_card_id,
        p_transaction_amount,
        p_transaction_type,
        p_transaction_currency
    );
    IF NOT v_result.is_valid THEN
        RETURN v_result;
END IF;

    -- Check merchant restrictions
    v_result := validate_merchant_restrictions(p_card_id, p_merchant_category_code);
    IF NOT v_result.is_valid THEN
        RETURN v_result;
END IF;

    -- Check geographic restrictions
    v_result := validate_geographic_restrictions(p_card_id, p_country_code);
    IF NOT v_result.is_valid THEN
        RETURN v_result;
END IF;

    -- Check security settings
    v_result := validate_card_security(
        p_card_id,
        p_transaction_type,
        p_card_present_flag,
        p_merchant_category_code
    );
    IF NOT v_result.is_valid THEN
        RETURN v_result;
END IF;

RETURN (TRUE, NULL)::validation_result;
END;
$$ LANGUAGE plpgsql;

-- Create triggers for different validation scenarios

-- Trigger for transaction validation
CREATE OR REPLACE FUNCTION tr_validate_card_transaction()
RETURNS TRIGGER AS $$
DECLARE
v_result validation_result;
BEGIN
    v_result := validate_card_transaction(
        NEW.card_id,
        NEW.card_fee_amount,
        NEW.transaction_type,
        NEW.card_present_flag,
        NEW.card_merchant_category_code,
        NEW.card_holder_country,
        NEW.card_fee_currency
    );

    IF NOT v_result.is_valid THEN
        RAISE EXCEPTION 'Transaction validation failed: %', v_result.error_message;
END IF;

RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger for card status changes
CREATE OR REPLACE FUNCTION tr_validate_card_status_change()
RETURNS TRIGGER AS $$
BEGIN
    -- Prevent manual expiration
    IF NEW.card_status = 'EXPIRED' AND OLD.card_status != 'EXPIRED' THEN
        RAISE EXCEPTION 'Card status cannot be manually set to EXPIRED';
END IF;

    -- Validate status transitions
    IF OLD.card_status = 'CANCELLED' AND NEW.card_status != 'CANCELLED' THEN
        RAISE EXCEPTION 'Cannot reactivate a cancelled card';
END IF;

    -- Auto-expire cards
    IF NEW.expiration_date < CURRENT_TIMESTAMP AND NEW.card_status != 'EXPIRED' THEN
        NEW.card_status := 'EXPIRED';
END IF;

RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger for limit changes
CREATE OR REPLACE FUNCTION tr_validate_limit_change()
RETURNS TRIGGER AS $$
BEGIN
    -- Validate limit amounts
    IF NEW.limit_amount < 0 THEN
        RAISE EXCEPTION 'Limit amount cannot be negative';
END IF;

    -- Validate limit types
    IF NEW.limit_type = 'DAILY_SPENDING' AND
       NEW.limit_amount > (
           SELECT limit_amount
           FROM card_limit
           WHERE card_id = NEW.card_id
           AND limit_type = 'MONTHLY_SPENDING'
       ) THEN
        RAISE EXCEPTION 'Daily limit cannot exceed monthly limit';
END IF;

RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Create triggers
CREATE TRIGGER trg_validate_card_transaction
    BEFORE INSERT OR UPDATE ON card_transaction
                         FOR EACH ROW
                         EXECUTE FUNCTION tr_validate_card_transaction();

CREATE TRIGGER trg_validate_card_status_change
    BEFORE UPDATE ON card
    FOR EACH ROW
    EXECUTE FUNCTION tr_validate_card_status_change();

CREATE TRIGGER trg_validate_limit_change
    BEFORE INSERT OR UPDATE ON card_limit
                         FOR EACH ROW
                         EXECUTE FUNCTION tr_validate_limit_change();

-- Create indexes to support validations
CREATE INDEX idx_card_transaction_daily ON card_transaction (
                                                             card_id,
                                                             DATE(card_transaction_timestamp),
                                                             transaction_status
    );

CREATE INDEX idx_card_transaction_monthly ON card_transaction (
                                                               card_id,
                                                               DATE_TRUNC('month', card_transaction_timestamp),
                                                               transaction_status
    );
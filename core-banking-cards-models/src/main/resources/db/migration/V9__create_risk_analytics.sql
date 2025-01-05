-- V9__create_risk_analytics.sql
-- Drop existing objects if they exist
DROP MATERIALIZED VIEW IF EXISTS card_mv_enhanced_risk_scoring;
DROP FUNCTION IF EXISTS calculate_velocity_score(BIGINT, TIMESTAMP);
DROP FUNCTION IF EXISTS calculate_merchant_risk_score(VARCHAR);
DROP FUNCTION IF EXISTS trg_refresh_risk_analytics();

-- Helper Functions for Risk Analysis
CREATE OR REPLACE FUNCTION calculate_velocity_score(
    p_card_id BIGINT,
    p_current_timestamp TIMESTAMP
)
RETURNS TABLE (
    tx_per_hour INTEGER,
    amount_per_hour DECIMAL,
    countries_per_hour INTEGER,
    merchants_per_hour INTEGER
) AS $$
BEGIN
RETURN QUERY
SELECT
    COUNT(*)::INTEGER as tx_per_hour,
        COALESCE(SUM(card_fee_amount), 0) as amount_per_hour,
    COUNT(DISTINCT card_holder_country)::INTEGER as countries_per_hour,
        COUNT(DISTINCT card_merchant_category_code)::INTEGER as merchants_per_hour
FROM card_transaction
WHERE card_id = p_card_id
  AND card_transaction_timestamp >= p_current_timestamp - INTERVAL '1 hour';
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION calculate_merchant_risk_score(
    p_merchant_category_code VARCHAR(4)
)
RETURNS DECIMAL AS $$
DECLARE
v_fraud_rate DECIMAL;
BEGIN
SELECT
    COALESCE(
            COUNT(CASE WHEN card_fraud_flag THEN 1 END)::DECIMAL /
                                                       NULLIF(COUNT(*), 0) * 100,
            0
    )
INTO v_fraud_rate
FROM card_transaction
WHERE card_merchant_category_code = p_merchant_category_code
  AND card_transaction_timestamp >= CURRENT_TIMESTAMP - INTERVAL '90 days';

RETURN v_fraud_rate;
END;
$$ LANGUAGE plpgsql;

-- Enhanced Risk Scoring View
CREATE MATERIALIZED VIEW card_mv_enhanced_risk_scoring AS
WITH transaction_patterns AS (
    SELECT
        ct.card_id,
        c.card_type,
        DATE_TRUNC('hour', ct.card_transaction_timestamp) as hour_bucket,
        COUNT(*) as tx_count,
        SUM(ct.card_fee_amount) as total_amount,
        COUNT(DISTINCT ct.card_holder_country) as unique_countries,
        COUNT(DISTINCT ct.card_merchant_category_code) as unique_merchants,
        COUNT(CASE WHEN ct.card_fraud_flag THEN 1 END) as fraud_count,
        COUNT(CASE WHEN ct.transaction_status = 'FAILED' THEN 1 END) as failed_count,
        COUNT(CASE WHEN NOT ct.card_present_flag THEN 1 END) as card_not_present_count,
        AVG(ct.card_fee_amount) as avg_amount,
        STDDEV(ct.card_fee_amount) as stddev_amount,
        MAX(ct.card_fee_amount) as max_amount,
        vel.tx_per_hour,
        vel.amount_per_hour,
        vel.countries_per_hour,
        vel.merchants_per_hour
    FROM card_transaction ct
    JOIN card c ON c.card_id = ct.card_id
    CROSS JOIN LATERAL calculate_velocity_score(ct.card_id, ct.card_transaction_timestamp) vel
    WHERE ct.card_transaction_timestamp >= CURRENT_TIMESTAMP - INTERVAL '24 hours'
    GROUP BY
        ct.card_id,
        c.card_type,
        DATE_TRUNC('hour', ct.card_transaction_timestamp),
        vel.tx_per_hour,
        vel.amount_per_hour,
        vel.countries_per_hour,
        vel.merchants_per_hour
)
SELECT
    card_id,
    card_type,
    hour_bucket,
    -- Base Risk Factors
    LEAST(tx_count * 10, 100) as transaction_volume_risk,
    LEAST((total_amount / 1000), 100) as transaction_amount_risk,
    LEAST(unique_countries * 20, 100) as geographical_risk,
    LEAST(card_not_present_count * 15, 100) as card_not_present_risk,

    -- Velocity Risk Scores
    LEAST(tx_per_hour * 5, 100) as transaction_velocity_risk,
    LEAST((amount_per_hour / 1000), 100) as amount_velocity_risk,
    LEAST(countries_per_hour * 25, 100) as country_velocity_risk,
    LEAST(merchants_per_hour * 10, 100) as merchant_velocity_risk,

    -- Composite Risk Score
    (
        -- Transaction Pattern Risks (30%)
        (LEAST(tx_count * 10, 100) * 0.10) +
        (LEAST((total_amount / 1000), 100) * 0.10) +
        (LEAST(unique_countries * 20, 100) * 0.05) +
        (LEAST(card_not_present_count * 15, 100) * 0.05) +

            -- Velocity Risks (30%)
        (LEAST(tx_per_hour * 5, 100) * 0.10) +
        (LEAST((amount_per_hour / 1000), 100) * 0.10) +
        (LEAST(countries_per_hour * 25, 100) * 0.05) +
        (LEAST(merchants_per_hour * 10, 100) * 0.05)
        ) as composite_risk_score,

    -- Risk Level Classification
    CASE
        WHEN ((LEAST(tx_count * 10, 100) * 0.10) +
              (LEAST((total_amount / 1000), 100) * 0.10) +
              (LEAST(unique_countries * 20, 100) * 0.05) +
              (LEAST(card_not_present_count * 15, 100) * 0.05) +
              (LEAST(tx_per_hour * 5, 100) * 0.10) +
              (LEAST((amount_per_hour / 1000), 100) * 0.10) +
              (LEAST(countries_per_hour * 25, 100) * 0.05) +
              (LEAST(merchants_per_hour * 10, 100) * 0.05)) >= 80 THEN 'CRITICAL'
        WHEN ((LEAST(tx_count * 10, 100) * 0.10) +
              (LEAST((total_amount / 1000), 100) * 0.10) +
              (LEAST(unique_countries * 20, 100) * 0.05) +
              (LEAST(card_not_present_count * 15, 100) * 0.05) +
              (LEAST(tx_per_hour * 5, 100) * 0.10) +
              (LEAST((amount_per_hour / 1000), 100) * 0.10) +
              (LEAST(countries_per_hour * 25, 100) * 0.05) +
              (LEAST(merchants_per_hour * 10, 100) * 0.05)) >= 60 THEN 'HIGH'
        WHEN ((LEAST(tx_count * 10, 100) * 0.10) +
              (LEAST((total_amount / 1000), 100) * 0.10) +
              (LEAST(unique_countries * 20, 100) * 0.05) +
              (LEAST(card_not_present_count * 15, 100) * 0.05) +
              (LEAST(tx_per_hour * 5, 100) * 0.10) +
              (LEAST((amount_per_hour / 1000), 100) * 0.10) +
              (LEAST(countries_per_hour * 25, 100) * 0.05) +
              (LEAST(merchants_per_hour * 10, 100) * 0.05)) >= 40 THEN 'MEDIUM'
        WHEN ((LEAST(tx_count * 10, 100) * 0.10) +
              (LEAST((total_amount / 1000), 100) * 0.10) +
              (LEAST(unique_countries * 20, 100) * 0.05) +
              (LEAST(card_not_present_count * 15, 100) * 0.05) +
              (LEAST(tx_per_hour * 5, 100) * 0.10) +
              (LEAST((amount_per_hour / 1000), 100) * 0.10) +
              (LEAST(countries_per_hour * 25, 100) * 0.05) +
              (LEAST(merchants_per_hour * 10, 100) * 0.05)) >= 20 THEN 'LOW'
        ELSE 'MINIMAL'
        END as risk_level
FROM transaction_patterns
    WITH DATA;

CREATE UNIQUE INDEX idx_card_mv_enhanced_risk_scoring_card_hour
    ON card_mv_enhanced_risk_scoring(card_id, hour_bucket);

CREATE INDEX idx_card_mv_enhanced_risk_scoring_risk_level
    ON card_mv_enhanced_risk_scoring(risk_level, composite_risk_score);

-- Function to refresh risk analytics
CREATE OR REPLACE FUNCTION refresh_risk_analytics()
RETURNS void AS $$
BEGIN
    REFRESH MATERIALIZED VIEW CONCURRENTLY card_mv_enhanced_risk_scoring;
END;
$$ LANGUAGE plpgsql;

-- Create trigger function
CREATE OR REPLACE FUNCTION trg_refresh_risk_analytics()
RETURNS TRIGGER AS $$
BEGIN
    PERFORM refresh_risk_analytics();
RETURN NULL;
END;
$$ LANGUAGE plpgsql;

-- Drop existing trigger if exists
DROP TRIGGER IF EXISTS trg_refresh_risk_analytics ON card_transaction;

-- Create trigger for risk analytics refresh
CREATE TRIGGER trg_refresh_risk_analytics
    AFTER INSERT OR UPDATE OR DELETE ON card_transaction
    FOR EACH STATEMENT
    WHEN (pg_trigger_depth() = 0)
    EXECUTE FUNCTION trg_refresh_risk_analytics();
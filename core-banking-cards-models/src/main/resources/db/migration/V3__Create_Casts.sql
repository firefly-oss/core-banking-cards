-- Create casts for converting between varchar and enum types

-- CardStatusEnum casts
CREATE OR REPLACE FUNCTION varchar_to_card_status_enum(varchar) RETURNS card_status_enum AS $$
BEGIN
    RETURN $1::card_status_enum;
EXCEPTION WHEN others THEN
    RETURN NULL;
END;
$$ LANGUAGE plpgsql IMMUTABLE;

CREATE OR REPLACE FUNCTION card_status_enum_to_varchar(card_status_enum) RETURNS varchar AS $$
BEGIN
    RETURN $1::varchar;
END;
$$ LANGUAGE plpgsql IMMUTABLE;

CREATE CAST (varchar AS card_status_enum) WITH FUNCTION varchar_to_card_status_enum(varchar) AS IMPLICIT;
CREATE CAST (card_status_enum AS varchar) WITH FUNCTION card_status_enum_to_varchar(card_status_enum) AS IMPLICIT;

-- CardTypeEnum casts
CREATE OR REPLACE FUNCTION varchar_to_card_type_enum(varchar) RETURNS card_type_enum AS $$
BEGIN
    RETURN $1::card_type_enum;
EXCEPTION WHEN others THEN
    RETURN NULL;
END;
$$ LANGUAGE plpgsql IMMUTABLE;

CREATE OR REPLACE FUNCTION card_type_enum_to_varchar(card_type_enum) RETURNS varchar AS $$
BEGIN
    RETURN $1::varchar;
END;
$$ LANGUAGE plpgsql IMMUTABLE;

CREATE CAST (varchar AS card_type_enum) WITH FUNCTION varchar_to_card_type_enum(varchar) AS IMPLICIT;
CREATE CAST (card_type_enum AS varchar) WITH FUNCTION card_type_enum_to_varchar(card_type_enum) AS IMPLICIT;

-- ConfigTypeEnum casts
CREATE OR REPLACE FUNCTION varchar_to_config_type_enum(varchar) RETURNS config_type_enum AS $$
BEGIN
    RETURN $1::config_type_enum;
EXCEPTION WHEN others THEN
    RETURN NULL;
END;
$$ LANGUAGE plpgsql IMMUTABLE;

CREATE OR REPLACE FUNCTION config_type_enum_to_varchar(config_type_enum) RETURNS varchar AS $$
BEGIN
    RETURN $1::varchar;
END;
$$ LANGUAGE plpgsql IMMUTABLE;

CREATE CAST (varchar AS config_type_enum) WITH FUNCTION varchar_to_config_type_enum(varchar) AS IMPLICIT;
CREATE CAST (config_type_enum AS varchar) WITH FUNCTION config_type_enum_to_varchar(config_type_enum) AS IMPLICIT;

-- LimitTypeEnum casts
CREATE OR REPLACE FUNCTION varchar_to_limit_type_enum(varchar) RETURNS limit_type_enum AS $$
BEGIN
    RETURN $1::limit_type_enum;
EXCEPTION WHEN others THEN
    RETURN NULL;
END;
$$ LANGUAGE plpgsql IMMUTABLE;

CREATE OR REPLACE FUNCTION limit_type_enum_to_varchar(limit_type_enum) RETURNS varchar AS $$
BEGIN
    RETURN $1::varchar;
END;
$$ LANGUAGE plpgsql IMMUTABLE;

CREATE CAST (varchar AS limit_type_enum) WITH FUNCTION varchar_to_limit_type_enum(varchar) AS IMPLICIT;
CREATE CAST (limit_type_enum AS varchar) WITH FUNCTION limit_type_enum_to_varchar(limit_type_enum) AS IMPLICIT;

-- ResetPeriodEnum casts
CREATE OR REPLACE FUNCTION varchar_to_reset_period_enum(varchar) RETURNS reset_period_enum AS $$
BEGIN
    RETURN $1::reset_period_enum;
EXCEPTION WHEN others THEN
    RETURN NULL;
END;
$$ LANGUAGE plpgsql IMMUTABLE;

CREATE OR REPLACE FUNCTION reset_period_enum_to_varchar(reset_period_enum) RETURNS varchar AS $$
BEGIN
    RETURN $1::varchar;
END;
$$ LANGUAGE plpgsql IMMUTABLE;

CREATE CAST (varchar AS reset_period_enum) WITH FUNCTION varchar_to_reset_period_enum(varchar) AS IMPLICIT;
CREATE CAST (reset_period_enum AS varchar) WITH FUNCTION reset_period_enum_to_varchar(reset_period_enum) AS IMPLICIT;

-- ProviderStatusEnum casts
CREATE OR REPLACE FUNCTION varchar_to_provider_status_enum(varchar) RETURNS provider_status_enum AS $$
BEGIN
    RETURN $1::provider_status_enum;
EXCEPTION WHEN others THEN
    RETURN NULL;
END;
$$ LANGUAGE plpgsql IMMUTABLE;

CREATE OR REPLACE FUNCTION provider_status_enum_to_varchar(provider_status_enum) RETURNS varchar AS $$
BEGIN
    RETURN $1::varchar;
END;
$$ LANGUAGE plpgsql IMMUTABLE;

CREATE CAST (varchar AS provider_status_enum) WITH FUNCTION varchar_to_provider_status_enum(varchar) AS IMPLICIT;
CREATE CAST (provider_status_enum AS varchar) WITH FUNCTION provider_status_enum_to_varchar(provider_status_enum) AS IMPLICIT;

-- SecurityFeatureEnum casts
CREATE OR REPLACE FUNCTION varchar_to_security_feature_enum(varchar) RETURNS security_feature_enum AS $$
BEGIN
    RETURN $1::security_feature_enum;
EXCEPTION WHEN others THEN
    RETURN NULL;
END;
$$ LANGUAGE plpgsql IMMUTABLE;

CREATE OR REPLACE FUNCTION security_feature_enum_to_varchar(security_feature_enum) RETURNS varchar AS $$
BEGIN
    RETURN $1::varchar;
END;
$$ LANGUAGE plpgsql IMMUTABLE;

CREATE CAST (varchar AS security_feature_enum) WITH FUNCTION varchar_to_security_feature_enum(varchar) AS IMPLICIT;
CREATE CAST (security_feature_enum AS varchar) WITH FUNCTION security_feature_enum_to_varchar(security_feature_enum) AS IMPLICIT;

-- TransactionStatusEnum casts
CREATE OR REPLACE FUNCTION varchar_to_transaction_status_enum(varchar) RETURNS transaction_status_enum AS $$
BEGIN
    RETURN $1::transaction_status_enum;
EXCEPTION WHEN others THEN
    RETURN NULL;
END;
$$ LANGUAGE plpgsql IMMUTABLE;

CREATE OR REPLACE FUNCTION transaction_status_enum_to_varchar(transaction_status_enum) RETURNS varchar AS $$
BEGIN
    RETURN $1::varchar;
END;
$$ LANGUAGE plpgsql IMMUTABLE;

CREATE CAST (varchar AS transaction_status_enum) WITH FUNCTION varchar_to_transaction_status_enum(varchar) AS IMPLICIT;
CREATE CAST (transaction_status_enum AS varchar) WITH FUNCTION transaction_status_enum_to_varchar(transaction_status_enum) AS IMPLICIT;

-- TransactionTypeEnum casts
CREATE OR REPLACE FUNCTION varchar_to_transaction_type_enum(varchar) RETURNS transaction_type_enum AS $$
BEGIN
    RETURN $1::transaction_type_enum;
EXCEPTION WHEN others THEN
    RETURN NULL;
END;
$$ LANGUAGE plpgsql IMMUTABLE;

CREATE OR REPLACE FUNCTION transaction_type_enum_to_varchar(transaction_type_enum) RETURNS varchar AS $$
BEGIN
    RETURN $1::varchar;
END;
$$ LANGUAGE plpgsql IMMUTABLE;

CREATE CAST (varchar AS transaction_type_enum) WITH FUNCTION varchar_to_transaction_type_enum(varchar) AS IMPLICIT;
CREATE CAST (transaction_type_enum AS varchar) WITH FUNCTION transaction_type_enum_to_varchar(transaction_type_enum) AS IMPLICIT;

-- VirtualCardStatusEnum casts
CREATE OR REPLACE FUNCTION varchar_to_virtual_card_status_enum(varchar) RETURNS virtual_card_status_enum AS $$
BEGIN
    RETURN $1::virtual_card_status_enum;
EXCEPTION WHEN others THEN
    RETURN NULL;
END;
$$ LANGUAGE plpgsql IMMUTABLE;

CREATE OR REPLACE FUNCTION virtual_card_status_enum_to_varchar(virtual_card_status_enum) RETURNS varchar AS $$
BEGIN
    RETURN $1::varchar;
END;
$$ LANGUAGE plpgsql IMMUTABLE;

CREATE CAST (varchar AS virtual_card_status_enum) WITH FUNCTION varchar_to_virtual_card_status_enum(varchar) AS IMPLICIT;
CREATE CAST (virtual_card_status_enum AS varchar) WITH FUNCTION virtual_card_status_enum_to_varchar(virtual_card_status_enum) AS IMPLICIT;
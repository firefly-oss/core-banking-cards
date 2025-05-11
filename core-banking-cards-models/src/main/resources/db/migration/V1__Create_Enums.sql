-- Create enums for the database

-- CardStatusEnum
CREATE TYPE card_status_enum AS ENUM (
    'ACTIVE',
    'BLOCKED',
    'EXPIRED',
    'CANCELLED'
);

-- CardTypeEnum
CREATE TYPE card_type_enum AS ENUM (
    'DEBIT',
    'CREDIT',
    'PREPAID'
);

-- ConfigTypeEnum
CREATE TYPE config_type_enum AS ENUM (
    'CONTACTLESS_ENABLED',
    'CHIP_ENABLED',
    'ONLINE_TRANSACTIONS_ENABLED',
    'INTERNATIONAL_USAGE_ENABLED'
);

-- LimitTypeEnum
CREATE TYPE limit_type_enum AS ENUM (
    'DAILY_SPENDING',
    'MONTHLY_SPENDING',
    'ATM_WITHDRAWAL',
    'ONLINE_PURCHASE'
);

-- ResetPeriodEnum
CREATE TYPE reset_period_enum AS ENUM (
    'DAILY',
    'MONTHLY'
);

-- ProviderStatusEnum
CREATE TYPE provider_status_enum AS ENUM (
    'ACTIVE',
    'INACTIVE',
    'PENDING',
    'SUSPENDED'
);

-- SecurityFeatureEnum
CREATE TYPE security_feature_enum AS ENUM (
    'PIN_ENABLED',
    'TWO_FACTOR_AUTH',
    'FRAUD_DETECTION_ENABLED'
);

-- TransactionStatusEnum
CREATE TYPE transaction_status_enum AS ENUM (
    'PENDING',
    'COMPLETED',
    'FAILED',
    'REVERSED'
);

-- TransactionTypeEnum
CREATE TYPE transaction_type_enum AS ENUM (
    'PURCHASE',
    'WITHDRAWAL',
    'PAYMENT',
    'FEE',
    'REFUND'
);

-- VirtualCardStatusEnum
CREATE TYPE virtual_card_status_enum AS ENUM (
    'ACTIVE',
    'SUSPENDED',
    'CANCELLED'
);
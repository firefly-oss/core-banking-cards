-- V1: Create all enum types

-- Card Status enum
CREATE TYPE card_status_enum AS ENUM (
    'PENDING',
    'ACTIVE',
    'INACTIVE',
    'BLOCKED',
    'SUSPENDED',
    'EXPIRED',
    'CLOSED',
    'LOST',
    'STOLEN',
    'REPLACED'
);

-- Payment Status enum
CREATE TYPE payment_status_enum AS ENUM (
    'PENDING',
    'PROCESSING',
    'COMPLETED',
    'FAILED',
    'CANCELLED',
    'REFUNDED',
    'REVERSED',
    'REJECTED',
    'EXPIRED'
);

-- Dispute Status enum
CREATE TYPE dispute_status_enum AS ENUM (
    'INITIATED',
    'UNDER_REVIEW',
    'PENDING_EVIDENCE',
    'PENDING_RESPONSE',
    'RESOLVED_IN_FAVOR_OF_CARDHOLDER',
    'RESOLVED_IN_FAVOR_OF_MERCHANT',
    'CLOSED',
    'CANCELLED',
    'EXPIRED'
);

-- Dispute Stage enum
CREATE TYPE dispute_stage_enum AS ENUM (
    'FILING',
    'REVIEW',
    'EVIDENCE_COLLECTION',
    'MERCHANT_RESPONSE',
    'DECISION',
    'APPEAL',
    'CLOSED'
);

-- Terminal Status enum
CREATE TYPE terminal_status_enum AS ENUM (
    'ACTIVE',
    'INACTIVE',
    'MAINTENANCE',
    'FAULTY',
    'DECOMMISSIONED',
    'PENDING_ACTIVATION',
    'SUSPENDED'
);

-- Terminal Type enum
CREATE TYPE terminal_type_enum AS ENUM (
    'POS',
    'ATM',
    'KIOSK',
    'MOBILE',
    'VIRTUAL',
    'INTEGRATED',
    'STANDALONE'
);

-- Merchant Status enum
CREATE TYPE merchant_status_enum AS ENUM (
    'ACTIVE',
    'INACTIVE',
    'PENDING_APPROVAL',
    'SUSPENDED',
    'BLACKLISTED',
    'UNDER_REVIEW',
    'TERMINATED'
);

-- Merchant Type enum
CREATE TYPE merchant_type_enum AS ENUM (
    'RETAIL',
    'ONLINE',
    'RESTAURANT',
    'TRAVEL',
    'ENTERTAINMENT',
    'HEALTHCARE',
    'EDUCATION',
    'GOVERNMENT',
    'UTILITY',
    'FINANCIAL',
    'OTHER'
);

-- Activity Type enum
CREATE TYPE activity_type_enum AS ENUM (
    'CARD_ACTIVATION',
    'PIN_CHANGE',
    'LIMIT_CHANGE',
    'STATUS_CHANGE',
    'PERSONAL_INFO_UPDATE',
    'SECURITY_SETTING_CHANGE',
    'LOGIN',
    'LOGOUT',
    'FAILED_LOGIN',
    'PASSWORD_RESET',
    'TRANSACTION',
    'PAYMENT',
    'DISPUTE_FILING',
    'STATEMENT_VIEW',
    'CARD_BLOCK',
    'CARD_UNBLOCK',
    'CARD_REPLACEMENT',
    'CARD_RENEWAL',
    'CARD_CLOSURE'
);

-- Activity Status enum
CREATE TYPE activity_status_enum AS ENUM (
    'SUCCESSFUL',
    'FAILED',
    'PENDING',
    'IN_PROGRESS',
    'CANCELLED',
    'EXPIRED',
    'REJECTED'
);

-- Manufacturing Status enum
CREATE TYPE manufacturing_status_enum AS ENUM (
    'ORDERED',
    'IN_PRODUCTION',
    'PRODUCED',
    'QUALITY_CHECK',
    'READY_FOR_SHIPPING',
    'SHIPPED',
    'DELIVERED',
    'FAILED',
    'CANCELLED'
);

-- Shipping Method enum
CREATE TYPE shipping_method_enum AS ENUM (
    'STANDARD',
    'EXPRESS',
    'PRIORITY',
    'OVERNIGHT',
    'INTERNATIONAL',
    'COURIER',
    'REGISTERED_MAIL',
    'BRANCH_PICKUP'
);

-- Activation Method enum
CREATE TYPE activation_method_enum AS ENUM (
    'ONLINE',
    'MOBILE_APP',
    'PHONE',
    'ATM',
    'BRANCH',
    'AUTOMATED',
    'SMS',
    'EMAIL'
);

-- Payment Method enum
CREATE TYPE payment_method_enum AS ENUM (
    'BANK_TRANSFER',
    'DEBIT_CARD',
    'CREDIT_CARD',
    'CASH',
    'CHECK',
    'DIRECT_DEBIT',
    'MOBILE_PAYMENT',
    'ONLINE_BANKING',
    'WALLET'
);

-- Payment Channel enum
CREATE TYPE payment_channel_enum AS ENUM (
    'ONLINE',
    'MOBILE_APP',
    'ATM',
    'BRANCH',
    'PHONE',
    'KIOSK',
    'AGENT',
    'THIRD_PARTY'
);

-- Delivery Method enum
CREATE TYPE delivery_method_enum AS ENUM (
    'EMAIL',
    'SMS',
    'PUSH_NOTIFICATION',
    'MAIL',
    'ONLINE_BANKING',
    'MOBILE_APP',
    'BRANCH_PICKUP'
);

-- Risk Rating enum
CREATE TYPE risk_rating_enum AS ENUM (
    'LOW',
    'MEDIUM',
    'HIGH',
    'VERY_HIGH',
    'CRITICAL'
);

-- Settlement Frequency enum
CREATE TYPE settlement_frequency_enum AS ENUM (
    'DAILY',
    'WEEKLY',
    'BI_WEEKLY',
    'MONTHLY',
    'QUARTERLY',
    'REAL_TIME',
    'T_PLUS_1',
    'T_PLUS_2',
    'T_PLUS_3'
);

-- Communication Type enum
CREATE TYPE communication_type_enum AS ENUM (
    'IP',
    'DIAL_UP',
    'GPRS',
    '3G',
    '4G',
    '5G',
    'WIFI',
    'BLUETOOTH',
    'ETHERNET'
);

-- Connection Type enum
CREATE TYPE connection_type_enum AS ENUM (
    'ETHERNET',
    'WIFI',
    'CELLULAR',
    'BLUETOOTH',
    'DIAL_UP',
    'USB',
    'SERIAL'
);

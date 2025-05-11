-- V3: Create casts from varchar to enum types with INOUT AS IMPLICIT

-- Card Status casts
CREATE CAST (VARCHAR AS card_status_enum) WITH INOUT AS IMPLICIT;

-- Payment Status casts
CREATE CAST (VARCHAR AS payment_status_enum) WITH INOUT AS IMPLICIT;

-- Dispute Status casts
CREATE CAST (VARCHAR AS dispute_status_enum) WITH INOUT AS IMPLICIT;

-- Dispute Stage casts
CREATE CAST (VARCHAR AS dispute_stage_enum) WITH INOUT AS IMPLICIT;

-- Terminal Status casts
CREATE CAST (VARCHAR AS terminal_status_enum) WITH INOUT AS IMPLICIT;

-- Terminal Type casts
CREATE CAST (VARCHAR AS terminal_type_enum) WITH INOUT AS IMPLICIT;

-- Merchant Status casts
CREATE CAST (VARCHAR AS merchant_status_enum) WITH INOUT AS IMPLICIT;

-- Merchant Type casts
CREATE CAST (VARCHAR AS merchant_type_enum) WITH INOUT AS IMPLICIT;

-- Activity Type casts
CREATE CAST (VARCHAR AS activity_type_enum) WITH INOUT AS IMPLICIT;

-- Activity Status casts
CREATE CAST (VARCHAR AS activity_status_enum) WITH INOUT AS IMPLICIT;

-- Manufacturing Status casts
CREATE CAST (VARCHAR AS manufacturing_status_enum) WITH INOUT AS IMPLICIT;

-- Shipping Method casts
CREATE CAST (VARCHAR AS shipping_method_enum) WITH INOUT AS IMPLICIT;

-- Activation Method casts
CREATE CAST (VARCHAR AS activation_method_enum) WITH INOUT AS IMPLICIT;

-- Payment Method casts
CREATE CAST (VARCHAR AS payment_method_enum) WITH INOUT AS IMPLICIT;

-- Payment Channel casts
CREATE CAST (VARCHAR AS payment_channel_enum) WITH INOUT AS IMPLICIT;

-- Delivery Method casts
CREATE CAST (VARCHAR AS delivery_method_enum) WITH INOUT AS IMPLICIT;

-- Risk Rating casts
CREATE CAST (VARCHAR AS risk_rating_enum) WITH INOUT AS IMPLICIT;

-- Settlement Frequency casts
CREATE CAST (VARCHAR AS settlement_frequency_enum) WITH INOUT AS IMPLICIT;

-- Communication Type casts
CREATE CAST (VARCHAR AS communication_type_enum) WITH INOUT AS IMPLICIT;

-- Connection Type casts
CREATE CAST (VARCHAR AS connection_type_enum) WITH INOUT AS IMPLICIT;

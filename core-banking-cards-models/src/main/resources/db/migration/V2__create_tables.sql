-- V2: Create all tables with enum types

-- Card Network table
CREATE TABLE IF NOT EXISTS card_network (
    card_network_id BIGSERIAL PRIMARY KEY,
    network_name VARCHAR(100) NOT NULL,
    network_code VARCHAR(50) NOT NULL,
    network_logo_url VARCHAR(255),
    support_contact VARCHAR(100),
    api_endpoint VARCHAR(255),
    api_key VARCHAR(255),
    api_secret VARCHAR(255),
    is_active BOOLEAN DEFAULT TRUE,
    description TEXT,
    date_created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    date_updated TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Card Type table
CREATE TABLE IF NOT EXISTS card_type (
    card_type_id BIGSERIAL PRIMARY KEY,
    type_name VARCHAR(100) NOT NULL,
    type_code VARCHAR(50) NOT NULL,
    is_credit BOOLEAN DEFAULT FALSE,
    is_debit BOOLEAN DEFAULT FALSE,
    is_prepaid BOOLEAN DEFAULT FALSE,
    is_virtual BOOLEAN DEFAULT FALSE,
    is_commercial BOOLEAN DEFAULT FALSE,
    is_gift BOOLEAN DEFAULT FALSE,
    default_credit_limit DOUBLE PRECISION,
    default_daily_limit DOUBLE PRECISION,
    default_monthly_limit DOUBLE PRECISION,
    is_active BOOLEAN DEFAULT TRUE,
    description TEXT,
    date_created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    date_updated TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Issuer table
CREATE TABLE IF NOT EXISTS issuer (
    issuer_id BIGSERIAL PRIMARY KEY,
    issuer_name VARCHAR(100) NOT NULL,
    issuer_code VARCHAR(50) NOT NULL,
    country_code VARCHAR(3),
    contact_email VARCHAR(100),
    contact_phone VARCHAR(20),
    is_active BOOLEAN DEFAULT TRUE,
    support_url VARCHAR(255),
    logo_url VARCHAR(255),
    description TEXT,
    date_created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    date_updated TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- BIN table
CREATE TABLE IF NOT EXISTS bin (
    bin_id BIGSERIAL PRIMARY KEY,
    bin_number VARCHAR(8) NOT NULL,
    bin_length INTEGER NOT NULL,
    issuer_id BIGINT REFERENCES issuer(issuer_id),
    card_network_id BIGINT REFERENCES card_network(card_network_id),
    card_type_id BIGINT REFERENCES card_type(card_type_id),
    country_code VARCHAR(3),
    currency_code VARCHAR(3),
    is_active BOOLEAN DEFAULT TRUE,
    description TEXT,
    date_created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    date_updated TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Card Design table
CREATE TABLE IF NOT EXISTS card_design (
    design_id BIGSERIAL PRIMARY KEY,
    design_name VARCHAR(100) NOT NULL,
    design_code VARCHAR(50) NOT NULL,
    front_image_url VARCHAR(255),
    back_image_url VARCHAR(255),
    card_type_id BIGINT REFERENCES card_type(card_type_id),
    issuer_id BIGINT REFERENCES issuer(issuer_id),
    card_network_id BIGINT REFERENCES card_network(card_network_id),
    is_customizable BOOLEAN DEFAULT FALSE,
    is_default BOOLEAN DEFAULT FALSE,
    is_active BOOLEAN DEFAULT TRUE,
    description TEXT,
    date_created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    date_updated TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Card Program table
CREATE TABLE IF NOT EXISTS card_program (
    program_id BIGSERIAL PRIMARY KEY,
    program_name VARCHAR(100) NOT NULL,
    program_code VARCHAR(50) NOT NULL,
    issuer_id BIGINT REFERENCES issuer(issuer_id),
    bin_id BIGINT REFERENCES bin(bin_id),
    card_type_id BIGINT REFERENCES card_type(card_type_id),
    card_network_id BIGINT REFERENCES card_network(card_network_id),
    default_design_id BIGINT REFERENCES card_design(design_id),
    start_date TIMESTAMP WITH TIME ZONE,
    end_date TIMESTAMP WITH TIME ZONE,
    is_active BOOLEAN DEFAULT TRUE,
    max_cards_per_customer INTEGER,
    default_daily_limit DOUBLE PRECISION,
    default_monthly_limit DOUBLE PRECISION,
    default_credit_limit DOUBLE PRECISION,
    default_card_validity_years INTEGER,
    supports_physical_cards BOOLEAN DEFAULT TRUE,
    supports_virtual_cards BOOLEAN DEFAULT FALSE,
    supports_contactless BOOLEAN DEFAULT TRUE,
    supports_international BOOLEAN DEFAULT TRUE,
    supports_atm_withdrawal BOOLEAN DEFAULT TRUE,
    supports_online_transactions BOOLEAN DEFAULT TRUE,
    supports_recurring_payments BOOLEAN DEFAULT TRUE,
    supports_apple_pay BOOLEAN DEFAULT FALSE,
    supports_google_pay BOOLEAN DEFAULT FALSE,
    supports_samsung_pay BOOLEAN DEFAULT FALSE,
    requires_pin BOOLEAN DEFAULT TRUE,
    requires_activation BOOLEAN DEFAULT TRUE,
    currency_code VARCHAR(3),
    country_code VARCHAR(3),
    terms_and_conditions_url VARCHAR(255),
    description TEXT,
    date_created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    date_updated TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Card table
CREATE TABLE IF NOT EXISTS card (
    card_id BIGSERIAL PRIMARY KEY,
    card_number VARCHAR(19),
    masked_card_number VARCHAR(19),
    card_sequence_number VARCHAR(3),
    bin_id BIGINT REFERENCES bin(bin_id),
    card_type_id BIGINT REFERENCES card_type(card_type_id),
    card_network_id BIGINT REFERENCES card_network(card_network_id),
    issuer_id BIGINT REFERENCES issuer(issuer_id),
    contract_id BIGINT,
    account_id BIGINT,
    customer_id BIGINT,
    card_status card_status_enum,
    card_holder_name VARCHAR(100),
    card_holder_id VARCHAR(50),
    expiration_month INTEGER,
    expiration_year INTEGER,
    cvv VARCHAR(4),
    pin VARCHAR(255),
    activation_date TIMESTAMP WITH TIME ZONE,
    issuance_date TIMESTAMP WITH TIME ZONE,
    expiration_date TIMESTAMP WITH TIME ZONE,
    last_used_date TIMESTAMP WITH TIME ZONE,
    is_physical BOOLEAN DEFAULT TRUE,
    is_virtual BOOLEAN DEFAULT FALSE,
    is_primary BOOLEAN DEFAULT FALSE,
    is_active BOOLEAN DEFAULT TRUE,
    is_locked BOOLEAN DEFAULT FALSE,
    lock_reason TEXT,
    daily_limit DOUBLE PRECISION,
    monthly_limit DOUBLE PRECISION,
    credit_limit DOUBLE PRECISION,
    available_balance DOUBLE PRECISION,
    currency_code VARCHAR(3),
    design_id BIGINT REFERENCES card_design(design_id),
    notes TEXT,
    date_created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    date_updated TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Card Configuration table
CREATE TABLE IF NOT EXISTS card_configuration (
    card_configuration_id BIGSERIAL PRIMARY KEY,
    card_id BIGINT REFERENCES card(card_id),
    program_id BIGINT REFERENCES card_program(program_id),
    config_key VARCHAR(100) NOT NULL,
    config_value VARCHAR(255),
    config_type VARCHAR(50),
    is_active BOOLEAN DEFAULT TRUE,
    activation_date TIMESTAMP WITH TIME ZONE,
    expiration_date TIMESTAMP WITH TIME ZONE,
    is_system_default BOOLEAN DEFAULT FALSE,
    is_program_default BOOLEAN DEFAULT FALSE,
    is_customer_configurable BOOLEAN DEFAULT FALSE,
    allowed_values TEXT,
    min_value VARCHAR(50),
    max_value VARCHAR(50),
    description TEXT,
    date_created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    date_updated TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Physical Card table
CREATE TABLE IF NOT EXISTS physical_card (
    physical_card_id BIGSERIAL PRIMARY KEY,
    card_id BIGINT REFERENCES card(card_id),
    embossed_name VARCHAR(100),
    plastic_id VARCHAR(50),
    design_id BIGINT REFERENCES card_design(design_id),
    is_contactless BOOLEAN DEFAULT TRUE,
    is_chip BOOLEAN DEFAULT TRUE,
    is_magstripe BOOLEAN DEFAULT TRUE,
    manufacturing_status manufacturing_status_enum,
    manufacturing_date TIMESTAMP WITH TIME ZONE,
    shipping_address VARCHAR(255),
    shipping_city VARCHAR(100),
    shipping_state VARCHAR(100),
    shipping_country VARCHAR(3),
    shipping_postal_code VARCHAR(20),
    shipping_method shipping_method_enum,
    shipping_tracking_number VARCHAR(50),
    shipping_carrier VARCHAR(50),
    shipment_date TIMESTAMP WITH TIME ZONE,
    estimated_delivery_date TIMESTAMP WITH TIME ZONE,
    actual_delivery_date TIMESTAMP WITH TIME ZONE,
    activation_method activation_method_enum,
    activation_date TIMESTAMP WITH TIME ZONE,
    is_activated BOOLEAN DEFAULT FALSE,
    replacement_reason VARCHAR(100),
    previous_card_id BIGINT REFERENCES card(card_id),
    notes TEXT,
    date_created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    date_updated TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Card Statement table
CREATE TABLE IF NOT EXISTS card_statement (
    statement_id BIGSERIAL PRIMARY KEY,
    card_id BIGINT REFERENCES card(card_id),
    customer_id BIGINT,
    account_id BIGINT,
    statement_reference VARCHAR(50),
    statement_date TIMESTAMP WITH TIME ZONE,
    statement_period_start TIMESTAMP WITH TIME ZONE,
    statement_period_end TIMESTAMP WITH TIME ZONE,
    due_date TIMESTAMP WITH TIME ZONE,
    closing_balance DECIMAL(19, 4),
    opening_balance DECIMAL(19, 4),
    minimum_payment_due DECIMAL(19, 4),
    total_payment_due DECIMAL(19, 4),
    currency_code VARCHAR(3),
    total_purchases DECIMAL(19, 4),
    total_cash_advances DECIMAL(19, 4),
    total_fees DECIMAL(19, 4),
    total_interest DECIMAL(19, 4),
    total_credits DECIMAL(19, 4),
    total_payments DECIMAL(19, 4),
    total_adjustments DECIMAL(19, 4),
    total_rewards_earned DECIMAL(19, 4),
    total_rewards_redeemed DECIMAL(19, 4),
    available_credit DECIMAL(19, 4),
    credit_limit DECIMAL(19, 4),
    cash_advance_limit DECIMAL(19, 4),
    interest_rate DECIMAL(10, 6),
    annual_percentage_rate DECIMAL(10, 6),
    payment_status payment_status_enum,
    is_generated BOOLEAN DEFAULT FALSE,
    generation_timestamp TIMESTAMP WITH TIME ZONE,
    is_delivered BOOLEAN DEFAULT FALSE,
    delivery_method delivery_method_enum,
    delivery_timestamp TIMESTAMP WITH TIME ZONE,
    delivery_address VARCHAR(255),
    is_viewed BOOLEAN DEFAULT FALSE,
    view_timestamp TIMESTAMP WITH TIME ZONE,
    document_url VARCHAR(255),
    notes TEXT,
    date_created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    date_updated TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Card Payment table
CREATE TABLE IF NOT EXISTS card_payment (
    payment_id BIGSERIAL PRIMARY KEY,
    card_id BIGINT REFERENCES card(card_id),
    customer_id BIGINT,
    account_id BIGINT,
    statement_id BIGINT REFERENCES card_statement(statement_id),
    payment_reference VARCHAR(50),
    external_reference VARCHAR(50),
    payment_amount DECIMAL(19, 4),
    currency_code VARCHAR(3),
    payment_method payment_method_enum,
    payment_channel payment_channel_enum,
    payment_status payment_status_enum,
    payment_timestamp TIMESTAMP WITH TIME ZONE,
    posting_timestamp TIMESTAMP WITH TIME ZONE,
    value_date TIMESTAMP WITH TIME ZONE,
    is_auto_payment BOOLEAN DEFAULT FALSE,
    is_minimum_payment BOOLEAN DEFAULT FALSE,
    is_full_payment BOOLEAN DEFAULT FALSE,
    is_scheduled_payment BOOLEAN DEFAULT FALSE,
    scheduled_date TIMESTAMP WITH TIME ZONE,
    recurrence_pattern VARCHAR(50),
    source_account_id VARCHAR(50),
    source_account_type VARCHAR(50),
    source_bank_code VARCHAR(20),
    source_bank_name VARCHAR(100),
    source_account_holder VARCHAR(100),
    payment_processor VARCHAR(100),
    processor_fee DECIMAL(19, 4),
    processor_reference VARCHAR(50),
    confirmation_code VARCHAR(50),
    failure_reason VARCHAR(255),
    failure_code VARCHAR(50),
    retry_count INTEGER,
    last_retry_timestamp TIMESTAMP WITH TIME ZONE,
    next_retry_timestamp TIMESTAMP WITH TIME ZONE,
    receipt_url VARCHAR(255),
    notes TEXT,
    date_created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    date_updated TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Card Dispute table
CREATE TABLE IF NOT EXISTS card_dispute (
    dispute_id BIGSERIAL PRIMARY KEY,
    card_id BIGINT REFERENCES card(card_id),
    transaction_id BIGINT,
    customer_id BIGINT,
    account_id BIGINT,
    dispute_reference VARCHAR(50),
    provider_reference VARCHAR(50),
    network_reference VARCHAR(50),
    dispute_reason_code VARCHAR(20),
    dispute_reason_description VARCHAR(255),
    dispute_status dispute_status_enum,
    dispute_stage dispute_stage_enum,
    dispute_amount DECIMAL(19, 4),
    dispute_currency VARCHAR(3),
    filing_timestamp TIMESTAMP WITH TIME ZONE,
    response_due_date TIMESTAMP WITH TIME ZONE,
    resolution_timestamp TIMESTAMP WITH TIME ZONE,
    resolution_code VARCHAR(20),
    resolution_description VARCHAR(255),
    is_cardholder_credited BOOLEAN DEFAULT FALSE,
    credit_timestamp TIMESTAMP WITH TIME ZONE,
    credit_amount DECIMAL(19, 4),
    credit_currency VARCHAR(3),
    is_merchant_debited BOOLEAN DEFAULT FALSE,
    debit_timestamp TIMESTAMP WITH TIME ZONE,
    debit_amount DECIMAL(19, 4),
    debit_currency VARCHAR(3),
    cardholder_statement TEXT,
    merchant_response TEXT,
    evidence_documents TEXT,
    assigned_agent_id BIGINT,
    assigned_agent_name VARCHAR(100),
    last_updated_timestamp TIMESTAMP WITH TIME ZONE,
    notes TEXT,
    date_created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    date_updated TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Card Terminal table
CREATE TABLE IF NOT EXISTS card_terminal (
    terminal_id BIGSERIAL PRIMARY KEY,
    terminal_reference VARCHAR(50),
    terminal_serial_number VARCHAR(50),
    terminal_name VARCHAR(100),
    terminal_description TEXT,
    terminal_type terminal_type_enum,
    terminal_model VARCHAR(50),
    terminal_manufacturer VARCHAR(100),
    terminal_status terminal_status_enum,
    is_active BOOLEAN DEFAULT TRUE,
    activation_date TIMESTAMP WITH TIME ZONE,
    deactivation_date TIMESTAMP WITH TIME ZONE,
    deactivation_reason VARCHAR(255),
    merchant_id BIGINT,
    merchant_name VARCHAR(100),
    merchant_location_id VARCHAR(50),
    merchant_location_name VARCHAR(100),
    address_line1 VARCHAR(255),
    address_line2 VARCHAR(255),
    city VARCHAR(100),
    state VARCHAR(100),
    postal_code VARCHAR(20),
    country VARCHAR(3),
    geolocation VARCHAR(100),
    acquirer_id VARCHAR(50),
    acquirer_name VARCHAR(100),
    processor_id VARCHAR(50),
    processor_name VARCHAR(100),
    software_version VARCHAR(50),
    firmware_version VARCHAR(50),
    hardware_version VARCHAR(50),
    last_maintenance_date TIMESTAMP WITH TIME ZONE,
    next_maintenance_date TIMESTAMP WITH TIME ZONE,
    last_update_date TIMESTAMP WITH TIME ZONE,
    installation_date TIMESTAMP WITH TIME ZONE,
    is_physical BOOLEAN DEFAULT TRUE,
    is_virtual BOOLEAN DEFAULT FALSE,
    is_mobile BOOLEAN DEFAULT FALSE,
    is_attended BOOLEAN DEFAULT TRUE,
    is_unattended BOOLEAN DEFAULT FALSE,
    is_contactless BOOLEAN DEFAULT TRUE,
    is_chip BOOLEAN DEFAULT TRUE,
    is_magstripe BOOLEAN DEFAULT TRUE,
    is_pin_supported BOOLEAN DEFAULT TRUE,
    is_signature_supported BOOLEAN DEFAULT TRUE,
    is_biometric_supported BOOLEAN DEFAULT FALSE,
    is_nfc_supported BOOLEAN DEFAULT TRUE,
    is_qr_supported BOOLEAN DEFAULT FALSE,
    is_emv_supported BOOLEAN DEFAULT TRUE,
    is_p2pe_supported BOOLEAN DEFAULT FALSE,
    is_tokenization_supported BOOLEAN DEFAULT FALSE,
    supported_card_networks VARCHAR(255),
    supported_payment_methods VARCHAR(255),
    supported_currencies VARCHAR(255),
    default_currency VARCHAR(3),
    communication_type communication_type_enum,
    connection_type connection_type_enum,
    ip_address VARCHAR(45),
    mac_address VARCHAR(17),
    is_pci_compliant BOOLEAN DEFAULT TRUE,
    pci_compliance_date TIMESTAMP WITH TIME ZONE,
    pci_compliance_expiry TIMESTAMP WITH TIME ZONE,
    encryption_method VARCHAR(50),
    key_management_scheme VARCHAR(50),
    last_key_rotation_date TIMESTAMP WITH TIME ZONE,
    next_key_rotation_date TIMESTAMP WITH TIME ZONE,
    is_fault_detected BOOLEAN DEFAULT FALSE,
    fault_code VARCHAR(50),
    fault_description VARCHAR(255),
    fault_detection_date TIMESTAMP WITH TIME ZONE,
    last_transaction_timestamp TIMESTAMP WITH TIME ZONE,
    last_transaction_id VARCHAR(50),
    last_transaction_status VARCHAR(50),
    notes TEXT,
    date_created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    date_updated TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Card Merchant table
CREATE TABLE IF NOT EXISTS card_merchant (
    merchant_id BIGSERIAL PRIMARY KEY,
    merchant_reference VARCHAR(50),
    merchant_name VARCHAR(100) NOT NULL,
    merchant_legal_name VARCHAR(100),
    merchant_display_name VARCHAR(100),
    merchant_description TEXT,
    merchant_category_code VARCHAR(4),
    merchant_category_name VARCHAR(100),
    merchant_type merchant_type_enum,
    merchant_status merchant_status_enum,
    is_active BOOLEAN DEFAULT TRUE,
    activation_date TIMESTAMP WITH TIME ZONE,
    deactivation_date TIMESTAMP WITH TIME ZONE,
    deactivation_reason VARCHAR(255),
    tax_id VARCHAR(50),
    registration_number VARCHAR(50),
    website_url VARCHAR(255),
    logo_url VARCHAR(255),
    address_line1 VARCHAR(255),
    address_line2 VARCHAR(255),
    city VARCHAR(100),
    state VARCHAR(100),
    postal_code VARCHAR(20),
    country VARCHAR(3),
    phone VARCHAR(20),
    email VARCHAR(100),
    contact_person_name VARCHAR(100),
    contact_person_title VARCHAR(50),
    contact_person_phone VARCHAR(20),
    contact_person_email VARCHAR(100),
    is_online BOOLEAN DEFAULT FALSE,
    is_physical BOOLEAN DEFAULT TRUE,
    is_mobile BOOLEAN DEFAULT FALSE,
    is_international BOOLEAN DEFAULT FALSE,
    supported_currencies VARCHAR(255),
    default_currency VARCHAR(3),
    supported_card_networks VARCHAR(255),
    supported_payment_methods VARCHAR(255),
    is_high_risk BOOLEAN DEFAULT FALSE,
    risk_rating risk_rating_enum,
    risk_score INTEGER,
    risk_assessment_date TIMESTAMP WITH TIME ZONE,
    is_fraud_suspected BOOLEAN DEFAULT FALSE,
    fraud_reason VARCHAR(255),
    fraud_report_date TIMESTAMP WITH TIME ZONE,
    is_blacklisted BOOLEAN DEFAULT FALSE,
    blacklist_reason VARCHAR(255),
    blacklist_date TIMESTAMP WITH TIME ZONE,
    is_settlement_enabled BOOLEAN DEFAULT TRUE,
    settlement_frequency settlement_frequency_enum,
    settlement_day INTEGER,
    settlement_bank_name VARCHAR(100),
    settlement_account_number VARCHAR(50),
    settlement_account_name VARCHAR(100),
    settlement_bank_code VARCHAR(20),
    settlement_currency VARCHAR(3),
    acquirer_id VARCHAR(50),
    acquirer_name VARCHAR(100),
    processor_id VARCHAR(50),
    processor_name VARCHAR(100),
    terminal_ids TEXT,
    notes TEXT,
    date_created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    date_updated TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Card Activity table
CREATE TABLE IF NOT EXISTS card_activity (
    activity_id BIGSERIAL PRIMARY KEY,
    card_id BIGINT REFERENCES card(card_id),
    customer_id BIGINT,
    account_id BIGINT,
    activity_reference VARCHAR(50),
    activity_type activity_type_enum,
    activity_category VARCHAR(50),
    activity_description VARCHAR(255),
    activity_timestamp TIMESTAMP WITH TIME ZONE,
    activity_channel VARCHAR(50),
    activity_source VARCHAR(50),
    activity_status activity_status_enum,
    activity_result VARCHAR(50),
    activity_details TEXT,
    previous_value TEXT,
    new_value TEXT,
    change_reason VARCHAR(255),
    change_authorized_by VARCHAR(100),
    ip_address VARCHAR(45),
    device_id VARCHAR(100),
    device_type VARCHAR(50),
    device_os VARCHAR(50),
    browser_type VARCHAR(50),
    user_agent TEXT,
    geolocation VARCHAR(100),
    is_customer_initiated BOOLEAN DEFAULT FALSE,
    is_system_initiated BOOLEAN DEFAULT TRUE,
    is_agent_initiated BOOLEAN DEFAULT FALSE,
    agent_id VARCHAR(50),
    agent_name VARCHAR(100),
    is_successful BOOLEAN DEFAULT TRUE,
    failure_reason VARCHAR(255),
    failure_code VARCHAR(50),
    is_notification_sent BOOLEAN DEFAULT FALSE,
    notification_channel VARCHAR(50),
    notification_timestamp TIMESTAMP WITH TIME ZONE,
    notification_recipient VARCHAR(100),
    related_entity_type VARCHAR(50),
    related_entity_id BIGINT,
    notes TEXT,
    date_created TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    date_updated TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes for better performance

-- Card Network indexes
CREATE INDEX idx_card_network_code ON card_network(network_code);
CREATE INDEX idx_card_network_active ON card_network(is_active);

-- Card Type indexes
CREATE INDEX idx_card_type_code ON card_type(type_code);
CREATE INDEX idx_card_type_active ON card_type(is_active);

-- Issuer indexes
CREATE INDEX idx_issuer_code ON issuer(issuer_code);
CREATE INDEX idx_issuer_active ON issuer(is_active);

-- BIN indexes
CREATE INDEX idx_bin_number ON bin(bin_number);
CREATE INDEX idx_bin_issuer ON bin(issuer_id);
CREATE INDEX idx_bin_network ON bin(card_network_id);
CREATE INDEX idx_bin_type ON bin(card_type_id);

-- Card Design indexes
CREATE INDEX idx_card_design_code ON card_design(design_code);
CREATE INDEX idx_card_design_type ON card_design(card_type_id);
CREATE INDEX idx_card_design_issuer ON card_design(issuer_id);
CREATE INDEX idx_card_design_network ON card_design(card_network_id);

-- Card Program indexes
CREATE INDEX idx_card_program_code ON card_program(program_code);
CREATE INDEX idx_card_program_issuer ON card_program(issuer_id);
CREATE INDEX idx_card_program_bin ON card_program(bin_id);
CREATE INDEX idx_card_program_type ON card_program(card_type_id);
CREATE INDEX idx_card_program_network ON card_program(card_network_id);
CREATE INDEX idx_card_program_active ON card_program(is_active);

-- Card indexes
CREATE INDEX idx_card_number ON card(card_number);
CREATE INDEX idx_card_masked_number ON card(masked_card_number);
CREATE INDEX idx_card_bin ON card(bin_id);
CREATE INDEX idx_card_type ON card(card_type_id);
CREATE INDEX idx_card_network ON card(card_network_id);
CREATE INDEX idx_card_issuer ON card(issuer_id);
CREATE INDEX idx_card_customer ON card(customer_id);
CREATE INDEX idx_card_account ON card(account_id);
CREATE INDEX idx_card_status ON card(card_status);
CREATE INDEX idx_card_active ON card(is_active);
CREATE INDEX idx_card_expiration ON card(expiration_date);

-- Card Configuration indexes
CREATE INDEX idx_card_config_card ON card_configuration(card_id);
CREATE INDEX idx_card_config_program ON card_configuration(program_id);
CREATE INDEX idx_card_config_key ON card_configuration(config_key);
CREATE INDEX idx_card_config_active ON card_configuration(is_active);

-- Physical Card indexes
CREATE INDEX idx_physical_card_card ON physical_card(card_id);
CREATE INDEX idx_physical_card_design ON physical_card(design_id);
CREATE INDEX idx_physical_card_activated ON physical_card(is_activated);
CREATE INDEX idx_physical_card_status ON physical_card(manufacturing_status);

-- Card Statement indexes
CREATE INDEX idx_card_statement_card ON card_statement(card_id);
CREATE INDEX idx_card_statement_customer ON card_statement(customer_id);
CREATE INDEX idx_card_statement_account ON card_statement(account_id);
CREATE INDEX idx_card_statement_reference ON card_statement(statement_reference);
CREATE INDEX idx_card_statement_date ON card_statement(statement_date);
CREATE INDEX idx_card_statement_due_date ON card_statement(due_date);
CREATE INDEX idx_card_statement_status ON card_statement(payment_status);

-- Card Payment indexes
CREATE INDEX idx_card_payment_card ON card_payment(card_id);
CREATE INDEX idx_card_payment_customer ON card_payment(customer_id);
CREATE INDEX idx_card_payment_account ON card_payment(account_id);
CREATE INDEX idx_card_payment_statement ON card_payment(statement_id);
CREATE INDEX idx_card_payment_reference ON card_payment(payment_reference);
CREATE INDEX idx_card_payment_status ON card_payment(payment_status);
CREATE INDEX idx_card_payment_timestamp ON card_payment(payment_timestamp);

-- Card Dispute indexes
CREATE INDEX idx_card_dispute_card ON card_dispute(card_id);
CREATE INDEX idx_card_dispute_transaction ON card_dispute(transaction_id);
CREATE INDEX idx_card_dispute_customer ON card_dispute(customer_id);
CREATE INDEX idx_card_dispute_account ON card_dispute(account_id);
CREATE INDEX idx_card_dispute_reference ON card_dispute(dispute_reference);
CREATE INDEX idx_card_dispute_status ON card_dispute(dispute_status);
CREATE INDEX idx_card_dispute_stage ON card_dispute(dispute_stage);
CREATE INDEX idx_card_dispute_filing ON card_dispute(filing_timestamp);

-- Card Terminal indexes
CREATE INDEX idx_card_terminal_reference ON card_terminal(terminal_reference);
CREATE INDEX idx_card_terminal_serial ON card_terminal(terminal_serial_number);
CREATE INDEX idx_card_terminal_merchant ON card_terminal(merchant_id);
CREATE INDEX idx_card_terminal_status ON card_terminal(terminal_status);
CREATE INDEX idx_card_terminal_active ON card_terminal(is_active);
CREATE INDEX idx_card_terminal_type ON card_terminal(terminal_type);

-- Card Merchant indexes
CREATE INDEX idx_card_merchant_reference ON card_merchant(merchant_reference);
CREATE INDEX idx_card_merchant_name ON card_merchant(merchant_name);
CREATE INDEX idx_card_merchant_category ON card_merchant(merchant_category_code);
CREATE INDEX idx_card_merchant_status ON card_merchant(merchant_status);
CREATE INDEX idx_card_merchant_active ON card_merchant(is_active);
CREATE INDEX idx_card_merchant_type ON card_merchant(merchant_type);
CREATE INDEX idx_card_merchant_risk ON card_merchant(risk_rating);

-- Card Activity indexes
CREATE INDEX idx_card_activity_card ON card_activity(card_id);
CREATE INDEX idx_card_activity_customer ON card_activity(customer_id);
CREATE INDEX idx_card_activity_account ON card_activity(account_id);
CREATE INDEX idx_card_activity_reference ON card_activity(activity_reference);
CREATE INDEX idx_card_activity_type ON card_activity(activity_type);
CREATE INDEX idx_card_activity_timestamp ON card_activity(activity_timestamp);
CREATE INDEX idx_card_activity_status ON card_activity(activity_status);

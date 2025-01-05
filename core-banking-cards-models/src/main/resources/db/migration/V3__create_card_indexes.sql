-- V3__create_card_indexes.sql
-- Create indexes for better query performance
CREATE INDEX idx_card_contract_id ON card(contract_id);
CREATE INDEX idx_card_account_id ON card(account_id);
CREATE INDEX idx_card_status ON card(card_status);
CREATE INDEX idx_card_expiration ON card(expiration_date);

CREATE INDEX idx_virtual_card_status ON virtual_card(virtual_card_status);
CREATE INDEX idx_virtual_card_number ON virtual_card(virtual_card_number);

CREATE INDEX idx_physical_card_number ON physical_card(card_number);
CREATE INDEX idx_physical_card_holder ON physical_card(card_holder_name);

CREATE INDEX idx_card_transaction_card_id ON card_transaction(card_id);
CREATE INDEX idx_card_transaction_timestamp ON card_transaction(card_transaction_timestamp);
CREATE INDEX idx_card_transaction_status ON card_transaction(transaction_status);
CREATE INDEX idx_card_transaction_type ON card_transaction(transaction_type);
CREATE INDEX idx_card_transaction_reference ON card_transaction(card_transaction_reference);

CREATE INDEX idx_card_limit_card_id ON card_limit(card_id);
CREATE INDEX idx_card_limit_type ON card_limit(limit_type);

CREATE INDEX idx_card_configuration_card_id ON card_configuration(card_id);
CREATE INDEX idx_card_configuration_type ON card_configuration(config_type);

CREATE INDEX idx_card_security_card_id ON card_security(card_id);
CREATE INDEX idx_card_security_feature ON card_security(security_feature);

CREATE INDEX idx_card_provider_card_id ON card_provider(card_id);
CREATE INDEX idx_card_provider_status ON card_provider(status);
CREATE INDEX idx_card_provider_external_reference ON card_provider(external_reference);
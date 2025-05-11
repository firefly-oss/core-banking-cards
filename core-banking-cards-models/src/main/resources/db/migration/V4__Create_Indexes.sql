-- Create indexes for the database

-- Card table indexes
CREATE INDEX idx_card_card_number ON card(card_number);
CREATE INDEX idx_card_masked_card_number ON card(masked_card_number);
CREATE INDEX idx_card_bin_id ON card(bin_id);
CREATE INDEX idx_card_card_type_id ON card(card_type_id);
CREATE INDEX idx_card_card_network_id ON card(card_network_id);
CREATE INDEX idx_card_issuer_id ON card(issuer_id);
CREATE INDEX idx_card_contract_id ON card(contract_id);
CREATE INDEX idx_card_account_id ON card(account_id);
CREATE INDEX idx_card_party_id ON card(party_id);
CREATE INDEX idx_card_card_status ON card(card_status);
CREATE INDEX idx_card_expiration_date ON card(expiration_date);

-- Physical Card table indexes
CREATE INDEX idx_physical_card_card_id ON physical_card(card_id);
CREATE INDEX idx_physical_card_design_id ON physical_card(design_id);
CREATE INDEX idx_physical_card_is_activated ON physical_card(is_activated);

-- Virtual Card table indexes
CREATE INDEX idx_virtual_card_card_id ON virtual_card(card_id);
CREATE INDEX idx_virtual_card_device_id ON virtual_card(device_id);
CREATE INDEX idx_virtual_card_wallet_provider ON virtual_card(wallet_provider);
CREATE INDEX idx_virtual_card_virtual_card_status ON virtual_card(virtual_card_status);

-- Card Balance table indexes
CREATE INDEX idx_card_balance_card_id ON card_balance(card_id);

-- Card Transaction table indexes
CREATE INDEX idx_card_transaction_card_id ON card_transaction(card_id);
CREATE INDEX idx_card_transaction_transaction_reference ON card_transaction(transaction_reference);
CREATE INDEX idx_card_transaction_account_id ON card_transaction(account_id);
CREATE INDEX idx_card_transaction_party_id ON card_transaction(party_id);
CREATE INDEX idx_card_transaction_transaction_timestamp ON card_transaction(transaction_timestamp);
CREATE INDEX idx_card_transaction_transaction_type ON card_transaction(transaction_type);
CREATE INDEX idx_card_transaction_transaction_status ON card_transaction(transaction_status);
CREATE INDEX idx_card_transaction_merchant_id ON card_transaction(merchant_id);
CREATE INDEX idx_card_transaction_merchant_category_code ON card_transaction(merchant_category_code);

-- Card Dispute table indexes
CREATE INDEX idx_card_dispute_card_id ON card_dispute(card_id);
CREATE INDEX idx_card_dispute_transaction_id ON card_dispute(transaction_id);
CREATE INDEX idx_card_dispute_party_id ON card_dispute(party_id);
CREATE INDEX idx_card_dispute_account_id ON card_dispute(account_id);
CREATE INDEX idx_card_dispute_dispute_reference ON card_dispute(dispute_reference);
CREATE INDEX idx_card_dispute_dispute_status ON card_dispute(dispute_status);
CREATE INDEX idx_card_dispute_filing_timestamp ON card_dispute(filing_timestamp);

-- Card Statement table indexes
CREATE INDEX idx_card_statement_card_id ON card_statement(card_id);
CREATE INDEX idx_card_statement_party_id ON card_statement(party_id);
CREATE INDEX idx_card_statement_account_id ON card_statement(account_id);
CREATE INDEX idx_card_statement_statement_date ON card_statement(statement_date);
CREATE INDEX idx_card_statement_due_date ON card_statement(due_date);

-- Card Payment table indexes
CREATE INDEX idx_card_payment_card_id ON card_payment(card_id);
CREATE INDEX idx_card_payment_statement_id ON card_payment(statement_id);
CREATE INDEX idx_card_payment_party_id ON card_payment(party_id);
CREATE INDEX idx_card_payment_account_id ON card_payment(account_id);
CREATE INDEX idx_card_payment_payment_reference ON card_payment(payment_reference);
CREATE INDEX idx_card_payment_payment_timestamp ON card_payment(payment_timestamp);

-- Card Activity table indexes
CREATE INDEX idx_card_activity_card_id ON card_activity(card_id);
CREATE INDEX idx_card_activity_party_id ON card_activity(party_id);
CREATE INDEX idx_card_activity_account_id ON card_activity(account_id);
CREATE INDEX idx_card_activity_activity_reference ON card_activity(activity_reference);
CREATE INDEX idx_card_activity_activity_timestamp ON card_activity(activity_timestamp);
CREATE INDEX idx_card_activity_activity_type ON card_activity(activity_type);
CREATE INDEX idx_card_activity_activity_status ON card_activity(activity_status);

-- Card Limit table indexes
CREATE INDEX idx_card_limit_card_id ON card_limit(card_id);
CREATE INDEX idx_card_limit_party_id ON card_limit(party_id);
CREATE INDEX idx_card_limit_limit_type ON card_limit(limit_type);

-- Card Fee table indexes
CREATE INDEX idx_card_fee_card_id ON card_fee(card_id);
CREATE INDEX idx_card_fee_program_id ON card_fee(program_id);

-- Card Interest table indexes
CREATE INDEX idx_card_interest_card_id ON card_interest(card_id);
CREATE INDEX idx_card_interest_program_id ON card_interest(program_id);

-- Card Promotion table indexes
CREATE INDEX idx_card_promotion_card_id ON card_promotion(card_id);
CREATE INDEX idx_card_promotion_program_id ON card_promotion(program_id);

-- Card Reward table indexes
CREATE INDEX idx_card_reward_card_id ON card_reward(card_id);
CREATE INDEX idx_card_reward_transaction_id ON card_reward(transaction_id);
CREATE INDEX idx_card_reward_party_id ON card_reward(party_id);
CREATE INDEX idx_card_reward_account_id ON card_reward(account_id);
CREATE INDEX idx_card_reward_program_id ON card_reward(program_id);
CREATE INDEX idx_card_reward_promotion_id ON card_reward(promotion_id);

-- Card Security table indexes
CREATE INDEX idx_card_security_card_id ON card_security(card_id);
CREATE INDEX idx_card_security_security_feature ON card_security(security_feature);

-- Card Configuration table indexes
CREATE INDEX idx_card_configuration_card_id ON card_configuration(card_id);
CREATE INDEX idx_card_configuration_config_type ON card_configuration(config_type);

-- Card Provider table indexes
CREATE INDEX idx_card_provider_provider_code ON card_provider(provider_code);
CREATE INDEX idx_card_provider_status ON card_provider(status);

-- Card Acquirer table indexes
CREATE INDEX idx_card_acquirer_acquirer_code ON card_acquirer(acquirer_code);

-- Card Processor table indexes
CREATE INDEX idx_card_processor_processor_code ON card_processor(processor_code);

-- Card Gateway table indexes
CREATE INDEX idx_card_gateway_gateway_code ON card_gateway(gateway_code);

-- Card Application table indexes
CREATE INDEX idx_card_application_party_id ON card_application(party_id);
CREATE INDEX idx_card_application_account_id ON card_application(account_id);
CREATE INDEX idx_card_application_application_reference ON card_application(application_reference);
CREATE INDEX idx_card_application_card_type_id ON card_application(card_type_id);
CREATE INDEX idx_card_application_program_id ON card_application(program_id);
CREATE INDEX idx_card_application_design_id ON card_application(design_id);
CREATE INDEX idx_card_application_application_status ON card_application(application_status);
CREATE INDEX idx_card_application_card_id ON card_application(card_id);

-- Card Alert table indexes
CREATE INDEX idx_card_alert_card_id ON card_alert(card_id);
CREATE INDEX idx_card_alert_party_id ON card_alert(party_id);
CREATE INDEX idx_card_alert_alert_type ON card_alert(alert_type);

-- Fraud Case table indexes
CREATE INDEX idx_fraud_case_card_id ON fraud_case(card_id);
CREATE INDEX idx_fraud_case_transaction_id ON fraud_case(transaction_id);
CREATE INDEX idx_fraud_case_party_id ON fraud_case(party_id);
CREATE INDEX idx_fraud_case_account_id ON fraud_case(account_id);
CREATE INDEX idx_fraud_case_case_reference ON fraud_case(case_reference);
CREATE INDEX idx_fraud_case_fraud_status ON fraud_case(fraud_status);
CREATE INDEX idx_fraud_case_detection_timestamp ON fraud_case(detection_timestamp);

-- Card Enrollment table indexes
CREATE INDEX idx_card_enrollment_card_id ON card_enrollment(card_id);
CREATE INDEX idx_card_enrollment_party_id ON card_enrollment(party_id);
CREATE INDEX idx_card_enrollment_service_type ON card_enrollment(service_type);
CREATE INDEX idx_card_enrollment_enrollment_status ON card_enrollment(enrollment_status);
-- Create base card table
CREATE TABLE card (
                      card_id BIGSERIAL PRIMARY KEY,
                      contract_id BIGINT NOT NULL,
                      account_id BIGINT NOT NULL,
                      card_type CARD_TYPE NOT NULL,
                      card_status CARD_STATUS NOT NULL,
                      issuing_bank VARCHAR(255) NOT NULL,
                      issuance_date TIMESTAMP NOT NULL,
                      expiration_date TIMESTAMP NOT NULL,
                      physical_flag BOOLEAN NOT NULL,
                      date_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      date_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE virtual_card (
                              virtual_card_id BIGSERIAL PRIMARY KEY,
                              card_id BIGINT NOT NULL UNIQUE,
                              device_id VARCHAR(255) NOT NULL,
                              virtual_card_number VARCHAR(255) NOT NULL,
                              virtual_card_status VIRTUAL_CARD_STATUS NOT NULL,
                              creation_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              update_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              CONSTRAINT fk_virtual_card_card FOREIGN KEY (card_id) REFERENCES card (card_id) ON DELETE CASCADE
);

CREATE TABLE physical_card (
                               physical_card_id BIGSERIAL PRIMARY KEY,
                               card_id BIGINT NOT NULL UNIQUE,
                               card_number VARCHAR(255) NOT NULL,
                               card_cvv VARCHAR(4) NOT NULL,
                               card_holder_name VARCHAR(255) NOT NULL,
                               card_network VARCHAR(50) NOT NULL,
                               card_design VARCHAR(100),
                               shipment_date TIMESTAMP,
                               delivery_date TIMESTAMP,
                               date_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               date_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               CONSTRAINT fk_physical_card_card FOREIGN KEY (card_id) REFERENCES card (card_id) ON DELETE CASCADE
);

CREATE TABLE card_transaction (
                                  card_transaction_id BIGSERIAL,
                                  card_id BIGINT NOT NULL,
                                  transaction_type TRANSACTION_TYPE NOT NULL,
                                  merchant_info TEXT,
                                  transaction_status TRANSACTION_STATUS NOT NULL,
                                  card_auth_code VARCHAR(50),
                                  card_merchant_category_code VARCHAR(4),
                                  card_merchant_name VARCHAR(255),
                                  card_pos_entry_mode VARCHAR(50),
                                  card_transaction_reference VARCHAR(255) NOT NULL,
                                  card_terminal_id VARCHAR(50),
                                  card_holder_country CHAR(2),
                                  card_present_flag BOOLEAN NOT NULL,
                                  card_transaction_timestamp TIMESTAMP NOT NULL,
                                  card_fraud_flag BOOLEAN NOT NULL DEFAULT FALSE,
                                  card_currency_conversion_rate DECIMAL(10,6),
                                  card_fee_amount DECIMAL(10,2),
                                  card_fee_currency CHAR(3),
                                  card_installment_plan TEXT,
                                  date_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  date_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  CONSTRAINT pk_card_transaction PRIMARY KEY (card_transaction_id, card_transaction_timestamp),
                                  CONSTRAINT fk_card_transaction_card FOREIGN KEY (card_id) REFERENCES card (card_id) ON DELETE CASCADE
) PARTITION BY RANGE (card_transaction_timestamp);

CREATE TABLE card_limit (
                            card_limit_id BIGSERIAL PRIMARY KEY,
                            card_id BIGINT NOT NULL,
                            limit_type LIMIT_TYPE NOT NULL,
                            limit_amount DECIMAL(15,2) NOT NULL,
                            current_usage DECIMAL(15,2) NOT NULL DEFAULT 0,
                            reset_period RESET_PERIOD NOT NULL,
                            date_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            date_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            CONSTRAINT fk_card_limit_card FOREIGN KEY (card_id) REFERENCES card (card_id) ON DELETE CASCADE
);

CREATE TABLE card_configuration (
                                    card_configuration_id BIGSERIAL PRIMARY KEY,
                                    card_id BIGINT NOT NULL,
                                    config_type CONFIG_TYPE NOT NULL,
                                    config_value BOOLEAN NOT NULL,
                                    date_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                    date_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                    CONSTRAINT fk_card_configuration_card FOREIGN KEY (card_id) REFERENCES card (card_id) ON DELETE CASCADE
);

CREATE TABLE card_security (
                               card_security_id BIGSERIAL PRIMARY KEY,
                               card_id BIGINT NOT NULL,
                               security_feature SECURITY_FEATURE NOT NULL,
                               security_status BOOLEAN NOT NULL,
                               date_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               date_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               CONSTRAINT fk_card_security_card FOREIGN KEY (card_id) REFERENCES card (card_id) ON DELETE CASCADE
);

CREATE TABLE card_provider (
                               card_provider_id BIGSERIAL PRIMARY KEY,
                               card_id BIGINT NOT NULL,
                               provider_name VARCHAR(255) NOT NULL,
                               external_reference VARCHAR(255) NOT NULL,
                               status PROVIDER_STATUS NOT NULL,
                               date_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               date_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               CONSTRAINT fk_card_provider_card FOREIGN KEY (card_id) REFERENCES card (card_id) ON DELETE CASCADE
);
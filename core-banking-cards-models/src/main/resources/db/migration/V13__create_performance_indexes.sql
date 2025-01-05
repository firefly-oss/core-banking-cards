-- V13__create_performance_indexes.sql

-- Create specialized indexes for common query patterns
DROP INDEX IF EXISTS idx_card_transaction_compound;
DROP INDEX IF EXISTS idx_card_transaction_fraud;
DROP INDEX IF EXISTS idx_card_active_status;

CREATE INDEX idx_card_transaction_compound
    ON card_transaction (
                         card_id,
                         transaction_type,
                         transaction_status,
                         card_transaction_timestamp
        );

CREATE INDEX idx_card_transaction_fraud
    ON card_transaction (card_fraud_flag)
    WHERE card_fraud_flag = true;

CREATE INDEX idx_card_active_status
    ON card (card_status, card_type)
    WHERE card_status = 'ACTIVE';
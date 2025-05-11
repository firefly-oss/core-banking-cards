-- V4: Drop card_customer table if it exists
DROP TABLE IF EXISTS card_customer CASCADE;

-- Drop any indexes related to card_customer
DROP INDEX IF EXISTS idx_card_customer_id;
DROP INDEX IF EXISTS idx_card_customer_reference;
DROP INDEX IF EXISTS idx_card_customer_email;
DROP INDEX IF EXISTS idx_card_customer_phone;
DROP INDEX IF EXISTS idx_card_customer_status;
DROP INDEX IF EXISTS idx_card_customer_active;

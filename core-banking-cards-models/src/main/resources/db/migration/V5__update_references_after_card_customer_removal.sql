-- V5: Update references after card_customer removal
-- Note: We're keeping the party_id columns in other tables as they might be used for external references,
-- but we're removing any foreign key constraints that might have existed

-- No need to drop foreign keys as they don't exist in the current schema
-- The party_id columns in various tables are not foreign keys to card_customer
-- They are likely references to parties in an external system

-- We'll keep the party_id columns in the following tables:
-- - card
-- - card_statement
-- - card_payment
-- - card_dispute
-- - card_activity
-- - card_limit

-- These columns will now be used to reference parties in external systems rather than in the card_customer table

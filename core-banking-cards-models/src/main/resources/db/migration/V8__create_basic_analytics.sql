-- V8__create_basic_analytics.sql

-- Drop existing views if they exist
DROP MATERIALIZED VIEW IF EXISTS card_mv_daily_transaction_summary;
DROP MATERIALIZED VIEW IF EXISTS card_mv_monthly_card_metrics;
DROP FUNCTION IF EXISTS refresh_card_basic_analytics();
DROP FUNCTION IF EXISTS trg_refresh_card_basic_analytics();

-- Basic Transaction Analytics
CREATE MATERIALIZED VIEW card_mv_daily_transaction_summary AS
SELECT
    DATE(ct.card_transaction_timestamp) AS transaction_date,
    c.card_type,
    ct.transaction_type,
    COUNT(*) AS transaction_count,
    SUM(ct.card_fee_amount) AS total_amount,
    COUNT(CASE WHEN ct.card_fraud_flag THEN 1 END) AS fraud_count,
    COUNT(CASE WHEN ct.transaction_status = 'FAILED' THEN 1 END) AS failed_count,
    AVG(ct.card_fee_amount) AS avg_transaction_amount,
    MIN(ct.card_fee_amount) AS min_transaction_amount,
    MAX(ct.card_fee_amount) AS max_transaction_amount
FROM card_transaction ct
    JOIN card c ON c.card_id = ct.card_id
GROUP BY
    DATE(ct.card_transaction_timestamp),
    c.card_type,
    ct.transaction_type
WITH DATA;

CREATE UNIQUE INDEX idx_card_mv_daily_transaction_summary
    ON card_mv_daily_transaction_summary(transaction_date, card_type, transaction_type);

-- Monthly Card Usage Metrics
CREATE MATERIALIZED VIEW card_mv_monthly_card_metrics AS
SELECT
    DATE_TRUNC('month', ct.card_transaction_timestamp) AS month,
    c.card_type,
    COUNT(DISTINCT ct.card_id) AS active_cards,
    COUNT(*) AS total_transactions,
    SUM(ct.card_fee_amount) AS total_amount,
    SUM(ct.card_fee_amount) / COUNT(DISTINCT ct.card_id) AS avg_amount_per_card,
    COUNT(*) / COUNT(DISTINCT ct.card_id)::float AS avg_transactions_per_card,
    COUNT(DISTINCT CASE WHEN ct.card_present_flag THEN ct.card_id END) AS physical_usage_cards,
    COUNT(DISTINCT CASE WHEN NOT ct.card_present_flag THEN ct.card_id END) AS online_usage_cards
FROM card_transaction ct
    JOIN card c ON c.card_id = ct.card_id
GROUP BY
    DATE_TRUNC('month', ct.card_transaction_timestamp),
    c.card_type
WITH DATA;

CREATE UNIQUE INDEX idx_card_mv_monthly_card_metrics
    ON card_mv_monthly_card_metrics(month, card_type);

-- Function to refresh basic analytics (internal)
CREATE OR REPLACE FUNCTION refresh_card_basic_analytics()
RETURNS void AS $$
BEGIN
    REFRESH MATERIALIZED VIEW CONCURRENTLY card_mv_daily_transaction_summary;
    REFRESH MATERIALIZED VIEW CONCURRENTLY card_mv_monthly_card_metrics;
END;
$$ LANGUAGE plpgsql;

-- Create trigger function
CREATE OR REPLACE FUNCTION trg_refresh_card_basic_analytics()
RETURNS TRIGGER AS $$
BEGIN
    PERFORM refresh_card_basic_analytics();
RETURN NULL;
END;
$$ LANGUAGE plpgsql;

-- Drop existing trigger if exists
DROP TRIGGER IF EXISTS trg_refresh_card_basic_analytics ON card_transaction;

-- Create trigger for basic analytics refresh
CREATE TRIGGER trg_refresh_card_basic_analytics
    AFTER INSERT OR UPDATE OR DELETE ON card_transaction
    FOR EACH STATEMENT
    WHEN (pg_trigger_depth() = 0)
    EXECUTE FUNCTION trg_refresh_card_basic_analytics();
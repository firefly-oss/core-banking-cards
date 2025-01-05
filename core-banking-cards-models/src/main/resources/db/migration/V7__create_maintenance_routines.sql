-- V8__create_basic_analytics.sql
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
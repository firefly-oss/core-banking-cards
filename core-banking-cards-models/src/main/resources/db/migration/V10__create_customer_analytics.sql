-- V10__create_customer_analytics.sql
-- Customer Segmentation View
CREATE MATERIALIZED VIEW card_mv_customer_segments AS
SELECT
    c.card_id,
    c.card_type,
    COUNT(ct.card_transaction_id) as total_transactions,
    SUM(ct.card_fee_amount) as total_spend,
    AVG(ct.card_fee_amount) as avg_transaction_amount,
    COUNT(DISTINCT DATE_TRUNC('month', ct.card_transaction_timestamp)) as active_months,
    -- RFM Scoring
    CURRENT_DATE - MAX(DATE(ct.card_transaction_timestamp)) as days_since_last_transaction,
    COUNT(*) / NULLIF(COUNT(DISTINCT DATE_TRUNC('month', ct.card_transaction_timestamp)), 0) as frequency_per_month,
    SUM(ct.card_fee_amount) / NULLIF(COUNT(DISTINCT DATE_TRUNC('month', ct.card_transaction_timestamp)), 0) as monetary_per_month,
    -- Segment Classification
    CASE
        WHEN SUM(ct.card_fee_amount) > 10000 AND COUNT(*) > 50 THEN 'HIGH_VALUE'
        WHEN SUM(ct.card_fee_amount) > 5000 AND COUNT(*) > 25 THEN 'MEDIUM_VALUE'
        WHEN SUM(ct.card_fee_amount) > 0 THEN 'LOW_VALUE'
        ELSE 'INACTIVE'
        END as customer_segment
FROM card c
         LEFT JOIN card_transaction ct ON c.card_id = ct.card_id
GROUP BY
    c.card_id,
    c.card_type
    WITH DATA;

-- Card Lifecycle Analysis
CREATE MATERIALIZED VIEW card_mv_lifecycle_analysis AS
SELECT
    c.card_id,
    c.card_type,
    c.issuance_date,
    c.expiration_date,
    CURRENT_DATE - c.issuance_date as days_since_issuance,
    c.expiration_date - CURRENT_DATE as days_until_expiration,
    MIN(ct.card_transaction_timestamp) as first_transaction_date,
    MAX(ct.card_transaction_timestamp) as last_transaction_date,
    COUNT(ct.card_transaction_id) as total_transactions,
    COUNT(DISTINCT DATE_TRUNC('month', ct.card_transaction_timestamp)) as active_months,
    -- Lifecycle Stage
    CASE
        WHEN c.card_status = 'EXPIRED' THEN 'EXPIRED'
        WHEN c.card_status = 'CANCELLED' THEN 'CANCELLED'
        WHEN c.card_status = 'BLOCKED' THEN 'BLOCKED'
        WHEN MAX(ct.card_transaction_timestamp) < CURRENT_DATE - INTERVAL '3 months' THEN 'DORMANT'
        WHEN COUNT(ct.card_transaction_id) > 0 THEN 'ACTIVE'
        ELSE 'NEW'
END as lifecycle_stage,
    -- Usage Trend
    CASE
        WHEN COUNT(ct.card_transaction_id) FILTER (WHERE ct.card_transaction_timestamp >= CURRENT_DATE - INTERVAL '3 months') >
             COUNT(ct.card_transaction_id) FILTER (WHERE ct.card_transaction_timestamp >= CURRENT_DATE - INTERVAL '6 months'
                                                   AND ct.card_transaction_timestamp < CURRENT_DATE - INTERVAL '3 months')
        THEN 'INCREASING'
        WHEN COUNT(ct.card_transaction_id) FILTER (WHERE ct.card_transaction_timestamp >= CURRENT_DATE - INTERVAL '3 months') <
             COUNT(ct.card_transaction_id) FILTER (WHERE ct.card_transaction_timestamp >= CURRENT_DATE - INTERVAL '6 months'
                                                   AND ct.card_transaction_timestamp < CURRENT_DATE - INTERVAL '3 months')
        THEN 'DECREASING'
        ELSE 'STABLE'
END as usage_trend
FROM card c
LEFT JOIN card_transaction ct ON c.card_id = ct.card_id
GROUP BY
    c.card_id,
    c.card_type,
    c.issuance_date,
    c.expiration_date,
    c.card_status
WITH DATA;
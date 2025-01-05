-- V11__create_performance_analytics.sql
-- Performance Metrics View
CREATE MATERIALIZED VIEW card_mv_performance_metrics AS
SELECT
    DATE_TRUNC('month', ct.card_transaction_timestamp) AS month,
    c.card_type,
    -- Transaction Success Metrics
    COUNT(*) as total_attempts,
    COUNT(CASE WHEN ct.transaction_status = 'COMPLETED' THEN 1 END) as successful_transactions,
    ROUND((COUNT(CASE WHEN ct.transaction_status = 'COMPLETED' THEN 1 END)::numeric /
           NULLIF(COUNT(*), 0) * 100), 2) as success_rate,
    -- Financial Metrics
    SUM(CASE WHEN ct.transaction_status = 'COMPLETED' THEN ct.card_fee_amount ELSE 0 END) as total_processed_amount,
    COUNT(DISTINCT ct.card_id) as active_cards,
    SUM(CASE WHEN ct.transaction_status = 'COMPLETED' THEN ct.card_fee_amount ELSE 0 END) /
        NULLIF(COUNT(DISTINCT ct.card_id), 0) as average_amount_per_card,
    -- Fraud Metrics
    COUNT(CASE WHEN ct.card_fraud_flag THEN 1 END) as fraud_cases,
    ROUND((COUNT(CASE WHEN ct.card_fraud_flag THEN 1 END)::numeric /
           NULLIF(COUNT(*), 0) * 100), 4) as fraud_rate,
    SUM(CASE WHEN ct.card_fraud_flag THEN ct.card_fee_amount ELSE 0 END) as fraud_amount,
    -- Channel Metrics
    COUNT(CASE WHEN ct.card_present_flag THEN 1 END) as card_present_transactions,
    COUNT(CASE WHEN NOT ct.card_present_flag THEN 1 END) as card_not_present_transactions,
    -- Error Metrics
    COUNT(CASE WHEN ct.transaction_status = 'FAILED' THEN 1 END) as failed_transactions,
    ROUND((COUNT(CASE WHEN ct.transaction_status = 'FAILED' THEN 1 END)::numeric /
           NULLIF(COUNT(*), 0) * 100), 2) as failure_rate,
    -- Performance Indicators
    COUNT(*) / NULLIF(COUNT(DISTINCT DATE(ct.card_transaction_timestamp)), 0) as avg_daily_transactions,
    SUM(ct.card_fee_amount) / NULLIF(COUNT(DISTINCT DATE(ct.card_transaction_timestamp)), 0) as avg_daily_amount,
    COUNT(DISTINCT ct.card_merchant_category_code) as unique_merchant_categories,
    -- Time-based Metrics
    COUNT(CASE WHEN EXTRACT(DOW FROM ct.card_transaction_timestamp) IN (0, 6) THEN 1 END) as weekend_transactions,
    COUNT(CASE WHEN EXTRACT(DOW FROM ct.card_transaction_timestamp) NOT IN (0, 6) THEN 1 END) as weekday_transactions,
    COUNT(CASE WHEN EXTRACT(HOUR FROM ct.card_transaction_timestamp) BETWEEN 9 AND 17 THEN 1 END) as business_hours_transactions,
    COUNT(CASE WHEN EXTRACT(HOUR FROM ct.card_transaction_timestamp) NOT BETWEEN 9 AND 17 THEN 1 END) as off_hours_transactions
FROM card_transaction ct
    JOIN card c ON c.card_id = ct.card_id
GROUP BY
    DATE_TRUNC('month', ct.card_transaction_timestamp),
    c.card_type
WITH DATA;

CREATE UNIQUE INDEX idx_card_mv_performance_metrics
    ON card_mv_performance_metrics(month, card_type);

-- Merchant Performance Analysis
CREATE MATERIALIZED VIEW card_mv_merchant_performance AS
SELECT
    ct.card_merchant_category_code,
    ct.card_merchant_name,
    DATE_TRUNC('month', ct.card_transaction_timestamp) AS month,
    COUNT(*) as total_transactions,
    COUNT(DISTINCT ct.card_id) as unique_cards,
    SUM(ct.card_fee_amount) as total_amount,
    AVG(ct.card_fee_amount) as avg_transaction_amount,
    STDDEV(ct.card_fee_amount) as stddev_transaction_amount,
    -- Success Metrics
    COUNT(CASE WHEN ct.transaction_status = 'COMPLETED' THEN 1 END) as successful_transactions,
    ROUND((COUNT(CASE WHEN ct.transaction_status = 'COMPLETED' THEN 1 END)::numeric /
           NULLIF(COUNT(*), 0) * 100), 2) as success_rate,
    -- Risk Metrics
    COUNT(CASE WHEN ct.card_fraud_flag THEN 1 END) as fraud_cases,
    ROUND((COUNT(CASE WHEN ct.card_fraud_flag THEN 1 END)::numeric /
           NULLIF(COUNT(*), 0) * 100), 4) as fraud_rate,
    -- Channel Distribution
    COUNT(CASE WHEN ct.card_present_flag THEN 1 END) as card_present_count,
    COUNT(CASE WHEN NOT ct.card_present_flag THEN 1 END) as card_not_present_count,
    -- Performance Score (example scoring)
    (
        (COUNT(CASE WHEN ct.transaction_status = 'COMPLETED' THEN 1 END)::numeric / NULLIF(COUNT(*), 0) * 50) +
        (100 - (COUNT(CASE WHEN ct.card_fraud_flag THEN 1 END)::numeric / NULLIF(COUNT(*), 0) * 100)) * 0.5
    ) as performance_score
FROM card_transaction ct
GROUP BY
    ct.card_merchant_category_code,
    ct.card_merchant_name,
    DATE_TRUNC('month', ct.card_transaction_timestamp)
WITH DATA;

CREATE UNIQUE INDEX idx_card_mv_merchant_performance
    ON card_mv_merchant_performance(card_merchant_category_code, month);

-- SLA Monitoring View
CREATE MATERIALIZED VIEW card_mv_sla_monitoring AS
SELECT
    DATE_TRUNC('hour', ct.card_transaction_timestamp) AS hour_bucket,
    c.card_type,
    COUNT(*) as total_transactions,
    AVG(EXTRACT(EPOCH FROM (ct.date_updated - ct.date_created))) as avg_processing_time_seconds,
    -- SLA Categories
    COUNT(CASE
              WHEN EXTRACT(EPOCH FROM (ct.date_updated - ct.date_created)) <= 3 THEN 1
        END) as within_3_seconds,
    COUNT(CASE
              WHEN EXTRACT(EPOCH FROM (ct.date_updated - ct.date_created)) > 3
                  AND EXTRACT(EPOCH FROM (ct.date_updated - ct.date_created)) <= 10 THEN 1
        END) as within_10_seconds,
    COUNT(CASE
              WHEN EXTRACT(EPOCH FROM (ct.date_updated - ct.date_created)) > 10 THEN 1
        END) as over_10_seconds,
    -- SLA Compliance Rate
    ROUND((COUNT(CASE
                     WHEN EXTRACT(EPOCH FROM (ct.date_updated - ct.date_created)) <= 3 THEN 1
        END)::numeric / NULLIF(COUNT(*), 0) * 100), 2) as sla_compliance_rate,
    -- Error Rates
    COUNT(CASE WHEN ct.transaction_status = 'FAILED' THEN 1 END) as errors,
    ROUND((COUNT(CASE WHEN ct.transaction_status = 'FAILED' THEN 1 END)::numeric /
           NULLIF(COUNT(*), 0) * 100), 2) as error_rate
FROM card_transaction ct
         JOIN card c ON c.card_id = ct.card_id
GROUP BY
    DATE_TRUNC('hour', ct.card_transaction_timestamp),
    c.card_type
    WITH DATA;

CREATE UNIQUE INDEX idx_card_mv_sla_monitoring
    ON card_mv_sla_monitoring(hour_bucket, card_type);

-- Function to refresh performance analytics
CREATE OR REPLACE FUNCTION refresh_performance_analytics()
RETURNS void AS $$
BEGIN
    REFRESH MATERIALIZED VIEW CONCURRENTLY card_mv_performance_metrics;
    REFRESH MATERIALIZED VIEW CONCURRENTLY card_mv_merchant_performance;
    REFRESH MATERIALIZED VIEW CONCURRENTLY card_mv_sla_monitoring;
END;
$$ LANGUAGE plpgsql;

-- Create trigger function for analytics refresh
CREATE OR REPLACE FUNCTION trg_refresh_performance_analytics()
RETURNS TRIGGER AS $$
BEGIN
    PERFORM refresh_performance_analytics();
RETURN NULL;
END;
$$ LANGUAGE plpgsql;

-- Create trigger
CREATE TRIGGER trg_refresh_performance_analytics
    AFTER INSERT OR UPDATE OR DELETE ON card_transaction
    FOR EACH STATEMENT
    WHEN (pg_trigger_depth() = 0)
    EXECUTE FUNCTION trg_refresh_performance_analytics();
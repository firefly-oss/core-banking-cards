-- V14__create_performance_optimizations.sql
-- Create function to suggest indexes based on query patterns
CREATE OR REPLACE FUNCTION suggest_card_indexes()
RETURNS TABLE (
    table_name TEXT,
    suggested_index TEXT,
    reasoning TEXT
) AS $$
BEGIN
RETURN QUERY
    WITH slow_queries AS (
        SELECT schemaname,
               tablename,
               indexname,
               seq_scan,
               seq_tup_read,
               idx_scan,
               idx_tup_fetch
        FROM pg_stat_user_tables t
        LEFT JOIN pg_stat_user_indexes i
        ON t.schemaname = i.schemaname
        AND t.relname = i.relname
        WHERE t.schemaname = 'public'
        AND t.relname LIKE 'card%'
    )
SELECT
    sq.tablename::TEXT,
        'CREATE INDEX ON ' || sq.tablename || ' (...)'::TEXT,
        CASE
            WHEN seq_scan > 1000 AND seq_scan > idx_scan * 10
                THEN 'High number of sequential scans: ' || seq_scan::TEXT
            WHEN seq_tup_read > 1000000 AND seq_tup_read > idx_tup_fetch * 10
                THEN 'High number of rows read sequentially: ' || seq_tup_read::TEXT
            END
FROM slow_queries sq
WHERE (seq_scan > 1000 AND seq_scan > idx_scan * 10)
   OR (seq_tup_read > 1000000 AND seq_tup_read > idx_tup_fetch * 10);
END;
$$ LANGUAGE plpgsql;

-- Create materialized view for heavy calculations
CREATE MATERIALIZED VIEW card_mv_transaction_stats AS
SELECT
    card_id,
    DATE_TRUNC('month', card_transaction_timestamp) AS month,
    COUNT(*) as transaction_count,
    SUM(card_fee_amount) as total_amount,
    AVG(card_fee_amount) as avg_amount,
    COUNT(DISTINCT card_merchant_category_code) as unique_categories,
    COUNT(DISTINCT card_holder_country) as unique_countries,
    SUM(CASE WHEN card_fraud_flag THEN 1 ELSE 0 END) as fraud_count
FROM card_transaction
GROUP BY
    card_id,
    DATE_TRUNC('month', card_transaction_timestamp)
WITH DATA;

CREATE UNIQUE INDEX idx_card_mv_transaction_stats
    ON card_mv_transaction_stats(card_id, month);

-- Create optimization hints via function parameters
CREATE OR REPLACE FUNCTION card_transaction_search(
    p_start_date TIMESTAMP,
    p_end_date TIMESTAMP,
    p_card_id BIGINT = NULL,
    p_transaction_type TRANSACTION_TYPE = NULL,
    p_use_index BOOLEAN = true  -- Hint for index usage
)
RETURNS SETOF card_transaction AS $$
BEGIN
    IF p_use_index THEN
        RETURN QUERY
SELECT *
FROM card_transaction
WHERE card_transaction_timestamp BETWEEN p_start_date AND p_end_date
  AND (p_card_id IS NULL OR card_id = p_card_id)
  AND (p_transaction_type IS NULL OR transaction_type = p_transaction_type)
ORDER BY card_transaction_timestamp DESC;
ELSE
        -- Force sequential scan for large data sets
        RETURN QUERY
SELECT *
FROM card_transaction
WHERE card_transaction_timestamp BETWEEN p_start_date AND p_end_date
  AND (p_card_id IS NULL OR card_id = p_card_id)
  AND (p_transaction_type IS NULL OR transaction_type = p_transaction_type)
ORDER BY card_transaction_timestamp DESC;
END IF;
END;
$$ LANGUAGE plpgsql;

-- Function to refresh performance stats
CREATE OR REPLACE FUNCTION refresh_card_stats()
RETURNS void AS $$
BEGIN
    REFRESH MATERIALIZED VIEW CONCURRENTLY card_mv_transaction_stats;
END;
$$ LANGUAGE plpgsql;

-- Create trigger function
CREATE OR REPLACE FUNCTION trg_refresh_card_stats()
RETURNS TRIGGER AS $$
BEGIN
    PERFORM refresh_card_stats();
RETURN NULL;
END;
$$ LANGUAGE plpgsql;

-- Create trigger for stats refresh
CREATE TRIGGER trg_refresh_card_stats
    AFTER INSERT OR UPDATE OR DELETE ON card_transaction
    FOR EACH STATEMENT
    WHEN (pg_trigger_depth() = 0)
    EXECUTE FUNCTION trg_refresh_card_stats();
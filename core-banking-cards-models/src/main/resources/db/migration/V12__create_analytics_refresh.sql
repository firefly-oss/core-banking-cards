-- V12__create_analytics_refresh.sql
-- Master refresh function for all analytics
CREATE OR REPLACE FUNCTION refresh_all_card_analytics()
RETURNS void AS $$
BEGIN
    -- Basic Analytics
    REFRESH MATERIALIZED VIEW CONCURRENTLY card_mv_daily_transaction_summary;
    REFRESH MATERIALIZED VIEW CONCURRENTLY card_mv_monthly_card_metrics;

    -- Risk Analytics
    REFRESH MATERIALIZED VIEW CONCURRENTLY card_mv_enhanced_risk_scoring;

    -- Customer Analytics
    REFRESH MATERIALIZED VIEW CONCURRENTLY card_mv_customer_segments;
    REFRESH MATERIALIZED VIEW CONCURRENTLY card_mv_lifecycle_analysis;

    -- Performance Analytics
    REFRESH MATERIALIZED VIEW CONCURRENTLY card_mv_performance_metrics;
    REFRESH MATERIALIZED VIEW CONCURRENTLY card_mv_merchant_performance;
    REFRESH MATERIALIZED VIEW CONCURRENTLY card_mv_sla_monitoring;
END;
$$ LANGUAGE plpgsql;

-- Function to check analytics health
CREATE OR REPLACE FUNCTION check_analytics_health()
RETURNS TABLE (
    view_name TEXT,
    last_refresh TIMESTAMP,
    row_count BIGINT,
    size_bytes BIGINT,
    status TEXT
) AS $$
BEGIN
RETURN QUERY
SELECT
    schemaname || '.' || matviewname as view_name,
    last_refresh::TIMESTAMP,
        n_live_tup::BIGINT as row_count,
        pg_total_relation_size(schemaname || '.' || matviewname)::BIGINT as size_bytes,
        CASE
            WHEN last_refresh < NOW() - INTERVAL '1 hour' THEN 'NEEDS_REFRESH'
        WHEN n_dead_tup > n_live_tup * 0.1 THEN 'NEEDS_VACUUM'
        ELSE 'HEALTHY'
END as status
    FROM pg_matviews
    JOIN pg_stat_matviews ON pg_matviews.matviewname = pg_stat_matviews.matviewname
    WHERE schemaname = 'public' AND pg_matviews.matviewname LIKE 'card_mv_%'
    ORDER BY last_refresh;
END;
$$ LANGUAGE plpgsql;

-- Create master trigger for analytics refresh
CREATE OR REPLACE FUNCTION trg_refresh_all_analytics()
RETURNS TRIGGER AS $$
BEGIN
    -- Only refresh if we've accumulated enough changes
    IF EXISTS (
        SELECT 1
        FROM card_transaction
        WHERE date_created >= (SELECT last_refresh FROM pg_stat_matviews WHERE matviewname = 'card_mv_daily_transaction_summary')
        HAVING COUNT(*) >= 1000
    ) THEN
        PERFORM refresh_all_card_analytics();
END IF;
RETURN NULL;
END;
$$ LANGUAGE plpgsql;

-- Create master trigger
CREATE TRIGGER trg_refresh_all_analytics
    AFTER INSERT OR UPDATE OR DELETE ON card_transaction
    FOR EACH STATEMENT
    WHEN (pg_trigger_depth() = 0)
    EXECUTE FUNCTION trg_refresh_all_analytics();

-- Create analytics maintenance function
CREATE OR REPLACE FUNCTION maintain_analytics()
RETURNS void AS $$
DECLARE
v_view RECORD;
BEGIN
    -- Vacuum and analyze all materialized views
FOR v_view IN
SELECT schemaname, matviewname
FROM pg_matviews
WHERE schemaname = 'public'
  AND matviewname LIKE 'card_mv_%'
    LOOP
        EXECUTE format('VACUUM ANALYZE %I.%I', v_view.schemaname, v_view.matviewname);
END LOOP;

    -- Refresh views that haven't been refreshed in the last hour
FOR v_view IN
SELECT matviewname
FROM pg_stat_matviews
WHERE schemaname = 'public'
  AND matviewname LIKE 'card_mv_%'
  AND (last_refresh IS NULL OR last_refresh < NOW() - INTERVAL '1 hour')
    LOOP
        EXECUTE format('REFRESH MATERIALIZED VIEW CONCURRENTLY %I', v_view.matviewname);
END LOOP;
END;
$$ LANGUAGE plpgsql;
-- V4__create_card_partitions.sql
-- Function to create new partitions automatically
CREATE OR REPLACE FUNCTION create_transaction_partition()
RETURNS TRIGGER AS $$
DECLARE
partition_date DATE;
    partition_name TEXT;
    start_date TIMESTAMP;
    end_date TIMESTAMP;
BEGIN
    partition_date := DATE_TRUNC('month', NEW.card_transaction_timestamp);
    partition_name := 'card_transaction_' || TO_CHAR(partition_date, 'YYYY_MM');
    start_date := partition_date;
    end_date := partition_date + INTERVAL '1 month';

    -- Check if partition exists
    IF NOT EXISTS (
        SELECT 1
        FROM pg_class c
        JOIN pg_namespace n ON n.oid = c.relnamespace
        WHERE c.relname = partition_name
        AND n.nspname = 'public'
    ) THEN
        -- Create new partition
        EXECUTE format(
            'CREATE TABLE IF NOT EXISTS %I PARTITION OF card_transaction
             FOR VALUES FROM (%L) TO (%L)',
            partition_name, start_date, end_date
        );

        -- Create indexes for the new partition
EXECUTE format(
        'CREATE INDEX IF NOT EXISTS %I ON %I (card_id, transaction_type)',
        'idx_' || partition_name || '_card_type',
        partition_name
        );

EXECUTE format(
        'CREATE INDEX IF NOT EXISTS %I ON %I (card_transaction_timestamp)',
        'idx_' || partition_name || '_timestamp',
        partition_name
        );
END IF;

RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Create trigger for automatic partition creation
CREATE TRIGGER tr_create_transaction_partition
    BEFORE INSERT ON card_transaction
    FOR EACH ROW
    EXECUTE FUNCTION create_transaction_partition();

-- Create initial partitions for current and next month
DO $$
DECLARE
start_date DATE;
BEGIN
    -- Current month partition
    start_date := DATE_TRUNC('month', CURRENT_DATE);
EXECUTE format(
        'CREATE TABLE IF NOT EXISTS card_transaction_%s PARTITION OF card_transaction
         FOR VALUES FROM (%L) TO (%L)',
        TO_CHAR(start_date, 'YYYY_MM'),
        start_date,
        start_date + INTERVAL '1 month'
        );

-- Next month partition
start_date := start_date + INTERVAL '1 month';
EXECUTE format(
        'CREATE TABLE IF NOT EXISTS card_transaction_%s PARTITION OF card_transaction
         FOR VALUES FROM (%L) TO (%L)',
        TO_CHAR(start_date, 'YYYY_MM'),
        start_date,
        start_date + INTERVAL '1 month'
        );
END $$;

-- Function to manage old partitions
CREATE OR REPLACE FUNCTION manage_old_partitions(p_months_to_keep INTEGER)
RETURNS void AS $$
DECLARE
partition_table RECORD;
    cutoff_date DATE;
BEGIN
    cutoff_date := DATE_TRUNC('month', CURRENT_DATE - (p_months_to_keep || ' months')::INTERVAL);

FOR partition_table IN
SELECT tablename
FROM pg_tables
WHERE tablename LIKE 'card_transaction_%'
  AND schemaname = 'public'
  AND TO_DATE(SUBSTRING(tablename FROM 'card_transaction_(.*)'), 'YYYY_MM') < cutoff_date
    LOOP
        EXECUTE format('DROP TABLE IF EXISTS %I', partition_table.tablename);
END LOOP;
END;
$$ LANGUAGE plpgsql;

-- Create monitoring view for partitions
CREATE OR REPLACE VIEW vw_partition_info AS
SELECT
    schemaname,
    tablename,
    pg_size_pretty(pg_total_relation_size(schemaname || '.' || tablename)) as partition_size,
    pg_total_relation_size(schemaname || '.' || tablename) as partition_size_bytes
FROM pg_tables
WHERE tablename LIKE 'card_transaction_%'
  AND schemaname = 'public'
ORDER BY tablename;

-- Create function to show partition ranges
CREATE OR REPLACE FUNCTION show_partition_ranges()
RETURNS TABLE (
    partition_name TEXT,
    range_start TIMESTAMP,
    range_end TIMESTAMP
) AS $$
BEGIN
RETURN QUERY
SELECT
    c.relname::TEXT as partition_name,
        CASE
            WHEN min.minimum IS NOT NULL THEN min.minimum::TIMESTAMP
            ELSE NULL
END as range_start,
        CASE
            WHEN max.maximum IS NOT NULL THEN max.maximum::TIMESTAMP
            ELSE NULL
END as range_end
    FROM pg_class c
    JOIN pg_namespace n ON n.oid = c.relnamespace
    LEFT JOIN pg_partitioned_table p ON c.oid = p.partrelid
    LEFT JOIN LATERAL (
        SELECT MIN(card_transaction_timestamp) as minimum
        FROM ONLY pg_catalog.pg_class
    ) min ON TRUE
    LEFT JOIN LATERAL (
        SELECT MAX(card_transaction_timestamp) as maximum
        FROM ONLY pg_catalog.pg_class
    ) max ON TRUE
    WHERE c.relname LIKE 'card_transaction_%'
    AND n.nspname = 'public'
    ORDER BY c.relname;
END;
$$ LANGUAGE plpgsql;
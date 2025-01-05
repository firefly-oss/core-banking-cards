-- V6__create_audit_trail.sql
-- Create audit log table
CREATE TABLE card_audit_log (
                                audit_id BIGSERIAL PRIMARY KEY,
                                table_name TEXT NOT NULL,
                                operation CHAR(1) NOT NULL, -- I=Insert, U=Update, D=Delete
                                record_id BIGINT NOT NULL,
                                column_name TEXT,
                                old_value TEXT,
                                new_value TEXT,
                                changed_by TEXT NOT NULL,
                                changed_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                client_ip TEXT,
                                user_agent TEXT
);

-- Create audit tables for storing full record history
CREATE TABLE card_history (
                              LIKE card INCLUDING ALL,
                              valid_from TIMESTAMP NOT NULL,
                              valid_to TIMESTAMP,
                              changed_by TEXT NOT NULL,
                              operation CHAR(1) NOT NULL
);

CREATE TABLE card_transaction_history (
                                          LIKE card_transaction INCLUDING ALL,
                                          valid_from TIMESTAMP NOT NULL,
                                          valid_to TIMESTAMP,
                                          changed_by TEXT NOT NULL,
                                          operation CHAR(1) NOT NULL
);

-- Function to get session context
CREATE OR REPLACE FUNCTION get_session_context()
RETURNS TABLE (
    username TEXT,
    client_ip TEXT,
    user_agent TEXT
) AS $$
BEGIN
RETURN QUERY
SELECT
            CURRENT_USER::TEXT,
        current_setting('app.client_ip', TRUE),
            current_setting('app.user_agent', TRUE);
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;

-- Generic audit trigger function
CREATE OR REPLACE FUNCTION audit_trigger_func()
RETURNS TRIGGER AS $$
DECLARE
v_old_data JSON;
    v_new_data JSON;
    v_session_data RECORD;
BEGIN
    -- Get session context
SELECT * FROM get_session_context() INTO v_session_data;

IF (TG_OP = 'UPDATE') THEN
        v_old_data = row_to_json(OLD);
        v_new_data = row_to_json(NEW);

        -- Record changed fields
INSERT INTO card_audit_log (
    table_name,
    operation,
    record_id,
    column_name,
    old_value,
    new_value,
    changed_by,
    client_ip,
    user_agent
)
SELECT
    TG_TABLE_NAME::TEXT,
        'U',
    NEW.card_id,
    key,
    v_old_data->key,
    v_new_data->key,
    v_session_data.username,
    v_session_data.client_ip,
    v_session_data.user_agent
FROM json_each_text(v_new_data)
WHERE v_old_data->key IS DISTINCT FROM v_new_data->key;

ELSIF (TG_OP = 'DELETE') THEN
        v_old_data = row_to_json(OLD);

INSERT INTO card_audit_log (
    table_name,
    operation,
    record_id,
    old_value,
    changed_by,
    client_ip,
    user_agent
)
VALUES (
           TG_TABLE_NAME::TEXT,
           'D',
           OLD.card_id,
           v_old_data,
           v_session_data.username,
           v_session_data.client_ip,
           v_session_data.user_agent
       );

ELSIF (TG_OP = 'INSERT') THEN
        v_new_data = row_to_json(NEW);

INSERT INTO card_audit_log (
    table_name,
    operation,
    record_id,
    new_value,
    changed_by,
    client_ip,
    user_agent
)
VALUES (
           TG_TABLE_NAME::TEXT,
           'I',
           NEW.card_id,
           v_new_data,
           v_session_data.username,
           v_session_data.client_ip,
           v_session_data.user_agent
       );
END IF;

RETURN NULL;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;

-- Create history trigger function
CREATE OR REPLACE FUNCTION history_trigger_func()
RETURNS TRIGGER AS $$
DECLARE
v_session_data RECORD;
BEGIN
    -- Get session context
SELECT * FROM get_session_context() INTO v_session_data;

IF (TG_OP = 'UPDATE') THEN
        -- Close current period
UPDATE card_history
SET valid_to = CURRENT_TIMESTAMP
WHERE card_id = OLD.card_id
  AND valid_to IS NULL;

-- Insert new period
INSERT INTO card_history
SELECT
    NEW.*,
    CURRENT_TIMESTAMP,
    NULL,
    v_session_data.username,
    'U';

ELSIF (TG_OP = 'DELETE') THEN
UPDATE card_history
SET valid_to = CURRENT_TIMESTAMP
WHERE card_id = OLD.card_id
  AND valid_to IS NULL;

INSERT INTO card_history
SELECT
    OLD.*,
    CURRENT_TIMESTAMP,
    NULL,
    v_session_data.username,
    'D';

ELSIF (TG_OP = 'INSERT') THEN
        INSERT INTO card_history
SELECT
    NEW.*,
    CURRENT_TIMESTAMP,
    NULL,
    v_session_data.username,
    'I';
END IF;

RETURN NULL;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;

-- Create audit triggers for main tables
CREATE TRIGGER tr_card_audit
    AFTER INSERT OR UPDATE OR DELETE ON card
    FOR EACH ROW EXECUTE FUNCTION audit_trigger_func();

CREATE TRIGGER tr_card_history
    AFTER INSERT OR UPDATE OR DELETE ON card
    FOR EACH ROW EXECUTE FUNCTION history_trigger_func();

CREATE TRIGGER tr_card_transaction_audit
    AFTER INSERT OR UPDATE OR DELETE ON card_transaction
    FOR EACH ROW EXECUTE FUNCTION audit_trigger_func();

-- Create indexes for better audit query performance
CREATE INDEX idx_audit_log_table_record ON card_audit_log(table_name, record_id);
CREATE INDEX idx_audit_log_changed_at ON card_audit_log(changed_at);
CREATE INDEX idx_audit_log_changed_by ON card_audit_log(changed_by);
CREATE INDEX idx_card_history_valid_period ON card_history(valid_from, valid_to);
CREATE INDEX idx_card_transaction_history_valid_period ON card_transaction_history(valid_from, valid_to);
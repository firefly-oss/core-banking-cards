-- A card that has not been issued can be removed. An issued card stays on file.

ALTER TYPE card_status_enum ADD VALUE IF NOT EXISTS 'DRAFT' BEFORE 'ACTIVE';

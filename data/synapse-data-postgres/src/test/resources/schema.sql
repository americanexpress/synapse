DROP SCHEMA IF EXISTS synapse CASCADE;
CREATE SCHEMA synapse;

SET
SCHEMA_SEARCH_PATH TO synapse;

DROP TABLE IF EXISTS product CASCADE;

/*
 * product table create script
 */
create table product
(
    id                      serial           PRIMARY KEY NOT NULL,
    product_name            VARCHAR(150)     NOT NULL,
    created_by              VARCHAR(100),
    last_modified_by        VARCHAR(100),
    created_date_time       TIMESTAMP        DEFAULT current_timestamp,
    last_modified_date_time TIMESTAMP,
    version                 INTEGER          NOT NULL
);

COMMIT;

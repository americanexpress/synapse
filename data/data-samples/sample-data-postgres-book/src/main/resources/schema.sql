DROP SCHEMA IF EXISTS synapse CASCADE;
CREATE SCHEMA synapse;

SET
SCHEMA_SEARCH_PATH TO synapse;

DROP TABLE IF EXISTS book CASCADE;

/*
 * book table create script
 */
create table book
(
    id                      serial           PRIMARY KEY NOT NULL,
    title                   VARCHAR(150)     NOT NULL UNIQUE,--this is the business primary key
    author                  VARCHAR(100),
    created_by              VARCHAR(100),
    last_modified_by        VARCHAR(100),
    created_date_time       TIMESTAMP             DEFAULT current_timestamp,
    last_modified_date_time TIMESTAMP,
    version                 INTEGER               NOT NULL
);

COMMIT;

drop SCHEMA IF EXISTS book_schema CASCADE;
create SCHEMA book_schema;

SET search_path TO book_schema;

drop table IF EXISTS book CASCADE;

/*
 * book table
 */
create TABLE book
(
    id                 SERIAL PRIMARY KEY,
    name               VARCHAR(255) NOT NULL UNIQUE,
    description        TEXT,
    author             VARCHAR(100),
    created_by         VARCHAR(100),
    last_modified_by   VARCHAR(100),
    created_date       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP,
    version            INTEGER      NOT NULL
);

commit;

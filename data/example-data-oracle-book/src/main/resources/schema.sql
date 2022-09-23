drop SCHEMA IF EXISTS synapse CASCADE;
create SCHEMA synapse;

SET
SCHEMA_SEARCH_PATH TO synapse;

drop table IF EXISTS book CASCADE;

/*
 * book table create script
 */
create table book
(
    id                      int primary key,
    title                   varchar(150) not null unique,--this is the business primary key
    author                  varchar(100),
    created_by              varchar(100),
    last_modified_by        varchar(100),
    created_date_time       timestamp             default current_timestamp,
    last_modified_date_time timestamp,
    version                 integer      not null default 0
);

commit;

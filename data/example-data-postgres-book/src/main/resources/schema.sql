drop SCHEMA IF EXISTS synapse CASCADE;
create SCHEMA synapse;

SET
search_path TO synapse;

drop table IF EXISTS book CASCADE;

/*
 * role table
 * Store the roles
 */
create table book
(
    id                 serial primary key,
    title              varchar(150) not null unique,--this is the business primary key
    author             varchar(100),
    created_by         varchar(100),
    last_modified_by   varchar(100),
    created_date       timestamp             default current_timestamp,
    last_modified_date timestamp,
    version            integer      not null default 0
);

commit;

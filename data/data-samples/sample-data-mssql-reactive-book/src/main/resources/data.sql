SET
SCHEMA_SEARCH_PATH TO synapse;
insert into book (id, title, author, created_date_time, last_modified_date_time, created_by,
                    last_modified_by, version)
values (1, 'Synapse', 'Gabriel', now(),
        now(), 'John-Appleseed@email.com', 'John-Appleseed@email.com', 0);
insert into book (id, title, author, created_date_time, last_modified_date_time, created_by,
                    last_modified_by, version)
values (2, 'Revenge of Synapse', 'John', now(),
        now(), 'John-Appleseed@email.com', 'John-Appleseed@email.com', 0);

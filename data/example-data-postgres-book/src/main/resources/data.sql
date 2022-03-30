SET
search_path TO synapse;
insert into book (id, title, author, created_date, last_modified_date, created_by,
                    last_modified_by, version)
values (1, 'White Fang', 'Jack London', now(),
        now(), 'alexei_morgado@yahoo.com', 'alexei_morgado@yahoo.com', 0);
insert into book (id, title, author, created_date, last_modified_date, created_by,
                    last_modified_by, version)
values (2, 'The Lord of the Rings', 'J. R. R. Tolkien', now(),
        now(), 'alexei_morgado@yahoo.com', 'alexei_morgado@yahoo.com', 0);
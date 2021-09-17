SET
search_path TO book_schema;

INSERT INTO sample (id, name, description, author, created_date, last_modified_date, created_by,
                    last_modified_by, version)
VALUES (1, 'Synapse', 'Synapse Book', 'Gabriel', now(), now(), 'johndoe@gmail.com', 'johndoe@gmail.com', 0);
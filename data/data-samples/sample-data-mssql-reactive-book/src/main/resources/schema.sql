USE tempdb;

CREATE TABLE book_entity (
                      id                      INT              IDENTITY(1,1) PRIMARY KEY NOT NULL,
                      title                   NVARCHAR(150)    NOT NULL UNIQUE,
                      author                  NVARCHAR(100),
                      created_by              NVARCHAR(100),
                      last_modified_by        NVARCHAR(100),
                      created_date_time       DATETIME         DEFAULT GETDATE(),
                      last_modified_date_time DATETIME,
                      version                 INT              NOT NULL
);


INSERT INTO book_entity (title, author, created_date_time, last_modified_date_time, created_by, last_modified_by, version)
VALUES ('Synapse', 'Gabriel', GETDATE(), GETDATE(), 'John-Appleseed@email.com', 'John-Appleseed@email.com', 0);

INSERT INTO book_entity (title, author, created_date_time, last_modified_date_time, created_by, last_modified_by, version)
VALUES ('Revenge of Synapse', 'John', GETDATE(), GETDATE(), 'John-Appleseed@email.com', 'John-Appleseed@email.com', 0);

GO

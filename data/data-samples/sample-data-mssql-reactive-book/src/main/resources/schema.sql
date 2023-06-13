-- Copyright 2020 American Express Travel Related Services Company, Inc.
--
-- Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
-- in compliance with the License. You may obtain a copy of the License at
--
-- http://www.apache.org/licenses/LICENSE-2.0
--
-- Unless required by applicable law or agreed to in writing, software distributed under the License
-- is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
-- or implied. See the License for the specific language governing permissions and limitations under
-- the License.
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

CREATE PROCEDURE GET_BOOKS_BY_AUTHOR @author nvarchar(100)
AS
    SELECT * FROM book_entity WHERE author=@author
GO;

EXEC GET_BOOKS_BY_AUTHOR @author = 'Gabriel';

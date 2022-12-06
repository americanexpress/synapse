/*
 * Copyright 2020 American Express Travel Related Services Company, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

DROP SCHEMA IF EXISTS synapse CASCADE;
CREATE SCHEMA synapse;

SET SCHEMA_SEARCH_PATH TO synapse;

DROP TABLE IF EXISTS book_entity CASCADE;

/*
 * book table create script
 */
CREATE TABLE book_entity
(
    id                      INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    title                   VARCHAR(150) NOT NULL UNIQUE,--this is the business primary key
    author                  VARCHAR(100),
    created_by              VARCHAR(100),
    last_modified_by        VARCHAR(100),
    created_date_time       TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    last_modified_date_time TIMESTAMP,
    version                 INTEGER      NOT NULL DEFAULT 0
);

COMMIT;

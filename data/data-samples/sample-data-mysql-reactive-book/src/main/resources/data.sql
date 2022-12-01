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

SET SCHEMA_SEARCH_PATH TO synapse;

INSERT INTO book (id, title, author, created_date_time, last_modified_date_time, created_by,
                    last_modified_by, version)
VALUES (1, 'Synapse', 'Gabriel', now(),
        now(), 'John-Appleseed@email.com', 'John-Appleseed@email.com', 0);
INSERT INTO book (id, title, author, created_date_time, last_modified_date_time, created_by,
                    last_modified_by, version)
VALUES (2, 'Revenge of Synapse', 'John', now(),
        now(), 'John-Appleseed@email.com', 'John-Appleseed@email.com', 0);

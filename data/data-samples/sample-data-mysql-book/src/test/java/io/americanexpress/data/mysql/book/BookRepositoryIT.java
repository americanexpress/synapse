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
package io.americanexpress.data.mysql.book;

import io.americanexpress.data.mysql.book.dao.BookRepository;
import io.americanexpress.data.mysql.book.config.MySqlDataConfigTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * {@code BookRepositoryIT} Integration test example of querying BookEntity by id.
 */
@DataJpaTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MySqlDataConfigTest.class)
public class BookRepositoryIT {

    /**
     * BookRepository used for database access.
     */
    @Autowired
    private BookRepository bookRepository;

    /**
     * Test and finds Synapse record in book table.
     */
    @Test
    public void findById_givenValidId_expectedBookFound() {
        Assertions.assertNotNull(bookRepository.findByTitle("Synapse"));
    }

}

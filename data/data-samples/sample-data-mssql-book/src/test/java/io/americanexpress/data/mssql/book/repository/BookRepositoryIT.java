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
package io.americanexpress.data.mssql.book.repository;

import io.americanexpress.data.mssql.book.config.BookDataTestConfig;
import io.americanexpress.data.mssql.book.entity.BookEntity;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * {@code BookRepositoryIT} tests the {@link BookRepository} with local instance of Mssql database.
 */
@ContextConfiguration(classes = BookDataTestConfig.class)
@ExtendWith(SpringExtension.class)
class BookRepositoryIT {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void findAll_givenBooksInDatabase_expectedBooksReturned() {
        List<BookEntity> bookEntityFlux = bookRepository.findAll();
        assertEquals(bookEntityFlux.size(), 2);
    }

    @Test
    void findAllBookByTitle_givenTitleInDatabase_expectedBookByTitleReturned() {
        BookEntity bookRepositoryByTitle = bookRepository.findByTitle("Revenge of Synapse");
        assertNotNull(bookRepositoryByTitle);
    }
}

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
package io.americanexpress.data.book.dao;

import io.americanexpress.data.book.config.BookDataTestConfig;
import io.americanexpress.data.book.entity.BookEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * BookRepositoryIT class runs integration test on local MongoDB instance test database.
 */
@ContextConfiguration(classes = BookDataTestConfig.class)
@DataMongoTest
class BookRepositoryIT {

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void init() {
        bookRepository.deleteAll();
    }

    @Test
    void findAll_givenEmptyBookCollection_expectedNoBooksFound() {
        Assertions.assertEquals(0, bookRepository.findAll().size());
    }

    @Test
    void findAll_givenBookEntry_expectedOneBookFound() {
        saveSampleBook();
        Assertions.assertEquals(1, bookRepository.findAll().size());
    }

    @Test
    void findByTitle_givenBookTitle_expectedBookFound() {
        saveSampleBook();
        BookEntity found = bookRepository.findByTitle("Alice in Wonderland");
        Assertions.assertAll("Find by title",
                () -> Assertions.assertNotNull(found),
                () -> Assertions.assertEquals("Alice in Wonderland", found.getTitle()));
    }

    void saveSampleBook() {
        BookEntity bookDocument = new BookEntity();
        bookDocument.setTitle("Alice in Wonderland");
        bookDocument.setAuthor("Lewis Carroll");
        bookRepository.save(bookDocument);
    }
}

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
package io.americanexpress.data.book.repository;

import io.americanexpress.data.book.config.BookDataTestConfig;
import io.americanexpress.data.book.entity.BookEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

/**
 * {@code BookRepositoryIT} class runs integration test on local Redis instance test database.
 */
@ContextConfiguration(classes = BookDataTestConfig.class)
@AutoConfigurationPackage
class BookRepositoryIT {

    private final BookRepository bookRepository;

    @Autowired
    public BookRepositoryIT(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @BeforeEach
    void clean() {
        bookRepository.deleteAll();
    }

    @Test
    void findAll_givenEmptyBookCollection_expectedNoBooksFound() {
        Assertions.assertEquals(0, bookRepository.findAll().size());
    }

    @Test
    void findByTitleAndAuthor_givenBook_expectedBookFound() {
        BookEntity bookEntity = new BookEntity("Alice In Wonderland", "Lewis Carroll");
        bookRepository.save(bookEntity);

        Optional<BookEntity> book = bookRepository.findByTitleAndAuthor("Alice In Wonderland", "Lewis Carroll");
        Assertions.assertEquals(bookEntity.getTitle(), book.get().getTitle());
    }

}

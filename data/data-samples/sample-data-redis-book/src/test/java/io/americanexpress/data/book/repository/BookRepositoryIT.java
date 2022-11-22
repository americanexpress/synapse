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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * {@code BookRepositoryIT} class runs integration test on local Redis instance test database.
 */
@ContextConfiguration(classes = BookDataTestConfig.class)
@ExtendWith(SpringExtension.class)
class BookRepositoryIT {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void save_givenValidBook_expectedSavedBookSuccess() {
        UUID id = UUID.randomUUID();
        BookEntity bookEntity = new BookEntity("Alice Wonderland", "Lewis Carroll");
        BookEntity saved = bookRepository.save(bookEntity);
        assertNotNull(saved);
    }

    @Test
    void findByTitleAndAuthor_givenBook_expectedBookFound() {
        BookEntity bookEntity = new BookEntity("Alice In Wonderland", "Lewis Carroll");
        bookEntity = bookRepository.save(bookEntity);

        Optional<BookEntity> book = bookRepository.findById(UUID.fromString(bookEntity.getIdentifier()));
        Assertions.assertEquals(bookEntity.getTitle(), book.get().getTitle());
    }

}

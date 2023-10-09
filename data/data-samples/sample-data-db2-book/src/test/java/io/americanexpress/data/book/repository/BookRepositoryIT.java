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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ContextConfiguration(classes = BookDataTestConfig.class)
@ExtendWith(SpringExtension.class)
class BookRepositoryIT {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void findByTitle_givenNewBookEntity_expectedFound() {
        var bookEntity = new BookEntity();
        bookEntity.setTitle("book title");
        bookEntity.setAuthor("myself");
        bookEntity.setId(1L);

        bookRepository.save(bookEntity);

        assertNotNull(bookRepository.findByTitle("IT"));

        bookRepository.deleteById(bookEntity.getId());
    }

    @Test
    void findByAuthor_givenNewBookEntity_expectedFound() {
        var bookEntity = new BookEntity();
        bookEntity.setAuthor("synapse");
        bookEntity.setTitle("synapse playbook");
        bookEntity.setId(2L);

        bookRepository.save(bookEntity);

        assertNotNull(bookRepository.findByAuthor("synapse"));

        bookRepository.deleteById(bookEntity.getId());
    }
}

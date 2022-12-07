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
import io.americanexpress.data.mysql.book.entity.BookEntity;
import io.americanexpress.data.mysql.book.config.MySqlDataConfigTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * {@code MySqlDataBookIT} MySql data book integration test.
 */
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
        bookRepository.findByTitle("Synapse").subscribe(Assertions::assertNotNull);
    }

    /**
     * Test and finds Synapse record in book table.
     */
    @Test
    public void saveNewTitle_givenValidId_expectedBookFound() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setAuthor("author");
        bookEntity.setTitle("Hello World");
        bookRepository.save(bookEntity).subscribe();
        bookRepository.findByTitle("Hello World").subscribe(Assertions::assertNotNull);
    }

    /**
     * Test and finds Synapse record in book table.
     */
    @Test
    public void updateTitle_givenValidEntity_expectedBookFound() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setAuthor("John Doe");
        bookEntity.setTitle("Hello World");
        bookRepository.save(bookEntity).subscribe();
        bookRepository.findByTitle("Hello World").subscribe(Assertions::assertNotNull);
    }

    /**
     * Test and finds Synapse record in book table.
     */
    @Test
    public void deleteTitle_givenValidTitle_expectedBookNotFound() {
        bookRepository.deleteByTitle("Hello World").subscribe();
        bookRepository.findByTitle("Hello World").subscribe(Assertions::assertNull);
    }


}

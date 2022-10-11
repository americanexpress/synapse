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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * {@code BookRepositoryIT} class runs integration test on local MongoDB instance test database.
 */
@ContextConfiguration(classes = BookDataTestConfig.class)
@DataMongoTest
class BookRepositoryIT {

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void init() {
        bookRepository.deleteAll().block();
    }

    @Test
    void findAll_givenEmptyBookCollection_expectedNoBooksFound() {
        Flux<BookEntity> bookDocumentFlux = bookRepository.findAll();
        StepVerifier.create(bookDocumentFlux).expectNextCount(0).verifyComplete();
    }

    @Test
    void findAll_givenBookCollection_expectedCollectionNotEmpty() {
        BookEntity entry = createSampleBook();
        bookRepository.save(entry).block();
        Flux<BookEntity> bookDocumentFlux = bookRepository.findAll();
        StepVerifier.create(bookDocumentFlux).expectNextCount(1).verifyComplete();
    }

    @Test
    void findByTitle_givenBookTitle_expectedBookFound() {
        BookEntity entry = createSampleBook();
        bookRepository.save(entry).block();
        Flux<BookEntity> bookDocumentFlux = bookRepository.findByTitle("Alice in Wonderland");
        StepVerifier.create(bookDocumentFlux).assertNext(bookEntity -> entry.getTitle().equalsIgnoreCase(bookEntity.getTitle())).expectComplete().verify();
    }

    private BookEntity createSampleBook() {
        BookEntity bookDocument = new BookEntity();
        bookDocument.setTitle("Alice in Wonderland");
        bookDocument.setAuthor("Lewis Carroll");
        return bookDocument;
    }
}

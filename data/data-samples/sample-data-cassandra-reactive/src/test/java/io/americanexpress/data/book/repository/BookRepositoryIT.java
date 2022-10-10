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
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

/**
 * {@code BookRepositoryIT} class runs integration test on local Cassandra instance test database.
 */
@EnableAutoConfiguration
@ContextConfiguration(classes = BookDataTestConfig.class)
@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true")
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
    void findByTitleAndAuthor_givenBook_expectedBookFound() {
        BookEntity bookEntity = new BookEntity("Alice In Wonderland", "Lewis Carroll");
        bookEntity.setIdentifier(UUID.randomUUID());
        bookRepository.save(bookEntity).block();

        Mono<BookEntity> bookEntityMono = bookRepository.findByTitleAndAuthor("Alice In Wonderland", "Lewis Carroll");
        StepVerifier.create(bookEntityMono).assertNext(book -> bookEntity.getTitle().equalsIgnoreCase(book.getTitle())).expectComplete().verify();
    }
}

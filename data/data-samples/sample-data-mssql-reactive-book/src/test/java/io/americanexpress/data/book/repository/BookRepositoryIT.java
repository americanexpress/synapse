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
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * {@code BookRepositoryIT} tests the {@link BookRepository} with local instance of Mssql database.
 */
@ContextConfiguration(classes = BookDataTestConfig.class)
@ExtendWith(SpringExtension.class)
@DataR2dbcTest
class BookRepositoryIT {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void findAll() {
        Flux<BookEntity> bookEntityFlux = bookRepository.findAll();

        StepVerifier.create(bookEntityFlux)
                .expectNextCount(2)
                .expectComplete()
                .verify();
    }

    @Test
    void getTotalBooksByAuthor() {
        Flux<BookEntity> bookEntityFlux = bookRepository.getBooksByAuthor("Lewis Carroll");

        StepVerifier.create(bookEntityFlux)
                .expectNextCount(1L)
                .expectComplete()
                .verify();
    }
}

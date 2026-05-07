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
package io.americanexpress.service.book.rest.integration;

import io.americanexpress.data.book.repository.BookRepository;
import io.americanexpress.service.book.rest.BookApplication;
import io.americanexpress.service.book.rest.model.CreateBookRequest;
import io.americanexpress.service.book.rest.model.ReadBookRequest;
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.service.book.rest.model.UpdateBookRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(classes = BookApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
class BookCrudIT {

    @DynamicPropertySource
    static void props(DynamicPropertyRegistry registry) {
        BookMongoContainer.registerProperties(registry);
    }

    @Autowired
    private WebTestClient web;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void resetCollection() {
        bookRepository.deleteAll().block();
    }

    @Test
    void createReadUpdateDelete_givenValidBook_endToEndAgainstRealMongo() {
        CreateBookRequest create = new CreateBookRequest();
        create.setTitle("Reactive Synapse");
        create.setAuthor("synapse-it");

        web.post().uri("/v1/books")
                .bodyValue(create)
                .exchange()
                .expectStatus().isCreated();

        // BaseReadFluxReactiveController exposes POST /v1/books/multiple_results — Flux response
        ReadBookRequest read = new ReadBookRequest();
        web.post().uri("/v1/books/multiple_results")
                .bodyValue(read)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ReadBookResponse.class)
                .hasSize(1)
                .value(books -> {
                    ReadBookResponse first = books.get(0);
                    org.junit.jupiter.api.Assertions.assertEquals("Reactive Synapse", first.getTitle());
                    org.junit.jupiter.api.Assertions.assertEquals("synapse-it", first.getAuthor());
                });

        UpdateBookRequest update = new UpdateBookRequest();
        update.setTitle("Reactive Synapse");
        update.setAuthor("synapse-it");
        update.setNumberOfCopies(7);
        web.put().uri("/v1/books")
                .bodyValue(update)
                .exchange()
                .expectStatus().is2xxSuccessful();

        // Verify the update landed in the data adapter
        org.junit.jupiter.api.Assertions.assertEquals(
                7,
                bookRepository.findByTitleAndAuthor("Reactive Synapse", "synapse-it")
                        .block()
                        .getNumberOfCopies());

        web.delete().uri("/v1/books/Reactive Synapse")
                .exchange()
                .expectStatus().isNoContent();

        org.junit.jupiter.api.Assertions.assertEquals(0L, bookRepository.count().block());
    }
}

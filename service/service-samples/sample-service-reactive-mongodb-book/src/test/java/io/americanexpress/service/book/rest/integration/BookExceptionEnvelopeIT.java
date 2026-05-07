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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(classes = BookApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
class BookExceptionEnvelopeIT {

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
    void create_givenBlankRequiredFields_returnsBadRequest() {
        // CreateBookRequest title and author are @NotBlank — sending blanks must be rejected.
        CreateBookRequest invalid = new CreateBookRequest();
        invalid.setTitle("");
        invalid.setAuthor("");

        web.post().uri("/v1/books")
                .bodyValue(invalid)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void create_givenMalformedJson_returnsBadRequest() {
        web.post().uri("/v1/books")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{not-valid-json")
                .exchange()
                .expectStatus().is4xxClientError();
    }
}

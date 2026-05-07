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

import io.americanexpress.service.book.rest.BookApplication;
import io.americanexpress.service.book.rest.model.CreateBookRequest;
import io.americanexpress.service.book.rest.model.ReadBookRequest;
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = BookApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration-test")
class BookCrudIT {

    @DynamicPropertySource
    static void props(DynamicPropertyRegistry registry) {
        BookPostgresContainer.registerProperties(registry);
    }

    @Autowired
    private TestRestTemplate http;

    @Test
    void createReadDelete_givenValidBook_endToEndAgainstRealPostgres() {
        CreateBookRequest create = new CreateBookRequest();
        create.setTitle("Synapse Crud Lifecycle");
        create.setAuthor("synapse-it");

        ResponseEntity<Void> created = http.postForEntity("/v1/books", create, Void.class);
        assertEquals(HttpStatus.CREATED, created.getStatusCode());
        assertNotNull(created.getHeaders().getLocation(), "BaseCreateController must emit Location");
        assertTrue(created.getHeaders().getLocation().toString().endsWith("/v1/books/0"),
                "CreateBookResponse has no id, so synapse falls back to /0");

        ReadBookRequest read = new ReadBookRequest();
        read.setTitle("Synapse Crud Lifecycle");
        read.setAuthor("synapse-it");
        ResponseEntity<ReadBookResponse> readResp = http.postForEntity(
                "/v1/books/inquiry_results", read, ReadBookResponse.class);
        assertEquals(HttpStatus.OK, readResp.getStatusCode());
        assertNotNull(readResp.getBody());
        assertEquals("Synapse Crud Lifecycle", readResp.getBody().getTitle());
        assertEquals("synapse-it", readResp.getBody().getAuthor());

        // BaseDeleteController exposes DELETE /v1/books/{identifier}; DeleteBookService treats
        // the identifier as a title, so we delete by the book's title.
        ResponseEntity<Void> deleted = http.exchange(
                "/v1/books/Synapse Crud Lifecycle",
                org.springframework.http.HttpMethod.DELETE,
                null,
                Void.class);
        assertEquals(HttpStatus.NO_CONTENT, deleted.getStatusCode());

        ResponseEntity<ReadBookResponse> readAfter = http.postForEntity(
                "/v1/books/inquiry_results", read, ReadBookResponse.class);
        assertEquals(HttpStatus.NO_CONTENT, readAfter.getStatusCode(),
                "MonoResponseEntityCreator returns 204 when the service produces null");
    }
}

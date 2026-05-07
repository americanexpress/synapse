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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.americanexpress.service.book.rest.BookApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = BookApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
class BookApiDocsIT {

    @DynamicPropertySource
    static void props(DynamicPropertyRegistry registry) {
        BookMongoContainer.registerProperties(registry);
    }

    @Autowired
    private WebTestClient web;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void apiDocs_isServedAndDescribesReactiveBookRoutes() throws Exception {
        byte[] response = web.get().uri("/v3/api-docs")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .returnResult()
                .getResponseBody();
        assertNotNull(response);

        JsonNode root = objectMapper.readTree(response);
        JsonNode paths = root.get("paths");
        assertNotNull(paths, "OpenAPI spec missing 'paths' — synapse-framework-api-docs may not be wired");
        assertTrue(paths.has("/v1/books"), "OpenAPI spec is missing /v1/books");
        assertTrue(paths.has("/v1/books/multiple_results"),
                "OpenAPI spec is missing the multiple_results route declared by BaseReadFluxReactiveController");
    }
}

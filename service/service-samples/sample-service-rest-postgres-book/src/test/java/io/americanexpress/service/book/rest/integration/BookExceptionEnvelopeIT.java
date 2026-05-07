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
import io.americanexpress.service.book.rest.model.CreateBookRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = BookApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration-test")
class BookExceptionEnvelopeIT {

    @DynamicPropertySource
    static void props(DynamicPropertyRegistry registry) {
        BookPostgresContainer.registerProperties(registry);
    }

    @Autowired
    private TestRestTemplate http;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void create_givenBlankRequiredFields_returnsSynapseErrorEnvelope() throws Exception {
        // BookRequest fields title and author are @NotBlank; sending blanks must trigger
        // ControllerExceptionHandler.handleMethodArgumentNotValidException -> ErrorResponse @ 400.
        CreateBookRequest invalid = new CreateBookRequest();
        invalid.setTitle("");
        invalid.setAuthor("");

        ResponseEntity<String> response = http.postForEntity("/v1/books", invalid, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        JsonNode body = objectMapper.readTree(response.getBody());
        assertNotNull(body.get("code"), "ErrorResponse.code missing");
        assertNotNull(body.get("message"), "ErrorResponse.message missing");
        assertNotNull(body.get("moreInfo"), "ErrorResponse.moreInfo missing");
        assertNotNull(body.get("developerMessage"), "ErrorResponse.developerMessage missing");
    }

    @Test
    void create_givenMalformedJson_returnsSynapseErrorEnvelopeFor4xx() throws Exception {
        // Triggers HttpMessageNotReadableException -> handler returns ErrorResponse with the
        // GENERIC_4XX_ERROR code (400-series).
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>("{not-valid-json", headers);

        ResponseEntity<String> response = http.exchange("/v1/books", HttpMethod.POST, request, String.class);
        assertTrue(response.getStatusCode().is4xxClientError(),
                "Expected 4xx, got " + response.getStatusCode());

        JsonNode body = objectMapper.readTree(response.getBody());
        assertNotNull(body.get("code"));
        assertNotNull(body.get("message"));
        assertNotNull(body.get("moreInfo"));
        assertNotNull(body.get("developerMessage"));
    }
}

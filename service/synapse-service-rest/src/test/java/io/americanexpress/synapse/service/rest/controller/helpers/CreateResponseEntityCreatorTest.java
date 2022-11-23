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
package io.americanexpress.synapse.service.rest.controller.helpers;

import io.americanexpress.synapse.service.rest.controller.helpers.model.BaseServiceResponseTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code CreateResponseEntityCreatorTest} Create Response Entity Creator Test
 */
public class CreateResponseEntityCreatorTest {

    /**
     * init
     */
    @BeforeAll
    public static void init() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    /**
     * Test successful create responseEntity.
     */
    @Test
    public void create_givenServiceResponse_expectedResponseEntity() {
        ResponseEntity<BaseServiceResponseTest> response = CreateResponseEntityCreator.create(new BaseServiceResponseTest("test", UUID.randomUUID().toString()));
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(201, response.getStatusCodeValue()),
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode())
        );
    }
}

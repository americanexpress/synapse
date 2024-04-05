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

import java.util.Objects;
import java.util.UUID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code MonoResponseEntityCreatorTest} Mono Response Entity Creator Test
 */
public class MonoResponseEntityCreatorTest {

    /**
     * test successful create responseEntity
     */
    @Test
    public void create_givenServiceResponse_expectedResponseEntity() {
        var response = MonoResponseEntityCreator.create(new BaseServiceResponseTest("test", UUID.randomUUID().toString()));
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(200, response.getStatusCodeValue()),
                () -> assertEquals("test", Objects.requireNonNull(response.getBody()).getValue()),
                () -> assertNotNull(Objects.requireNonNull(response.getBody()).getId())
        );
    }

    /**
     * Test no content responseEntity
     */
    @Test
    public void create_givenServiceResponse_expectedNoContent() {
        var response = MonoResponseEntityCreator.create(null);
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(204, response.getStatusCodeValue())
        );
    }

}

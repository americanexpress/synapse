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
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code PolyResponseEntityCreatorTest} Poly Response Entity Creator Test
 */
public class PolyResponseEntityCreatorTest {

    /**
     * test successful create responseEntity
     */
    @Test
    public void create_givenServiceResponse_expectedResponseEntity() {
        var page = new PageImpl<>(List.of(new BaseServiceResponseTest("test", UUID.randomUUID().toString())));
        var httpServletRequest = new MockHttpServletResponse();
        var responseEntity = PolyResponseEntityCreator.create(page, httpServletRequest);
        assertAll(
                () -> assertNotNull(responseEntity),
                () -> assertNotNull(Objects.requireNonNull(responseEntity.getBody()).get(0).getId()),
                () -> assertEquals(200, responseEntity.getStatusCodeValue()),
                () -> assertEquals("test", Objects.requireNonNull(responseEntity.getBody()).get(0).getValue())
        );
    }

    /**
     * test no content
     */
    @Test
    public void create_givenServiceResponseEmptyPage_expectedResponseEntityNoContent() {
        var httpServletRequest = new MockHttpServletResponse();
        var responseEntity = PolyResponseEntityCreator.create(null, httpServletRequest);
        assertAll(
                () -> assertNotNull(responseEntity),
                () -> assertEquals(204, responseEntity.getStatusCodeValue())
        );
    }
}

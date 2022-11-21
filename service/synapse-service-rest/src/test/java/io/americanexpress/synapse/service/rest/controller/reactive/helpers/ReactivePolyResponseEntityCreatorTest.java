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
package io.americanexpress.synapse.service.rest.controller.reactive.helpers;

import io.americanexpress.synapse.service.rest.controller.helpers.model.BaseServiceResponseTest;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * {@code ReactivePolyResponseEntityCreatorTest} Reactive Poly Response Entity Creator Test
 */
class ReactivePolyResponseEntityCreatorTest {

    /**
     * test successful create responseEntity
     */
    @Test
    public void create_givenServiceResponse_expectedResponseEntity() {
        var page = new PageImpl<>(List.of(new BaseServiceResponseTest("test", "test")));
        var httpServletRequest = new MockHttpServletResponse();
        var responseEntity = ReactivePolyResponseEntityCreator.create(page, httpServletRequest);
        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue());
        Objects.requireNonNull(responseEntity.getBody())
                .subscribe(results -> {
                    assertEquals("test", results.getValue());
                    assertEquals("test", results.getId());
                });
    }
}

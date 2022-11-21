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
import org.junit.Test;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * {@code ReactiveMonoResponseEntityCreatorTest} Reactive Mono Response Entity Creator Test
 */
public class ReactiveMonoResponseEntityCreatorTest {

    /**
     * test successful create responseEntity
     */
    @Test
    public void create_givenServiceResponse_expectedResponseEntity() {
        var response = ReactiveMonoResponseEntityCreator.create(Mono.just(new BaseServiceResponseTest("test", "test")));
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        Objects.requireNonNull(response.getBody())
                .subscribe(results -> {
                    assertEquals("test", results.getValue());
                    assertEquals("test", results.getId());
                });
    }
}

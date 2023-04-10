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
package io.americanexpress.synapse.service.reactive.rest.service;

import io.americanexpress.synapse.service.reactive.rest.model.BaseServiceResponse;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

/**
 * {@code BaseGetMonoReactiveService} class specifies the prototypes for performing business logic.
 * @param <O> service response
 */
public abstract class BaseGetMonoReactiveService<O extends BaseServiceResponse> extends BaseService {

    /**
     * Retrieves one resource.
     * @param headers headers
     * @param identifier an identifier
     * @return a mono read response
     */
    public Mono<O> read(HttpHeaders headers, String identifier) {
        logger.entry(identifier);
        final var response = executeRead(headers, identifier);
        logger.exit();
        return response;
    }

    /**
     * Prototype for reading a resource
     * @param headers headers
     * @param request an identifier
     * @return a mono read response
     */
    protected abstract Mono<O> executeRead(HttpHeaders headers, String request);
}

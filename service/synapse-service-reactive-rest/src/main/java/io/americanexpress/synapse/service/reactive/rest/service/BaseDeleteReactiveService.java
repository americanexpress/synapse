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

import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

/**
 * {@code BaseDeleteReactiveService} class specifies the prototypes for performing business logic.
 */
public abstract class BaseDeleteReactiveService extends BaseService {

    /**
     * Deletes a resource by id.
     *
     * @param headers containing the HTTP headers from the consumer
     * @param identifier an identifier
     * @return a mono void
     */
    public Mono<Void> delete(HttpHeaders headers, String identifier) {
        logger.entry();
        var results = executeDelete(headers, identifier);
        logger.exit();
        return results;
    }

    /**
     * Prototype for deleting a resource.
     *
     * @param headers containing the HTTP headers from the consumer
     * @param identifier an identifier
     * @return a mono void
     */
    protected abstract Mono<Void> executeDelete(HttpHeaders headers, String identifier);
}

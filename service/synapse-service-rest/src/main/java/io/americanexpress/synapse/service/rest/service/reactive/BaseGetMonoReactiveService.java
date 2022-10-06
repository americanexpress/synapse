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
package io.americanexpress.synapse.service.rest.service.reactive;

import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;
import io.americanexpress.synapse.service.rest.service.BaseService;
import reactor.core.publisher.Mono;

/**
 * {@code BaseGetMonoReactiveService} class specifies the prototypes for performing business logic.
 * @param <O>
 */
public abstract class BaseGetMonoReactiveService<O extends BaseServiceResponse> extends BaseService {

    /**
     * Retrieves one resource.
     * @param identifier
     * @return
     */
    public Mono<O> read(String identifier) {
        logger.entry(identifier);
        final var response = executeRead(identifier);
        logger.exit();
        return response;
    }

    protected abstract Mono<O> executeRead(String request);
}

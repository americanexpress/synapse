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
package io.americanexpress.synapse.service.reactive.service;

import io.americanexpress.synapse.service.reactive.model.BaseServiceResponse;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Flux;

/**
 * {@code BaseGetFluxReactiveService} class specifies the prototypes for performing business logic.
 * @param <O> an object extending the {@link BaseServiceResponse}
 * @author Francois Gutt
 */
public abstract class BaseGetFluxReactiveService<O extends BaseServiceResponse> extends BaseService {

    /**
     * Retrieves multiple resource.
     * @param headers headers
     * @return a flux read response
     */
    public Flux<O> read(HttpHeaders headers) {
        logger.entry();
        var response = executeRead(headers);
        logger.exit();
        return response;
    }

    /**
     * Prototype for reading multiple resources.
     * @param headers headers
     * @return a flux read response
     */
    protected abstract Flux<O> executeRead(HttpHeaders headers);
}

package io.americanexpress.synapse.service.reactive.service;

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

import io.americanexpress.synapse.service.reactive.model.BaseServiceRequest;
import io.americanexpress.synapse.service.reactive.model.BaseServiceResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 * {@code BaseCreateReactiveService} specifies the prototypes for performing business logic.
 * @param <I> an object extending the {@link BaseServiceRequest}
 * @param <O> an object extending the {@link BaseServiceResponse}
 * @author Francois Gutt
 */
public abstract class BaseCreateReactiveService<I extends BaseServiceRequest, O extends BaseServiceResponse> extends BaseService {

    /**
     * Add a single resource.
     *
     * @param headers the headers
     * @param request body received from the controller
     * @return response body to the controller
     */
    public Mono<O> create(HttpHeaders headers, I request) {
        logger.entry(request);
        final var response = executeCreate(headers,request);
        logger.exit();
        return response;
    }

    /**
     * Prototype for adding a resource.
     *
     * @param headers the headers
     * @param request body received from the controller
     * @return response body to the controller
     */
    protected abstract Mono<O> executeCreate(HttpHeaders headers,I request);
}

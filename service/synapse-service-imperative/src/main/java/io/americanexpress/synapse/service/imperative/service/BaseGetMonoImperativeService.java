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
package io.americanexpress.synapse.service.imperative.service;

import io.americanexpress.synapse.service.imperative.model.BaseServiceResponse;
import org.springframework.http.HttpHeaders;

/**
 * {@code BaseGetMonoService} class specifies the prototypes for performing business logic.
 * @param <O> BaseServiceResponse
 * @author Francois Gutt
 */
public abstract non-sealed class BaseGetMonoImperativeService<O extends BaseServiceResponse> extends BaseService {

    /**
     * Unique identifier for the resource.
     *
     * @param headers the http headers
     * @return a service response
     */
    public O read(HttpHeaders headers) {
        logger.entry(headers);
        O response = executeRead(headers);
        logger.exit(response);
        return response;
    }

    /**
     * Prototype for reading a resource.
     *
     * @param headers the http headers
     * @return a service response
     */
    protected abstract O executeRead(HttpHeaders headers);
}

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

import io.americanexpress.synapse.service.imperative.model.BaseServiceRequest;
import io.americanexpress.synapse.service.imperative.model.BaseServiceResponse;
import org.springframework.http.HttpHeaders;

/**
 * {@code BaseReadMonoService} class specifies the prototypes for performing business logic.
 * @param <I> class extending the {@link BaseServiceRequest}
 * @param <O> class extending the {@link BaseServiceResponse}
 *
 * @author Francois Gutt
 */
public abstract non-sealed class BaseReadMonoImperativeService<
            I extends BaseServiceRequest,
            O extends BaseServiceResponse
        > extends BaseService {

    /**
     * Get a single resource from the back end service.
     *
     * @param request body received from the controller
     * @return a single resource from the back end service.
     */
    public O read(I request) {
        logger.entry(request);
        final O response = executeRead(request);
        logger.exit(response);
        return response;
    }

    /**
     * Prototype for reading a resource.
     * @param headers the http header map
     * @param request the request
     * @return a read mono response
     */
    protected abstract O executeRead(I request);
}

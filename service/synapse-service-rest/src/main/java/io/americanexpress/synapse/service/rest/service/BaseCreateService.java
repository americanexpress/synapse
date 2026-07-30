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
package io.americanexpress.synapse.service.rest.service;

import io.americanexpress.synapse.service.rest.model.BaseServiceRequest;
import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;
import org.springframework.http.HttpHeaders;

/**
 * {@code BaseCreateService} class specifies the prototypes for performing business logic.
 *
 * @param <I> input request type
 * @param <O> output response type
 * @author Alexei Morgado
 */
public abstract class BaseCreateService<I extends BaseServiceRequest, O extends BaseServiceResponse> extends BaseService {

    /**
     * Add a single resource.
     *
     * @param headers received from the controller
     * @param request body received from the controller
     * @return response body to the controller
     */
    public O create(HttpHeaders headers, I request) {
        logger.entry(request);
        final O response = executeCreate(headers, request);
        logger.exit(response);
        return response;
    }

    /**
     * Prototype for adding a resource.
     * @param headers received from the controller
     * @param request body received from the controller
     * @return response body to the controller
     */
    /**
     * Create the resource. This is the single extension point for create operations.
     *
     * @implSpec Implement this method (not the public {@code create}) with the create business logic.
     * Return the populated response; on invalid input or business-rule failure throw
     * {@code io.americanexpress.synapse.framework.exception.ApplicationClientException} with an
     * {@code ErrorCode} (4XX), or {@code ApplicationServerException} (5XX) for unexpected failures —
     * the framework's {@code ControllerExceptionHandler} renders the standard error response.
     *
     * @param headers the HTTP headers
     * @param request the input request to create
     * @return the created resource response
     */
    protected abstract O executeCreate(HttpHeaders headers, I request);

}

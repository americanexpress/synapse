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
import org.springframework.http.HttpHeaders;

/**
 * {@code BaseUpdateService} class specifies the prototypes for performing business logic for update operation.
 *
 * @param <I> input request type
 * @author Alexei Morgado
 */
public abstract class BaseUpdateService<I extends BaseServiceRequest> extends BaseService {

    /**
     * Update a single resource.
     *
     * @param request body received from the controller
     */
    public void update(HttpHeaders headers, I request) {
        logger.entry(request);
        executeUpdate(headers, request);
        logger.exit();
    }

    /**
     * Prototype for updating a resource.
     *
     * @param request body received from the controller
     */
    protected abstract void executeUpdate(HttpHeaders headers, I request);
}

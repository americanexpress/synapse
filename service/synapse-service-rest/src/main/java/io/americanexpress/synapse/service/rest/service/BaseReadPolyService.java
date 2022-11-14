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
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;

public abstract class BaseReadPolyService<I extends BaseServiceRequest, O extends BaseServiceResponse> extends BaseService {

    /**
     * Get multiple resources from the back end service.
     *
     * @param headers received from the controller
     * @param request body received from the controller
     * @return a single resource from the back end service.
     */
    public Page<O> read(HttpHeaders headers, final I request) {

        logger.entry(request);

        final Page<O> responses = executeRead(headers, request);

        logger.exit(responses);

        return responses;
    }

    protected abstract Page<O> executeRead(HttpHeaders headers,I request);
}

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

import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;
import org.springframework.http.HttpHeaders;

public abstract class BaseGetMonoService<O extends BaseServiceResponse> extends BaseService {

    /**
     * Gets a single resource.
     *
     * @param headers received from the controller
     * @param identifier received from the controller
     */
    public O read(HttpHeaders headers, String identifier) {

        logger.entry(identifier);

        O response = executeRead(headers, identifier);

        logger.exit(response);

        return response;
    }

    protected abstract O executeRead(HttpHeaders headers, String identifier);

}

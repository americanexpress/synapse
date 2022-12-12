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

import org.springframework.http.HttpHeaders;

/**
 * {@code BaseDeleteService} class specifies the prototypes for performing business logic for delete operation.
 *
 * @author Alexei Morgado
 */
public abstract class BaseDeleteService extends BaseService {

    /**
     * Remove a single resource.
     *
     * @param headers received from the controller
     * @param id received from the controller
     */
    public void delete(HttpHeaders headers, String id) {
        logger.entry(id);

        executeDelete(headers, id);

        logger.exit();
    }

    /**
     * Prototype for removing a resource.
     *
     * @param id body received from the controller
     */
    protected abstract void executeDelete(HttpHeaders headers, String id);
}

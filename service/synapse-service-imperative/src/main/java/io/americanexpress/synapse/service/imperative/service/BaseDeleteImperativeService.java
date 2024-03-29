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

/**
 * {@code BaseDeleteService} class specifies the prototypes for performing business logic.
 *
 * @author Francois Gutt
 */
public abstract non-sealed class BaseDeleteImperativeService<
            I extends BaseServiceRequest
        > extends BaseService {

    /**
     *
     * @param serviceRequest
     */
    public void delete(I serviceRequest) {
        logger.entry(serviceRequest);
        executeDelete(serviceRequest);
        logger.exit();
    }

    protected abstract void executeDelete(I serviceRequest);
}

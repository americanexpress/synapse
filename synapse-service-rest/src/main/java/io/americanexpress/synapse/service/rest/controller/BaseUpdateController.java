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
package io.americanexpress.synapse.service.rest.controller;

import io.americanexpress.synapse.service.rest.model.BaseServiceRequest;
import io.americanexpress.synapse.service.rest.service.BaseUpdateService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

/**
 * <code>BaseUpdateController</code> class specifies the prototypes for listening for requests from the consumer
 * to Update (PUT/PATCH) a resource.
 *
 * @param <I> input request type
 * @param <S> service type
 * @author Gabriel Jimenez
 */
public abstract class BaseUpdateController<I extends BaseServiceRequest, S extends BaseUpdateService<I>> extends BaseController<S> {

    /**
     * Update a single resource entirely.
     *
     * @param serviceRequest body from the consumer
     */
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody I serviceRequest) {
        logger.entry(serviceRequest);

        service.update(serviceRequest);

        logger.exit();
    }

}

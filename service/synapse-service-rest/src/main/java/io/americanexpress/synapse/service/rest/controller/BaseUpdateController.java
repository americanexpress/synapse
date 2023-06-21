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
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;


/**
 * {@code BaseUpdateController} class specifies the prototypes for listening for requests from the consumer
 * to Update (PUT/PATCH) a resource. This controller expects only one object.
 *
 * @param <I> an object extending {@link BaseServiceRequest}
 * @param <S> an object extending {@link BaseUpdateService}
 * @author Gabriel Jimenez
 */
public abstract class BaseUpdateController<I extends BaseServiceRequest, S extends BaseUpdateService<I>> extends BaseController<S> {

    /**
     * Update a single resource entirely.
     *
     * @param headers containing the HTTP headers from the consumer
     * @param serviceRequest body from the consumer
     */
    @Operation(tags = "Update Operation", summary = "Updates a resource")
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestHeader HttpHeaders headers, @Valid @RequestBody I serviceRequest) {
        logger.entry(serviceRequest);

        service.update(headers, serviceRequest);

        logger.exit();
    }

}

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

import io.americanexpress.synapse.service.rest.controller.helpers.CreateResponseEntityCreator;
import io.americanexpress.synapse.service.rest.model.BaseServiceRequest;
import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;
import io.americanexpress.synapse.service.rest.service.BaseCreateService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import jakarta.validation.Valid;

/**
 * {@code BaseCreateController} class specifies the abstraction for listening for requests from the consumer
 * to Create (POST) a resource. This controller expects only one entry as request.
 *
 * @param <I> input request type
 * @param <O> output response type
 * @param <S> service type
 * @author Gabriel Jimenez
 */
public abstract class BaseCreateController<I extends BaseServiceRequest, O extends BaseServiceResponse, S extends BaseCreateService<I, O>> extends BaseController<S> {

    /**
     * Create a single resource.
     *
     * @param headers containing the HTTP headers from the consumer
     * @param serviceRequest body from the consumer
     * @return response to the consumer
     */
    @PostMapping
    @Operation(tags = "Create Operation", summary = "Creates a resource")
    public ResponseEntity<O> create(@RequestHeader HttpHeaders headers, @Valid @RequestBody I serviceRequest) {
        logger.entry(serviceRequest);
        
        O serviceResponse = service.create(headers, serviceRequest);
        ResponseEntity<O> responseEntity = CreateResponseEntityCreator.create(serviceResponse);

        logger.exit();
        return responseEntity;
    }
}

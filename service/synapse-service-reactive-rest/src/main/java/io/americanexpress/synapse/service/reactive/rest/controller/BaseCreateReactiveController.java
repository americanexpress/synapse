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
package io.americanexpress.synapse.service.reactive.rest.controller;

import io.americanexpress.synapse.service.reactive.rest.model.BaseServiceRequest;
import io.americanexpress.synapse.service.reactive.rest.model.BaseServiceResponse;
import io.americanexpress.synapse.service.reactive.rest.service.BaseCreateReactiveService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Mono;

/**
 * {@code BaseCreateReactiveController} class specifies the prototypes for listening for requests from the consumer
 * to Create (POST), Update (PUT/PATCH) or Delete (DELETE) a resource.
 *
 * @param <I> an object extending the {@link BaseServiceRequest}
 * @param <O> an object extending the {@link BaseServiceResponse}
 * @param <S> an object extending the {@link BaseCreateReactiveService}
 * @author Gabriel Jimenez
 */
public abstract class BaseCreateReactiveController<I extends BaseServiceRequest, O extends BaseServiceResponse, S extends BaseCreateReactiveService<I, O>> extends BaseController<S> {

    /**
     * Create a single resource.
     *
     * @param headers the headers
     * @param serviceRequest body from the consumer
     * @return response to the consumer
     */
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
    })
    public Mono<ResponseEntity<O>> create(@RequestHeader HttpHeaders headers, @Valid @RequestBody I serviceRequest) {
        logger.entry(serviceRequest);

        final var serviceResponse = service.create(headers, serviceRequest);
        final var responseEntity = serviceResponse
                .map(ResponseEntity.status(HttpStatus.CREATED)::body)
                .defaultIfEmpty(ResponseEntity.noContent().build());

        logger.exit();
        return responseEntity;
    }
}

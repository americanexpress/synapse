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
package io.americanexpress.synapse.api.rest.reactive.controller;

import io.americanexpress.synapse.service.reactive.model.BaseServiceRequest;
import io.americanexpress.synapse.service.reactive.model.BaseServiceResponse;
import io.americanexpress.synapse.service.reactive.service.BaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * {@code BaseCreateReactiveRestController} class specifies the prototypes for listening for requests from the consumer
 * to Create (POST), Update (PUT/PATCH) or Delete (DELETE) a resource.
 *
 * @param <I> an object extending the {@link BaseServiceRequest}
 * @param <O> an object extending the {@link BaseServiceResponse}
 * @param <S> an object extending the {@link BaseService}
 * @author Gabriel Jimenez
 */
@RestController
public class BaseCreateReactiveRestController<
            I extends BaseServiceRequest,
            O extends Publisher<? extends BaseServiceResponse>,
            S extends BaseService<I, O>
        > extends BaseController<I, O, S> {

    /**
     * Create a single resource.
     *
     * @param headers the headers
     * @param serviceRequest body from the consumer
     * @return response to the consumer
     */
    @Operation(description = "Reactive Create Operation", summary = "Creates a resource reactively")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
    })
    @PostMapping
    public Mono<ResponseEntity<O>> create(@RequestHeader HttpHeaders headers, @RequestBody I serviceRequest) {
        return execute(headers, serviceRequest);
    }
}

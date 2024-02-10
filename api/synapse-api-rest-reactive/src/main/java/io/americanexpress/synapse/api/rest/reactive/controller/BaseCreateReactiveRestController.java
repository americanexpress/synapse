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

import io.americanexpress.synapse.api.rest.reactive.controller.helper.MonoResponseEntityCreator;
import io.americanexpress.synapse.service.reactive.model.BaseServiceRequest;
import io.americanexpress.synapse.service.reactive.model.BaseServiceResponse;
import io.americanexpress.synapse.service.reactive.service.BaseCreateReactiveService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import javax.validation.Valid;

/**
 * {@code BaseCreateReactiveRestController} class specifies the prototypes for listening for requests from the consumer
 * to Create (POST), Update (PUT/PATCH) or Delete (DELETE) a resource.
 *
 * @param <I> an object extending the {@link BaseServiceRequest}
 * @param <O> an object extending the {@link BaseServiceResponse}
 * @param <S> an object extending the {@link BaseCreateReactiveService}
 * @author Gabriel Jimenez
 */
@RestController
public class BaseCreateReactiveRestController<
            I extends BaseServiceRequest,
            O extends BaseServiceResponse,
            S extends BaseCreateReactiveService<I, O>
        > extends BaseController<S> {

    /**
     * Create a single resource.
     *
     * @param headers the headers
     * @param serviceRequest body from the consumer
     * @return response to the consumer
     */
    @PostMapping
    @Operation(tags = "Reactive Create Operation", summary = "Creates a reactive resource")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
    })
    public ResponseEntity<Mono<O>> create(@RequestHeader HttpHeaders headers, @Valid @RequestBody I serviceRequest) {
        logger.entry(serviceRequest);
        final var serviceResponse = service.create(serviceRequest);
        ResponseEntity<Mono<O>> responseEntity = MonoResponseEntityCreator.create(serviceResponse);
        logger.exit();
        return responseEntity;
    }
}

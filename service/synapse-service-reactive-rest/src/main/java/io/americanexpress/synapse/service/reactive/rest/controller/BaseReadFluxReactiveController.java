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
import io.americanexpress.synapse.service.reactive.rest.service.BaseReadFluxReactiveService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Flux;

/**
 * {@code BaseReadFluxReactiveController} class specifies the prototypes for listening for requests from the consumer
 * to Read (POST) a resource.
 *
 * @param <I> an object extending the {@link BaseServiceRequest}
 * @param <O> an object extending the {@link BaseServiceResponse}
 * @param <S> an object extending the {@link BaseReadFluxReactiveService}
 * @author Gabriel Jimenez
 */
public abstract class BaseReadFluxReactiveController<I extends BaseServiceRequest, O extends BaseServiceResponse, S extends BaseReadFluxReactiveService<I, O>> extends BaseController<S> {

    /**
     * The constant MULTIPLE_RESULTS.
     */
    public static final String MULTIPLE_RESULTS = "/multiple_results";

    /**
     * Get a list of multiple resources from the back end service.
     *
     * @param headers the headers
     * @param serviceRequest body from the consumer
     * @return a list of resources from the back end service
     */
    @ApiOperation(value = "Reactive Read Poly", notes = "Gets a collection of resources", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
    })
    @PostMapping(MULTIPLE_RESULTS)
    public Flux<ResponseEntity<O>> read(@RequestHeader HttpHeaders headers, @Valid @RequestBody I serviceRequest) {
        logger.entry(serviceRequest);

        final var serviceResult = service.read(headers, serviceRequest);
        final var responseEntity = serviceResult
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());

        logger.exit(responseEntity);
        return responseEntity;
    }

}

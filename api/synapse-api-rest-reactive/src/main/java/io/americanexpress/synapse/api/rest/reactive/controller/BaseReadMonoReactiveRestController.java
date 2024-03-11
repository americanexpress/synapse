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
import io.americanexpress.synapse.service.reactive.service.BaseReadMonoReactiveService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Mono;
import jakarta.validation.Valid;

/**
 * {@code BaseReadMonoReactiveRestController} class specifies the prototypes for listening for requests from the consumer
 * to Read (POST) a resource. This Controller expects only one object in request and one object in the
 * response, hence, "Mono" in the name.
 *
 * @param <I> an object extending the {@link BaseServiceRequest}
 * @param <O> an object extending the {@link BaseServiceResponse}
 * @param <S> an object extending the {@link BaseReadMonoReactiveService}
 * @author Gabriel Jimenez
 */
public class BaseReadMonoReactiveRestController<
            I extends BaseServiceRequest,
            O extends BaseServiceResponse,
            S extends BaseReadMonoReactiveService<I, O>
        > extends BaseController<S> {

    /**
     * The constant INQUIRY_RESULTS.
     */
    public static final String INQUIRY_RESULTS = "/inquiry-results";

    /**
     * Get a single resource from the back end service.
     * @param headers the headers
     * @param serviceRequest body from the consumer
     * @return a single resource from the back end service
     */
    @ApiOperation(value = "Reactive Read Mono", notes = "Gets one resource")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 206, message = "Partial Content"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
    })
    @PostMapping(INQUIRY_RESULTS)
    public Mono<ResponseEntity<O>> read(@RequestHeader HttpHeaders headers, @Valid @RequestBody I serviceRequest) {
        logger.entry(serviceRequest);
        var serviceResponse = service.read(serviceRequest);
        var responseEntity = serviceResponse
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());
        logger.exit(responseEntity);
        return responseEntity;
    }
}

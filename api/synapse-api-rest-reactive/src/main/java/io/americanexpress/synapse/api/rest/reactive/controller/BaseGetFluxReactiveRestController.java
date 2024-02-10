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
import io.americanexpress.synapse.service.reactive.service.BaseGetFluxReactiveService;
import io.americanexpress.synapse.service.reactive.service.BaseGetMonoReactiveService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Flux;

/**
 * {@code BaseGetFluxReactiveRestController} is base class for read mono controller.
 * This controller handles GET method requests, but specifically for read purposes.
 *  This controller returns a single object.
 * @param <O> an object extending the {@link BaseServiceResponse}
 * @param <S> an object extending the {@link BaseGetMonoReactiveService}
 * @author Francois Gutt
 */
public class BaseGetFluxReactiveRestController<
            I extends BaseServiceRequest,
            O extends BaseServiceResponse,
            S extends BaseGetFluxReactiveService<I,O>
        >
        extends BaseController<S> {

    /**
     * Get a list of multiple resources from the back end service.
     * @param headers the headers
     * @return response
     */
    @ApiOperation(value = "Reactive get flux", notes = "Gets all resources reactively")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 206, message = "Partial Content"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
    })
    @GetMapping()
    public Flux<O> read(@RequestHeader HttpHeaders headers, I serviceRequest) {
        logger.entry();
        final var serviceResponse = service.read(serviceRequest);
        logger.exit();
        return serviceResponse;
    }
}

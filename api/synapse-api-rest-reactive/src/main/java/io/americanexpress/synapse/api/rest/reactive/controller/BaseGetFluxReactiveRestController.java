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
import org.reactivestreams.Publisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Mono;

/**
 * {@code BaseGetFluxReactiveRestController} is base class for read mono controller.
 * This controller handles GET method requests, but specifically for read purposes.
 *  This controller returns a single object.
 * @param <O> an object extending the {@link BaseServiceResponse}
 * @param <S> an object extending the {@link BaseService}
 * @author Francois Gutt
 */
public class BaseGetFluxReactiveRestController<
            I extends BaseServiceRequest,
            O extends Publisher<? extends BaseServiceResponse>,
            S extends BaseService<I, O>
        > extends BaseController<I, O, S> {

    /**
     * Get a list of multiple resources from the back end service.
     * @param headers the headers
     * @return response
     */
    @Operation(description = "Reactive get flux", summary = "Gets all resources reactively",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok"),
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(responseCode = "206", description = "Partial Content"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
            })
    @GetMapping()
    public Mono<ResponseEntity<O>> read(@RequestHeader HttpHeaders headers, I serviceRequest) {
        return execute(headers, serviceRequest);
    }
}

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Mono;

/**
 * {@code BaseDeleteReactiveRestController} is base class for delete mono controller. This controller handles DELETE
 *  method requests, but specifically for delete purposes.
 *  This controller returns a single object.
 * @param <S> an object extending the {@link BaseService}
 * @author Francois Gutt
 */
public class BaseDeleteReactiveRestController<
            I extends BaseServiceRequest,
            O extends Publisher<? extends BaseServiceResponse>,
            S extends BaseService<I, O>
        > extends BaseController<I, O, S> {

    /**
     * Delete a single resource.
     * @param headers the headers
     * @param serviceRequest of the resource to be deleted
     */
    @DeleteMapping
    @Operation(description = "Delete Operation", summary = "Deletes a resource reactively",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
            })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<O>> delete(@RequestHeader HttpHeaders headers, @PathVariable I serviceRequest) {
        return execute(headers, serviceRequest);
    }
}

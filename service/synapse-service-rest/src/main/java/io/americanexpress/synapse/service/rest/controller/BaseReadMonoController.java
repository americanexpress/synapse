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

import io.americanexpress.synapse.service.rest.controller.helpers.MonoResponseEntityCreator;
import io.americanexpress.synapse.service.rest.model.BaseServiceRequest;
import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;
import io.americanexpress.synapse.service.rest.service.BaseReadMonoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * <code>BaseReadMonoController</code> class specifies the prototypes for listening for requests from the consumer
 * to Read (POST) a resource. This Controller expects only one object in request and one object in the response, hence, "Mono" in the name.
 * *
 *
 * @param <I> input request type
 * @param <O> output response type
 * @param <S> service type
 * @author Gabriel Jimenez
 */
public abstract class BaseReadMonoController<I extends BaseServiceRequest, O extends BaseServiceResponse, S extends BaseReadMonoService<I, O>> extends BaseController<S> {

    public static final String INQUIRY_RESULTS = "/inquiry_results";

    @Autowired
    private MonoResponseEntityCreator<O> monoResponseEntityCreator;

    /**
     * Get a single resource from the back end service.
     *
     * @param serviceRequest body from the consumer
     * @return a single resource from the back end service
     */
    @Operation(tags ="Read Mono Operation", summary = "Read Mono Operation", description = "Reads one resource")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "OK"),
//            @ApiResponse(code = 204, message = "No Content"),
//            @ApiResponse(code = 400, message = "Bad Request"),
//            @ApiResponse(code = 401, message = "Unauthorized"),
//            @ApiResponse(code = 403, message = "Forbidden")
//    })
//    @ApiOperation(value = "Read Mono", notes = "Gets one resource")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "OK"),
//            @ApiResponse(code = 204, message = "No Content"),
//            @ApiResponse(code = 400, message = "Bad Request"),
//            @ApiResponse(code = 401, message = "Unauthorized"),
//            @ApiResponse(code = 403, message = "Forbidden")
//    })
    @PostMapping(INQUIRY_RESULTS)
    public ResponseEntity<O> read(@Valid @RequestBody I serviceRequest) {
        logger.entry(serviceRequest);

        final O serviceResponse = service.read(serviceRequest);
        ResponseEntity<O> responseEntity = monoResponseEntityCreator.create(serviceResponse);

        logger.exit(responseEntity);
        return responseEntity;
    }


}

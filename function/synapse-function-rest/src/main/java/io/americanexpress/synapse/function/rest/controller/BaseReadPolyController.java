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
package io.americanexpress.synapse.function.rest.controller;

import io.americanexpress.synapse.service.rest.controller.helpers.PolyResponseEntityCreator;
import io.americanexpress.synapse.service.rest.model.BaseServiceRequest;
import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;
import io.americanexpress.synapse.service.rest.service.BaseReadPolyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * <code>BaseReadPolyController</code> class specifies the prototypes for listening for requests from the consumer
 * to Read (POST) a resource. This Controller expects only one object in request and a list of objects as response, hence, "Poly" in the name.
 *
 * @param <I> input request type
 * @param <O> output response type
 * @param <S> service type
 * @author Gabriel Jimenez
 */
public abstract class BaseReadPolyController<I extends BaseServiceRequest, O extends BaseServiceResponse, S extends BaseReadPolyService<I, O>> extends BaseController<S> {

    public static final String MULTIPLE_RESULTS = "/multiple_results";

    @Autowired
    private PolyResponseEntityCreator<O> polyResponseEntityCreator;

    /**
     * Get a list of multiple resources from the back end service.
     *
     * @param serviceRequest      body from the consumer
     * @param httpServletResponse HttpServletResponse
     * @return a list of resources from the back end service
     */
    @Operation(summary = "Read operation based on criteria.", description = "Read a collection of resources based on request criteria.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
    })
    @PostMapping(MULTIPLE_RESULTS)
    public ResponseEntity<List<O>> read(@Valid @RequestBody I serviceRequest, HttpServletResponse httpServletResponse) {
        logger.entry(serviceRequest);

        final Page<O> page = service.read(serviceRequest);
        final ResponseEntity<List<O>> responseEntity = polyResponseEntityCreator.create(page, httpServletResponse);

        logger.exit(responseEntity);
        return responseEntity;
    }

}

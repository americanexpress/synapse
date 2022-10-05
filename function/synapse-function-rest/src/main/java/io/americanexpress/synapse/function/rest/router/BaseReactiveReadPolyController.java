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
package io.americanexpress.synapse.function.rest.router;

import io.americanexpress.synapse.function.rest.handler.BaseReadPolyService;
import io.americanexpress.synapse.function.rest.model.BaseFunctionRequest;
import io.americanexpress.synapse.function.rest.model.BaseFunctionResponse;
import io.americanexpress.synapse.function.rest.router.helpers.ReactivePolyResponseEntityCreator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * <code>BaseReadController</code> class specifies the prototypes for listening for requests from the consumer
 * to Read (POST) a resource.
 *
 * @param <I> input request type
 * @param <O> output response type
 * @param <S> service type
 * @author Gabriel Jimenez
 */
public abstract class BaseReactiveReadPolyController<I extends BaseFunctionRequest, O extends BaseFunctionResponse, S extends BaseReadPolyService<I, O>> extends BaseRouter<S> {

    public static final String MULTIPLE_RESULTS = "/multiple_results";

    @Autowired
    private ReactivePolyResponseEntityCreator<O> reactivePolyResponseEntityCreator;

    /**
     * Get a list of multiple resources from the back end service.
     *
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
    public ResponseEntity<Flux<O>> read(@Valid @RequestBody I serviceRequest, HttpServletResponse httpServletResponse) {
        logger.entry(serviceRequest);

        Page<O> page = service.read(serviceRequest);

        ResponseEntity<Flux<O>> responseEntity = reactivePolyResponseEntityCreator.create(page, httpServletResponse);

        logger.exit(responseEntity);
        return responseEntity;
    }

}

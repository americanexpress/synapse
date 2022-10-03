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
package io.americanexpress.synapse.function.rest.router.reactive;

import io.americanexpress.synapse.function.rest.router.BaseController;
import io.americanexpress.synapse.function.rest.router.reactive.helpers.ReactiveMonoResponseEntityCreator;
import io.americanexpress.synapse.function.rest.model.BaseServiceRequest;
import io.americanexpress.synapse.function.rest.model.BaseServiceResponse;
import io.americanexpress.synapse.function.rest.handler.BaseReadMonoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

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
public abstract class BaseReactiveReadMonoController<I extends BaseServiceRequest, O extends BaseServiceResponse, S extends BaseReadMonoService<I, O>> extends BaseController<S> {

    public static final String INQUIRY_RESULTS = "/inquiry_results";

    @Autowired
    private ReactiveMonoResponseEntityCreator<O> reactiveMonoResponseEntityCreator;

    /**
     * Get a single resource from the back end service.
     *
     * @param serviceRequest body from the consumer
     * @return a single resource from the back end service
     */
    @ApiOperation(value = "Reactive Read Mono", notes = "Gets one resource")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
    })
    @PostMapping(INQUIRY_RESULTS)
    public ResponseEntity<Mono<O>> read(@Valid @RequestBody I serviceRequest) {
        logger.entry(serviceRequest);

        O serviceResponse = service.read(serviceRequest);
        ResponseEntity<Mono<O>> responseEntity = reactiveMonoResponseEntityCreator.create(Mono.just(serviceResponse));

        logger.exit(responseEntity);
        return responseEntity;
    }

}

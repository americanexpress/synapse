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

import io.americanexpress.synapse.function.rest.handler.BaseReadMonoHandler;
import io.americanexpress.synapse.function.rest.model.BaseFunctionRequest;
import io.americanexpress.synapse.function.rest.model.BaseFunctionResponse;
import io.americanexpress.synapse.function.rest.router.helpers.ReactiveMonoResponseEntityCreator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

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
public abstract class BaseReactiveReadMonoRouter<I extends BaseFunctionRequest, O extends BaseFunctionResponse, S extends BaseReadMonoHandler<I, O>> extends BaseRouter<S> {

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
    @Bean
    public RouterFunction<ServerResponse> route(@Valid @RequestBody I serviceRequest) {

        O serverResponse = service.read(serviceRequest);
//        return RouterFunctions.route(POST(INQUIRY_RESULTS).and(accept(MediaType.APPLICATION_JSON),reactiveMonoResponseEntityCreator.create(Mono.just(serverResponse)));
        return null;
    }

}
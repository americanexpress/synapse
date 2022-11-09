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
package io.americanexpress.synapse.function.reactive.router;

import io.americanexpress.synapse.function.reactive.handler.BaseCrudMonoHandler;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

/**
 * {@code BaseReactiveReadMonoRouter} class specifies the prototypes for listening for requests from the consumer
 * to Read (POST) a resource.
 *
 * @param <S> service type
 * @author Gabriel Jimenez
 */

@Configuration
public abstract class BaseCrudMonoRouter<S extends BaseCrudMonoHandler> extends BaseRouter<S> {

    private String endpoint = "books";


    /**
     * Get a single resource from the back end service.
     *
     * @param handler body from the consumer
     * @return a single resource from the back end service
     */
    @ApiOperation(value = "Reactive Create Mono", notes = "Create one resource")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
    })

    @Bean
    public RouterFunction<ServerResponse> crudRoute(S handler) {
        logger.entry(handler);

        RouterFunction<ServerResponse> routerResponse = RouterFunctions
//                .route(GET(getEndpoint()).and(accept(MediaType.APPLICATION_JSON)), handler::getAll)
                .route(GET(getEndpoint()).and(accept(MediaType.APPLICATION_JSON)), handler::getAll);
//                .andRoute(POST(getEndpoint()).and(accept(MediaType.APPLICATION_JSON)), handler::create)
//                .andRoute(GET(getEndpoint() + "/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::getById)
//                .andRoute(PUT(getEndpoint() + "/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::updateById)
//                .andRoute(DELETE(getEndpoint() + "/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::deleteById);

        logger.exit();

        return routerResponse;
    }

    private String getEndpoint() {
        return endpoint;
    }

    protected abstract void setEndpoint(String endpoint);
}

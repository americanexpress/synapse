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

import io.americanexpress.synapse.function.reactive.handler.BaseReadPolyHandler;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

/**
 * <code>BaseReactiveReadMonoRouter</code> class specifies the prototypes for listening for requests from the consumer
 * to Read (POST) a resource.
 *
 * @param <S> service type
 * @author Gabriel Jimenez
 */
public abstract class BaseReadPolyRouter<S extends BaseReadPolyHandler> extends BaseRouter<S> {

    public static final String INQUIRY_RESULTS = "/multiple_results";

    public static String endpoint = "not_a_valid_endpoint";

    /**
     * Get a single resource from the back end service.
     *
     * @param handler body from the consumer
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
    public RouterFunction<ServerResponse> routePoly(S handler) {
        return RouterFunctions
                .route(GET(getEndpoint()).and(accept(MediaType.APPLICATION_JSON)), handler::read);
    }

    private String getEndpoint() {
        return endpoint;
    }

    protected abstract void setEndpoint(String endpoint);
}

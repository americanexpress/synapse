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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    public static final String MULTIPLE_RESULTS = "/multiple_results";

    /**
     * Get a single resource from the back end service.
     *
     * @param handler body from the consumer
     * @return a single resource from the back end service
     */
    @Operation(description = "Reactive Read Mono", summary = "Gets one resource")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
    })

    @Bean
    public RouterFunction<ServerResponse> routePoly(S handler) {
        return RouterFunctions
                .route(GET(getEndpoint() + MULTIPLE_RESULTS).and(accept(MediaType.APPLICATION_JSON)), handler::read);
    }

    public abstract String getEndpoint();
}

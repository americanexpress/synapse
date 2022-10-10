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
package io.americanexpress.synapse.function.reactive.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public abstract class BaseCrudMonoHandler extends BaseHandler {

    /**
     * Get a single resource
     *
     * @param request body received from the controller
     * @return a single resource from the back end service.
     */
    public Mono<ServerResponse> create(ServerRequest request) {
        logger.entry(request);

        final Mono<ServerResponse> response = executeCreate(request);

        logger.exit(response);
        return response;
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        logger.entry(request);

        final Mono<ServerResponse> response = executeGetById(request);

        logger.exit(response);
        return response;
    }

    public Mono<ServerResponse> updateById(ServerRequest request) {
        logger.entry(request);

        final Mono<ServerResponse> response = executeUpdateById(request);

        logger.exit(response);
        return response;
    }

    public Mono<ServerResponse> deleteById(ServerRequest request) {
        logger.entry(request);

        final Mono<ServerResponse> response = executeDeleteById(request);

        logger.exit(response);
        return response;
    }

    protected abstract Mono<ServerResponse> executeCreate(ServerRequest request);

    protected abstract Mono<ServerResponse> executeGetById(ServerRequest request);

    protected abstract Mono<ServerResponse> executeUpdateById(ServerRequest request);

    protected abstract Mono<ServerResponse> executeDeleteById(ServerRequest request);
}

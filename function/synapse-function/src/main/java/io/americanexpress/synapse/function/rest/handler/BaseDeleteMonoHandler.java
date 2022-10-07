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
package io.americanexpress.synapse.function.rest.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public abstract class BaseDeleteMonoHandler extends BaseHandler {

    /**
     * Delete a single resource from the back end service.
     *
     * @param request body received from the controller
     * @return a single resource from the back end service.
     */
    public Mono<ServerResponse> delete(ServerRequest request) {
        logger.entry(request);

        final Mono<ServerResponse> response = executeDelete(request);

        logger.exit(response);
        return response;
    }

    protected abstract Mono<ServerResponse> executeDelete(ServerRequest request);
}

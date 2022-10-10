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

import io.americanexpress.synapse.function.reactive.model.BaseFunctionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public abstract class BaseCrudMonoHandler<S extends BaseFunctionResponse> extends BaseHandler {

    private Class<S> baseFunctionResponse;

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return ServerResponse
          .ok()
          .contentType(MediaType.APPLICATION_JSON)
//          .body(executeGetAll(request), baseFunctionResponse);
          .body(executeGetAll(request), baseFunctionResponse);
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        return executeGetById(request.pathVariable("id"))
            .flatMap(user -> ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(user, baseFunctionResponse)
          )
          .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<S> response = request.bodyToMono(baseFunctionResponse);

        return response
            .flatMap(u -> ServerResponse
            .status(HttpStatus.CREATED)
            .contentType(MediaType.APPLICATION_JSON)
            .body(executeCreate(u), baseFunctionResponse)
          );
    }

    public Mono<ServerResponse> updateById(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<S> update = request.bodyToMono(baseFunctionResponse);

        return update
            .flatMap(object -> ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(executeUpdateById(id, object), baseFunctionResponse)
          );
    }

    public Mono<ServerResponse> deleteById(ServerRequest request){
        return executeDeleteById(request.pathVariable("id"))
          .flatMap(u -> ServerResponse.ok().body(u, baseFunctionResponse))
          .switchIfEmpty(ServerResponse.notFound().build());
    }

    protected abstract Mono<ServerResponse> executeGetAll(ServerRequest request);

    protected abstract Mono<ServerResponse> executeCreate(S object);

    protected abstract Mono<ServerResponse> executeGetById(String id);

    protected abstract Mono<ServerResponse> executeUpdateById(String id, S object);

    protected abstract Mono<ServerResponse> executeDeleteById(String id);

}

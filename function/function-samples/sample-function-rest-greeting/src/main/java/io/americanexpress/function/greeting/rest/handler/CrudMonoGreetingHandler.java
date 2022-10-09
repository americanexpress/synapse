package io.americanexpress.function.greeting.rest.handler;

import io.americanexpress.function.greeting.rest.model.Greeting;
import io.americanexpress.synapse.function.reactive.handler.BaseCrudMonoHandler;
import io.americanexpress.synapse.function.reactive.handler.BaseReadMonoHandler;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class CrudMonoGreetingHandler extends BaseCrudMonoHandler {


    @Override
    protected Mono<ServerResponse> executeCreate(ServerRequest request) {
        return null;
    }

    @Override
    protected Mono<ServerResponse> executeGetById(ServerRequest request) {
        return null;
    }

    @Override
    protected Mono<ServerResponse> executeUpdateById(ServerRequest request) {
        return null;
    }

    @Override
    protected Mono<ServerResponse> executeDeleteById(ServerRequest request) {
        return null;
    }
}

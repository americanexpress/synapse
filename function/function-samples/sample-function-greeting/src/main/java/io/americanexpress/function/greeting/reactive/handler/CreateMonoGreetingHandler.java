package io.americanexpress.function.greeting.reactive.handler;

import io.americanexpress.function.greeting.reactive.model.Greeting;
import io.americanexpress.synapse.function.reactive.handler.BaseCreateMonoHandler;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class CreateMonoGreetingHandler extends BaseCreateMonoHandler {

    @Override
    protected Mono<ServerResponse> executeCreate(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new Greeting("Hello, Spring!!")));
    }
}

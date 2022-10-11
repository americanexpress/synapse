package io.americanexpress.function.greeting.reactive.handler;

import org.springframework.beans.factory.annotation.Autowired;
import io.americanexpress.function.greeting.reactive.model.Greeting;
import io.americanexpress.synapse.function.reactive.handler.BaseReadMonoHandler;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ReadMonoGreetingHandler extends BaseReadMonoHandler {

    private ReadMonoGreetingHandler(@Autowired Validator validator) {
        super(Greeting.class, validator);
    }

    @Override
    protected Mono<ServerResponse> executeRead(Object request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new Greeting("Hello, Spring!!")));
    }
}

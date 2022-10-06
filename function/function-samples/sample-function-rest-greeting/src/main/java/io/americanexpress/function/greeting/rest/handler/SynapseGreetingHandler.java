package io.americanexpress.function.greeting.rest.handler;

import io.americanexpress.function.greeting.rest.model.Greeting;
import io.americanexpress.synapse.function.rest.handler.BaseReadMonoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class SynapseGreetingHandler extends BaseReadMonoHandler<Greeting, Validator> {

    private SynapseGreetingHandler(@Autowired Validator validator) {
        super(Greeting.class, validator);
    }

    @Override
    protected Mono<ServerResponse> executeRead(Greeting request) {
        logger.info("Im here");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new Greeting("Hello, Spring!!")));
    }
}

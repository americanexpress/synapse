package io.americanexpress.function.greeting.reactive.handler;

import io.americanexpress.function.greeting.reactive.model.Greeting;
import io.americanexpress.synapse.function.reactive.handler.BaseUpdateMonoHandler;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UpdateMonoGreetingHandler extends BaseUpdateMonoHandler<Greeting> {

    @Override
    protected Mono<ServerResponse> executeUpdate(Greeting request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new Greeting("UPDATE Greeting Updated!!")));
    }
}

package io.americanexpress.function.greeting.rest.handler;

import io.americanexpress.function.greeting.rest.model.Greeting;
import io.americanexpress.function.greeting.rest.model.GreetingRequest;
import io.americanexpress.function.greeting.rest.model.GreetingResponse;
import io.americanexpress.synapse.function.rest.handler.BaseReadMonoHandler;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class SynapseGreetingHandler extends BaseReadMonoHandler<GreetingRequest, GreetingResponse> {

  public Mono<ServerResponse> hello(ServerRequest request) {
    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
      .body(BodyInserters.fromValue(new Greeting("Hello, Spring!")));
  }

  @Override
  protected Mono<GreetingResponse> executeRead(GreetingRequest request) {
    return null;
  }

  @Override
  protected Mono<GreetingResponse> executeRead(ServerRequest request) {
    return ServerResponse
      .ok()
      .contentType(MediaType.APPLICATION_JSON)
      .body(BodyInserters.fromValue(new Greeting("Hello, Spring!")));
  }
}
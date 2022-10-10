package io.americanexpress.function.book.rest.client;

import io.americanexpress.function.book.rest.model.ReadBookResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class BookWebClient {

  private final WebClient client;

  // Spring Boot auto-configures a `WebClient.Builder` instance with nice defaults and customizations.
  // We can use it to create a dedicated `WebClient` for our component.
  public BookWebClient(WebClient.Builder builder) {
    this.client = builder.baseUrl("http://localhost:8080").build();
  }

  public Mono<String> getTitle() {
    return this.client.get().uri("/books").accept(MediaType.APPLICATION_JSON)
      .retrieve()
      .bodyToMono(ReadBookResponse.class)
      .map(ReadBookResponse::getTitle);
  }
}

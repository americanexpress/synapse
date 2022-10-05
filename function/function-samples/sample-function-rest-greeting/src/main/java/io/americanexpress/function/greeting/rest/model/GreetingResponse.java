package io.americanexpress.function.greeting.rest.model;

import org.springframework.web.reactive.function.server.ServerResponse;

public abstract class GreetingResponse implements ServerResponse {

  private String message;

  public GreetingResponse() {
  }

  public GreetingResponse(String message) {
    this.message = message;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
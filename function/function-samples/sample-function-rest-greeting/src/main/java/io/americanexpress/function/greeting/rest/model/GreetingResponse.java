package io.americanexpress.function.greeting.rest.model;


import io.americanexpress.synapse.function.rest.model.BaseFunctionResponse;

public class GreetingResponse extends BaseFunctionResponse {

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

  @Override
  public String toString() {
    return "Greeting{" +
        "message='" + message + '\'' +
        '}';
  }
}
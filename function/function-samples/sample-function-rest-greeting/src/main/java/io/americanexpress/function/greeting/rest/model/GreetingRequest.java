package io.americanexpress.function.greeting.rest.model;


import io.americanexpress.synapse.function.rest.model.BaseFunctionRequest;

public class GreetingRequest implements BaseFunctionRequest {

  private String message;

  public GreetingRequest() {
  }

  public GreetingRequest(String message) {
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
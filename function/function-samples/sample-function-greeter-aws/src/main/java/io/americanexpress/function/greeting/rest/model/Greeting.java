package io.americanexpress.function.greeting.rest.model;

import javax.validation.constraints.NotBlank;

public class Greeting {

    @NotBlank
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package io.americanexpress.function.greeting.rest.function;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class Greeter implements Function<String, String> {

    @Override
    public String apply(String s) {
        return "Hello " + s + ", and welcome to Spring Cloud Function!!!";
    }
}

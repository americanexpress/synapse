package io.americanexpress.service.book.rest;

import org.springframework.stereotype.Component;
import java.util.function.Function;

@Component
public class SimpleFunction implements Function<String, String> {

    @Override
    public String apply(String s) {
        return "Test Function + " + s;
    }
}
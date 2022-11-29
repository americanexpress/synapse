package io.americanexpress.service.book.rest;

import java.util.function.Function;

public class SimpleFunction implements Function<String, String> {

    @Override
    public String apply(String s) {
        System.out.println("Input = " + s);
        return "Test Function + " + s;
    }
}
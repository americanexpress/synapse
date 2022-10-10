package io.americanexpress.function.book.rest;

import io.americanexpress.function.book.rest.client.BookWebClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class BookFunctionApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BookFunctionApplication.class, args);
        BookWebClient bookWebClient = context.getBean(BookWebClient.class);
        // We need to block for the content here or the JVM might exit before the message is logged
        System.out.println(">> message = " + bookWebClient.getTitle().block());
    }
}
package io.americanexpress.service.book.rest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

/**
 * {@code BookApplication} starts the Spring Boot Application for the book rest sample.
 */
@OpenAPIDefinition(info = @Info(
        title = "Book API",
        version = "v1.0.0",
        description = "Rest API that provides book related information."))
@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class BookApplication {
    private static final XLogger LOGGER = XLoggerFactory.getXLogger(BookApplication.class);

    public static void main(String... args) {
        SpringApplication.run(BookApplication.class, args);
        LOGGER.info("Rest Book Oracle Sample Application is up and running...");
    }
}

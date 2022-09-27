package io.americanexpress.service.book.rest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * BookApplication starts the Spring Boot Application for the book rest sample.
 */
@OpenAPIDefinition(info = @Info(
        title = "Book API",
        version = "v1.0.0",
        description = "Rest API that provides book related information."))
@SpringBootApplication
public class BookApplication {

    private static final XLogger LOGGER = XLoggerFactory.getXLogger(BookApplication.class);

    /**
     * Main method to run the Spring Boot Book Application.
     *
     * @param args the args
     */
    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
        LOGGER.info("Rest Book Application is up and running ...");
    }


}

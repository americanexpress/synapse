package io.americanexpress.synapse.api.rest.sample.imperativebook;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * {@code BookApplication} starts the Spring Boot Application for the book rest sample.
 *
 * @author Tanvir Islam
 */
@SpringBootApplication()
public class BookApplication {

    /**
     * The logger.
     */
    private static final XLogger LOGGER = XLoggerFactory.getXLogger(BookApplication.class);

    /**
     * Runs the application.
     *
     * @param args the var args
     */
    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
        LOGGER.info("Rest Book Imperative Sample Application is up and running...");
    }
}
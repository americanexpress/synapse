package io.americanexpress.service.book.reactive.rest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.americanexpress.synapse.service.reactive.rest.config.ServiceReactiveRestConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * BookConfig is the configuration class for the Book Application.
 */
@Configuration
@PropertySource("classpath:/service-book-application.properties")
@ComponentScan(basePackages = "io.americanexpress.service.book.reactive.rest")
public class BookConfig extends ServiceReactiveRestConfig {

    /**
     * The constant BOOK_ENDPOINT.
     */
    public static final String BOOK_ENDPOINT = "/v1/books";

    /**
     * Constructor taking in objectMapper & metricInterceptor
     *
     * @param defaultObjectMapper the default object mapper.
     */
    public BookConfig(ObjectMapper defaultObjectMapper) {
        super(defaultObjectMapper);
    }
}

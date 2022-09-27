package io.americanexpress.service.book.rest.config;

import io.americanexpress.data.book.config.BookDataConfig;
import io.americanexpress.synapse.service.rest.config.ServiceRestConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * BookConfig is the configuration class for the Book Application.
 */
@Configuration
@PropertySource("classpath:/service-book-application.properties")
@ComponentScan(basePackages = "io.americanexpress.service.book.rest")
@Import({ServiceRestConfig.class, BookDataConfig.class})
public class BookConfig {

    public BookConfig() {}

    /**
     * The constant BOOK_ENDPOINT.
     */
    public static final String BOOK_ENDPOINT = "/v1/books";
}

package io.americanexpress.sample.client.graphql.book.config;

import io.americanexpress.sample.client.graphql.book.client.BookReactiveGraphqlClient;
import io.americanexpress.synapse.client.rest.config.BaseReactiveRestClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * {@code BookReactiveGraphqlClientConfig} contains configurations for book graphql client.
 */
@ComponentScan("io.americanexpress.sample.client.graphql.book")
@Configuration
@PropertySource("classpath:client-graphql-book.properties")
public class BookReactiveGraphqlClientConfig extends BaseReactiveRestClientConfig {

    /**
     * Used to call book graphql api.
     */
    private final BookReactiveGraphqlClient bookReactiveGraphqlClient;

    /**
     * Instantiates a new Book reactive graphql client config.
     *
     * @param bookReactiveGraphqlClient the book reactive graphql client
     */
    public BookReactiveGraphqlClientConfig(BookReactiveGraphqlClient bookReactiveGraphqlClient) {
        this.bookReactiveGraphqlClient = bookReactiveGraphqlClient;
    }

    /**
     * Initialize the book graphql client with url.
     * @param destinationUrl the url
     */
    @Value("${book.graphql.client.url}")
    @Override
    protected void initialize(String destinationUrl) {
        initializeClient(destinationUrl, bookReactiveGraphqlClient);
    }
}

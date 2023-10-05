package io.americanexpress.sample.client.graphql.book.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BookReactiveGraphqlClientConfig.class)
public class BookReactiveGraphqlClientTestConfig {
}

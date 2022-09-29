package io.americanexpress.service.book.rest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * {@code BookTestConfig} contains configurations for testing.
 */
@Configuration
@Import(BookConfig.class)
public class BookTestConfig {
}

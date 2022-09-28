package io.americanexpress.data.book.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * {@code BookDataTestConfig} class contains configurations for tests.
 */
@Configuration
@Import({BookDataConfig.class})
public class BookDataTestConfig {
}

package io.americanexpress.function.book.config;

import io.americanexpress.data.book.config.BookDataConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({BookDataConfig.class})
public class BookConfig {
}

package io.americanexpress.data.book.config;

import io.americanexpress.synapse.data.redis.config.BaseRedisDataConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * {@code BookDataConfig} is the configuration class to load all the properties for the book data module.
 */
@Configuration
@PropertySource("classpath:/data-book-application.properties")
@ComponentScan("io.americanexpress.data.book.*")
public class BookDataConfig extends BaseRedisDataConfig {

    public BookDataConfig(Environment environment) {
        super(environment);
    }

}

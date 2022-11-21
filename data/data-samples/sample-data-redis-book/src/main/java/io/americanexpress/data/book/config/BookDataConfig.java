package io.americanexpress.data.book.config;

import io.americanexpress.synapse.data.redis.config.BaseRedisDataConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * {@code BookDataConfig} is the configuration class to load all the properties for the book data module.
 */
@Configuration
@PropertySource("classpath:data-book-application.properties")
@EnableRedisRepositories(basePackages = BookDataConfig.PACKAGE_NAME)
@EnableAutoConfiguration
public class BookDataConfig extends BaseRedisDataConfig {

    /**
     * The Package name.
     */
    static final String PACKAGE_NAME = "io.americanexpress.data.book";
    public BookDataConfig(Environment environment) {
        super(environment);
    }

}

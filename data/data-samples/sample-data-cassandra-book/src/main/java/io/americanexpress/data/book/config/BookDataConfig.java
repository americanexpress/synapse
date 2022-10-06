package io.americanexpress.data.book.config;

import io.americanexpress.synapse.data.cassandra.config.BaseCassandraDataConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * {@code BookDataConfig} is the configuration class to load all the properties for the book data module.
 */
@Configuration
@PropertySource("classpath:/data-book-application.properties")
public class BookDataConfig extends BaseCassandraDataConfig {

    public BookDataConfig(Environment environment) {
        super(environment);
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[]{"io.americanexpress.data.book.entity"};
    }
}

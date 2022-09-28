package io.americanexpress.data.oracle.book.config;

import io.americanexpress.synapse.data.oracle.config.BaseR2dbcOracleConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:data-oracle-book-application.properties")
public class R2dbcDataBookConfig extends BaseR2dbcOracleConfig {

    public R2dbcDataBookConfig(Environment environment) {
        super(environment);
    }
}

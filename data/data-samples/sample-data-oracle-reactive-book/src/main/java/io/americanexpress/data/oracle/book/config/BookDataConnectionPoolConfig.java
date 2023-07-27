package io.americanexpress.data.oracle.book.config;

import io.americanexpress.synapse.data.oracle.config.BaseReactiveOracleConfig;
import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import java.time.Duration;

@Configuration
@PropertySource("classpath:data-oracle-book-application.properties")
@EnableR2dbcRepositories(basePackages = "io.americanexpress.data.oracle.book.dao")
public class BookDataConnectionPoolConfig extends BaseReactiveOracleConfig {
    protected BookDataConnectionPoolConfig(Environment environment) {
        super(environment);
    }

    @Bean
    public ConnectionPool connectionPool(ConnectionFactory connectionFactory) {
        ConnectionPoolConfiguration configPool = ConnectionPoolConfiguration.builder(connectionFactory)
                .maxIdleTime(Duration.ofMillis(1000))
                .maxSize(20)
                .build();


        return new ConnectionPool(configPool);
    }
}

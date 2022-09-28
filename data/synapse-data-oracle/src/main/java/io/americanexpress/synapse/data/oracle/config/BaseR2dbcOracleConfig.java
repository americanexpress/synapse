package io.americanexpress.synapse.data.oracle.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
@EnableR2dbcRepositories
public abstract class BaseR2dbcOracleConfig extends AbstractR2dbcConfiguration {

    protected final Environment environment;

    public BaseR2dbcOracleConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    @ConfigurationProperties("spring.oracle.r2dbc.datasource")
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(ConnectionFactoryOptions.builder()
                .option(DRIVER, environment.getRequiredProperty("spring.oracle.r2dbc.datasource.driverType"))
                .option(HOST, environment.getRequiredProperty("spring.oracle.r2dbc.datasource.url"))
                .option(USER, environment.getRequiredProperty("spring.oracle.r2dbc.datasource.username"))
                .option(PASSWORD, environment.getRequiredProperty("spring.oracle.r2dbc.datasource.password"))
                .build());
    }
}

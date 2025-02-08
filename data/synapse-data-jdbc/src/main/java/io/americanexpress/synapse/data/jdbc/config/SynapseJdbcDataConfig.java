package io.americanexpress.synapse.data.jdbc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Configuration class for setting up JDBC data source and enabling transaction management.
 *
 * @author John Robert Martinez Ponce
 */
@Configuration
@ComponentScan(basePackages = "io.americanexpress.synapse.data.jdbc")
@EnableTransactionManagement
public class SynapseJdbcDataConfig {

    /**
     * Creates and configures a {@link DataSource} bean.
     * <p>
     * This method uses properties prefixed with 'spring.datasource' from the application's
     * configuration to build the data source. The {@link ConfigurationProperties} annotation
     * binds the external properties to the data source builder.
     * </p>
     *
     * @return a configured {@link DataSource} instance
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
}
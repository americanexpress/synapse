package io.americanexpress.data.oracle.book.config;

import io.americanexpress.synapse.data.oracle.config.BaseOracleDataConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@PropertySource("classpath:data-oracle-book-application.properties")
@EnableJpaRepositories(basePackages = "io.americanexpress.data.oracle.book")
@EnableAutoConfiguration
public class DataBookConfig extends BaseOracleDataConfig {

    /**
     * Instantiates a new Base postgres data config.
     *
     * @param environment      the environment
     */
    public DataBookConfig(Environment environment) {
        super(environment);
    }

    @Override
    protected void setPackagesToScan(org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        entityManagerFactoryBean.setPackagesToScan("io.americanexpress.data.oracle.book.entity");
    }
}

package io.americanexpress.data.oracle.book.config;

import io.americanexpress.synapse.data.oracle.config.BaseOracleDataConfig;
import io.americanexpress.synapse.data.oracle.config.OracleProperties;
import io.americanexpress.synapse.framework.test.model.ProfileConstants;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@PropertySource("classpath:data-oracle-book-application.properties")
@EnableJpaRepositories(basePackages = "io.americanexpress.data.oracle.book")
@EnableAutoConfiguration
@Import({OracleProperties.class})
@Profile(ProfileConstants.NOT_TEST)
public class OracleDataBookConfig extends BaseOracleDataConfig {

    /**
     * Instantiates a new Base postgres data config.
     *
     * @param environment      the environment
     * @param oracleProperties
     */
    public OracleDataBookConfig(Environment environment, OracleProperties oracleProperties) {
        super(environment, oracleProperties);
    }

    @Override
    protected void setPackagesToScan(org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        entityManagerFactoryBean.setPackagesToScan("io.americanexpress.data.oracle.book.entity");
    }
}

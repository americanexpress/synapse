package io.americanexpress.data.book.config;

import io.americanexpress.synapse.data.postgres.config.BasePostgresDataConfig;
import io.americanexpress.synapse.framework.test.model.ProfileConstants;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
@PropertySource("classpath:/data-book-application.properties")
@EnableJpaRepositories(basePackages = DataBookConfig.PACKAGE_NAME)
@EnableAutoConfiguration
@Profile(ProfileConstants.NOT_TEST)
public class DataBookConfig extends BasePostgresDataConfig {


    /**
     * The Package name.
     */
    static final String PACKAGE_NAME = "io.americanexpress.data.book";

    /**
     * Instantiates a new Data training config.
     *
     * @param environment the environment
     */
    public DataBookConfig(Environment environment) {
        super(environment);
    }

    /**
     * Set the packages to Scan property to the entityManagerFactory.
     *
     * @param entityManagerFactoryBean the entity manager factory bean
     */
    @Override
    protected void setPackagesToScan(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        entityManagerFactoryBean.setPackagesToScan(PACKAGE_NAME + ENTITY_PACKAGE_NAME);
    }
}

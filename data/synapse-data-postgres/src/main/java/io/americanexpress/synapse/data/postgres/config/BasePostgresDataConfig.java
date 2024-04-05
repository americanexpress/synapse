/*
 * Copyright 2020 American Express Travel Related Services Company, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.americanexpress.synapse.data.postgres.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * <code>BasePostgresDataConfig</code> class is used to hold the common configuration for all data-postgres modules.
 *
 * @author Gabriel Jimenez
 */
@Configuration
@EnableTransactionManagement
public abstract class BasePostgresDataConfig {

    protected static final String ENTITY_PACKAGE_NAME = ".entity";

    /**
     * Used to acquire environment variables.
     */
    @Autowired
    protected Environment environment;

    /**
     * Instantiates a new Base postgres data config.
     *
     * @param environment the environment
     */
    public BasePostgresDataConfig(Environment environment) {
        this.environment = environment;
    }

    /**
     * Used to create and edit the DataSource bean.
     *
     * @return DataSource bean
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        HikariDataSource dataSource = DataSourceBuilder.create().type(HikariDataSource.class).build();
        dataSource.setSchema(environment.getRequiredProperty("spring.jpa.properties.hibernate.default_schema"));
        dataSource.setLeakDetectionThreshold(2000);
        dataSource.setDataSourceProperties(additionalHibernateSpringProperties());
        return dataSource;
    }

    /**
     * Used to create a Properties object and add environment variable to that property object.
     *
     * @return Properties object
     */
    private Properties additionalHibernateSpringProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        properties.setProperty("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.setProperty("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.setProperty("spring.datasource.initialization-mode", environment.getRequiredProperty("spring.datasource.initialization-mode"));
        properties.setProperty("hibernate.cache.use_query_cache", "true");
        properties.setProperty("hibernate.cache.provider_class", "org.ehcache.hibernate.EhCacheProvider");
        return properties;
    }

    /**
     * Used to create and edit the LocalContainerEntityManagerFactoryBean.
     *
     * @return LocalContainerEntityManagerFactoryBean
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        setPackagesToScan(entityManagerFactoryBean);
        return entityManagerFactoryBean;
    }

    /**
     * Set the packages to Scan property to the entityManagerFactory.
     *
     * @param entityManagerFactoryBean
     */
    protected abstract void setPackagesToScan(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean);
}

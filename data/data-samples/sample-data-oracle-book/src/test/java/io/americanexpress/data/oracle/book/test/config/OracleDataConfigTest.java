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
package io.americanexpress.data.oracle.book.test.config;

import com.zaxxer.hikari.HikariDataSource;
import io.americanexpress.synapse.data.oracle.config.BaseOracleDataConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * {code OracleDataConfigTest} Configuration file uses h2 database for integration test purpose.
 */
@TestConfiguration
@PropertySource("classpath:data-oracle-book-application-test.properties")
@ActiveProfiles("test")
public class OracleDataConfigTest extends BaseOracleDataConfig {
    /**
     * Instantiates a new Base postgres data config.
     *
     * @param environment  the environment
     */
    public OracleDataConfigTest(Environment environment) {
        super(environment);
    }

    /**
     * Used to create and edit the DataSource bean.
     *
     * @return DataSource bean
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.h2")
    @Override
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
        properties.setProperty("spring.datasource.h2.initialization-mode", environment.getRequiredProperty("spring.datasource.h2.initialization-mode"));
        properties.setProperty("hibernate.cache.use_query_cache", "true");
        properties.setProperty("hibernate.cache.provider_class", "net.sf.ehcache.hibernate.EhCacheProvider");
        return properties;
    }


    /**
     * Set the packages to Scan property to the entityManagerFactory.
     *
     * @param entityManagerFactoryBean
     */
    @Override
    protected void setPackagesToScan(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        entityManagerFactoryBean.setPackagesToScan("io.americanexpress.data.oracle.book.entity");
    }
}

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
package io.americanexpress.data.mysql.book.config;

import com.zaxxer.hikari.HikariDataSource;
import io.americanexpress.synapse.data.mysql.config.BaseMySqlDataConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * {code MySqlDataConfigTest} Configuration file uses h2 database for integration test purpose.
 */
@TestConfiguration
@TestPropertySource("classpath:data-mysql-book-application-test.properties")
@EnableJpaRepositories(basePackages = "io.americanexpress.data.mysql.book.dao")
@ActiveProfiles("test")
public class MySqlDataConfigTest extends BaseMySqlDataConfig {
    /**
     * Instantiates a new Base postgres data config.
     *
     * @param environment  the environment
     */
    public MySqlDataConfigTest(Environment environment) {
        super(environment);
    }

    /**
     * Used to create and edit the DataSource bean.
     *
     * @return DataSource bean
     */
    @Bean()
    @ConfigurationProperties(prefix = "spring.datasource.h2")
    @Override
    public DataSource dataSource() {
        HikariDataSource dataSource = DataSourceBuilder.create().type(HikariDataSource.class).build();
        dataSource.setSchema("PUBLIC");
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
        properties.setProperty("hibernate.hbm2ddl.auto", "none");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("spring.datasource.h2.initialization-mode", "always");
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
        entityManagerFactoryBean.setPackagesToScan("io.americanexpress.data.mysql.book.entity");
    }
}

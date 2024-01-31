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
package io.americanexpress.synapse.data.db2.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import java.util.Properties;

/**
 * {@code BaseDb2Config} contains base level configurations for db2 required for all usage of this data module.
 * @author tisla4
 */
@Configuration
@EnableTransactionManagement
public abstract class BaseDb2Config {

    protected final Environment environment;

    /**
     * Creates a new instance of {@code BaseDb2Config} with the given parameters.
     * @param environment the environment
     */
    protected BaseDb2Config(Environment environment) {
        this.environment = environment;
    }

    /**
     * Used to create and edit the datasource bean
     * @return a new instance of {@link HikariDataSource} configured for the required database connection
     */
    @Bean
    public HikariDataSource dataSource() {
        var hikariDataSource = DataSourceBuilder.create().type(HikariDataSource.class).build();

        hikariDataSource.setUsername(environment.getProperty("spring.datasource.username"));
        hikariDataSource.setPassword(environment.getProperty("spring.datasource.password"));
        hikariDataSource.setJdbcUrl(environment.getProperty("spring.datasource.url"));
        hikariDataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));

        addAdditionalProperties(hikariDataSource);

        return hikariDataSource;
    }

    /**
     * Used to configure the transaction manager to handle operations on the database entities.
     * @return a new instance of {@link PlatformTransactionManager} configured for the required database connection.
     */
    @Bean
    @DependsOn("entityManagerFactory")
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactoryBean.getObject());

        return jpaTransactionManager;
    }

    /**
     * Used to configure how the database entities within the application are managed and helps set up the underlying operations on them.
     * @return a new instance of {@link LocalContainerEntityManagerFactoryBean} configured for the required database connection.
     */
    @Bean
    @DependsOn("dataSource")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(HikariDataSource hikariDataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(hikariDataSource);
        entityManagerFactory.setJpaProperties(setJpaProperties());
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        setPackagesToScan(entityManagerFactory);
        return entityManagerFactory;
    }

    /**
     * Used to specify the hibernate behavior for DB2.
     * @return a {@link Properties} object specifying persistence operations and handling for entities.
     */
    private Properties setJpaProperties() {
        var jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", environment.getProperty("spring.jpa.properties.hibernate.dialect"));
        jpaProperties.put("hibernate.hbm2ddl.auto", environment.getProperty("spring.jpa.hibernate.ddl-auto"));
        return jpaProperties;
    }

    /**
     * Can be overridden by child classes to add other configurations as needed per use case
     * @param hikariDataSource the data source
     */
    protected void addAdditionalProperties(HikariDataSource hikariDataSource) {
    }

    /**
     * Set the packages to scan property to the entityManagerFactory.
     *
     * @param entityManagerFactory the entity manager factory
     */
    protected abstract void setPackagesToScan(LocalContainerEntityManagerFactoryBean entityManagerFactory);
}

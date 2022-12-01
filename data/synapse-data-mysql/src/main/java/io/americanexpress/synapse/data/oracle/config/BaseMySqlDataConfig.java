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
package io.americanexpress.synapse.data.oracle.config;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * {@code BaseMySqlDataConfig} class is used to hold the common configuration for all data-mysql modules.
 *
 * @author Gabriel Jimenez
 */
@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
public abstract class BaseMySqlDataConfig {

    /**
     * Used to acquire environment variables.
     */
    protected final Environment environment;

    /**
     * Instantiates a new Base postgres data config.
     *
     * @param environment the environment
     */
    protected BaseMySqlDataConfig(Environment environment) {
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
        MysqlDataSource dataSource = DataSourceBuilder
                .create()
                .type(MysqlDataSource.class)
                .build();

        dataSource.setURL(environment.getRequiredProperty("spring.mysql.datasource.url"));
//        dataSource.setServerName(environment.getRequiredProperty("spring.mysql.datasource.serviceName"));
        dataSource.setUser(environment.getRequiredProperty("spring.mysql.datasource.username"));
        dataSource.setPassword(environment.getRequiredProperty("spring.mysql.datasource.password"));
        return dataSource;
    }

    /**
     * Used to create and edit the LocalContainerEntityManagerFactoryBean.
     *
     * @return LocalContainerEntityManagerFactoryBean
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        this.setPackagesToScan(entityManagerFactoryBean);
        return entityManagerFactoryBean;
    }

    /**
     * Set the packages to Scan property to the entityManagerFactory.
     *
     * @param entityManagerFactoryBean
     */
    protected abstract void setPackagesToScan(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean);
}

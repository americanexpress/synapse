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

import oracle.jdbc.pool.OracleDataSource;
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

/**
 * <code>BasePostgresDataConfig</code> class is used to hold the common configuration for all data-postgres modules.
 *
 * @author Gabriel Jimenez
 */
@Configuration
@EnableTransactionManagement
public abstract class BaseOracleDataConfig {

    /**
     * Used to acquire environment variables.
     */
    protected Environment environment;

    private final OracleProperties oracleProperties;

    /**
     * Instantiates a new Base postgres data config.
     *
     * @param environment the environment
     */
    @Autowired
    public BaseOracleDataConfig(Environment environment, OracleProperties oracleProperties) {
        this.environment = environment;
        this.oracleProperties = oracleProperties;
    }

    /**
     * Used to create and edit the DataSource bean.
     *
     * @return DataSource bean
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        OracleDataSource dataSource = DataSourceBuilder
                .create()
                .type(OracleDataSource.class)
                .build();
        dataSource.setURL(oracleProperties.getUrl());
        dataSource.setUser(oracleProperties.getUsername());
        dataSource.setPassword(oracleProperties.getPassword());
        dataSource.setDriverType(oracleProperties.getDriverType());
        dataSource.setPortNumber(oracleProperties.getPortNumber());

//        dataSource.setURL(environment.getRequiredProperty("spring.oracle.datasource.url"));
//        dataSource.setUser(environment.getRequiredProperty("spring.oracle.datasource.username"));
//        dataSource.setPassword(environment.getRequiredProperty("spring.oracle.datasource.password"));
//        dataSource.setDriverType(environment.getRequiredProperty("spring.oracle.datasource.drivertype"));
//        dataSource.setPortNumber(Integer.parseInt(environment.getRequiredProperty("spring.oracle.datasource.portnumber")));
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

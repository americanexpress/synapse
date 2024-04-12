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
package io.americanexpress.synapse.data.mssql.config;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * {@code BaseMicrosoftSQLDataConfig} class is used to hold the common configuration for all data-mssql modules.
 */
@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
public abstract class BaseMicrosoftSQLDataConfig {

    /**
     * Used to acquire environment variables.
     */
    private final Environment environment;

    /**
     * Instantiates a new Base microsoft sql data config.
     *
     * @param environment the environment
     */
    public BaseMicrosoftSQLDataConfig(Environment environment) {
        this.environment = environment;
    }

    /**
     * Used to create and edit the DataSource bean.
     * Create connection for connecting to Mssql database.
     * Customization to Mssql database connection can be provided in the spring.datasource.url.
     * Ref to <a href="https://learn.microsoft.com/en-us/sql/connect/jdbc/building-the-connection-url?view=sql-server-ver16">Building the connection URL</a>
     * for customization options.
     * Ex: jdbc:sqlserver://localhost:1433.
     *
     * @return DataSource bean.
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        SQLServerDataSource dataSource = DataSourceBuilder
            .create()
            .type(SQLServerDataSource.class)
            .build();

        dataSource.setURL(environment.getRequiredProperty("spring.mssql.datasource.url"));
        dataSource.setUser(environment.getRequiredProperty("spring.mssql.datasource.username"));
        dataSource.setPassword(environment.getRequiredProperty("spring.mssql.datasource.password"));
        return dataSource;
    }

    /**
     * Used to create and edit the LocalContainerEntityManagerFactoryBean.
     * @param dataSource Database connectivity
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
     * @param entityManagerFactoryBean Used to set entity scanner for JPA.
     */
    protected abstract void setPackagesToScan(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean);
}

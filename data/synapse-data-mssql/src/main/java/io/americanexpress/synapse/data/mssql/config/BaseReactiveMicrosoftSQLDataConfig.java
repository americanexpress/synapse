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

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.r2dbc.ConnectionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;

/**
 * {@code BaseReactiveMicrosoftSQLDataConfig} contains base configuration for connecting to Microsoft SQL (MSSQL) database.
 */
@Configuration
@EnableR2dbcAuditing
@EnableR2dbcRepositories
public abstract class BaseReactiveMicrosoftSQLDataConfig extends AbstractR2dbcConfiguration {

    /**
     * Used to retrieve properties.
     */
    private final Environment environment;

    /**
     * Instantiates a new Base reactive microsoft sql data config.
     *
     * @param environment the environment
     */
    protected BaseReactiveMicrosoftSQLDataConfig(Environment environment) {
        this.environment = environment;
    }

    /**
     * Create connection factory for connecting to Mssql database.
     * Customization to Mssql database connection can be provided in the spring.r2dbc.url.
     * Ref to https://github.com/r2dbc/r2dbc-mssql,
     * https://learn.microsoft.com/en-us/sql/connect/jdbc/building-the-connection-url?view=sql-server-ver16
     * for customization options
     * Ex: r2dbc:sqlserver://localhost:1433/tempdb
     */
    @Bean
    @ConfigurationProperties("spring.r2dbc")
    public ConnectionFactory connectionFactory() {
        return ConnectionFactoryBuilder.withUrl(environment.getRequiredProperty("spring.r2dbc.url"))
                .username(environment.getRequiredProperty("spring.r2dbc.username"))
                .password(environment.getRequiredProperty("spring.r2dbc.password"))
                .build();
    }

    /**
     * Reactive transaction manager.
     *
     * @param connectionFactory the connection factory
     * @return the reactive transaction manager
     */
    @Bean
    ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }

}

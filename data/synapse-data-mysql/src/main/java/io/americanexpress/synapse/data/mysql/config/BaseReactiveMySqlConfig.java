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
package io.americanexpress.synapse.data.mysql.config;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.r2dbc.ConnectionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;

/**
 * {@code BaseReactiveMySqlConfig} class is used to hold the common configuration for all reactive data-mysql modules.
 *
 */
@Configuration
@EnableR2dbcAuditing
public abstract class BaseReactiveMySqlConfig extends AbstractR2dbcConfiguration {

    /**
     * Environment
     */
    private final Environment environment;

    /**
     * Constructor taking in environment.
     * @param environment Environment is loaded to use system properties.
     */
    protected BaseReactiveMySqlConfig(Environment environment) {
        this.environment = environment;
    }

    /**
     * Loads up the ConnectionFactory to connect to the database.
     * @return ConnectionFactory of the database.
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
     * Loads the transaction manager.
     * @param connectionFactory the connection factory
     * @return the reactive transaction manager
     */
    @Bean
    ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }

}

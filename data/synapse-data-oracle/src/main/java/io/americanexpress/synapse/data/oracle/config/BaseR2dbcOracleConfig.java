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

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

/**
 * {@code BaseR2dbcOracleConfig} class is used to hold the common configuration for all reactive data-oracle modules.
 */
@Configuration
@EnableR2dbcRepositories
public abstract class BaseR2dbcOracleConfig extends AbstractR2dbcConfiguration {

    protected final Environment environment;

    public BaseR2dbcOracleConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    @ConfigurationProperties("spring.oracle.r2dbc.datasource")
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(ConnectionFactoryOptions.builder()
                .option(DRIVER, environment.getRequiredProperty("spring.oracle.r2dbc.datasource.driverType"))
                .option(HOST, environment.getRequiredProperty("spring.oracle.r2dbc.datasource.url"))
                .option(USER, environment.getRequiredProperty("spring.oracle.r2dbc.datasource.username"))
                .option(PASSWORD, environment.getRequiredProperty("spring.oracle.r2dbc.datasource.password"))
                .build());
    }
}
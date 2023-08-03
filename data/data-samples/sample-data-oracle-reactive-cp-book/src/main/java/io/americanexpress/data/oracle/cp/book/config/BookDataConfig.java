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
package io.americanexpress.data.oracle.cp.book.config;

import io.americanexpress.data.oracle.cp.book.service.BookPersistenceService;
import io.americanexpress.synapse.data.oracle.config.BaseReactiveOracleConfig;
import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import java.time.Duration;

/**
 * {@code DataBookConfig} example of using BaseReactiveOracleConfig with a custom connection pool.
 */
@Configuration
@PropertySource("classpath:data-oracle-book-application.properties")
@ComponentScan(basePackageClasses = BookPersistenceService.class)
@EnableR2dbcRepositories(basePackages = "io.americanexpress.data.oracle.cp.book.dao")
public class BookDataConfig extends BaseReactiveOracleConfig {

    /**
     * Constructor config passing environment to parent.
     *
     * @param environment      the environment
     */
    protected BookDataConfig(Environment environment) {
        super(environment);
    }

    /**
     * Creates and configures a connection pool for the provided {@link ConnectionFactory}.
     * The connection pool allows efficient management and reuse of database connections,
     * reducing connection establishment overhead and providing better performance database operations.
     * @param connectionFactory The {@link ConnectionFactory} used to create new connections for the pool.
     * @return A new {@link ConnectionPool} instance configured with the specified parameters.
     * @see ConnectionPoolConfiguration
     */
    @Bean
    public ConnectionPool connectionPool(ConnectionFactory connectionFactory) {
        ConnectionPoolConfiguration configPool = ConnectionPoolConfiguration.builder(connectionFactory)
                .maxIdleTime(Duration.ofMillis(1000))
                .maxSize(20)
                .build();

        return new ConnectionPool(configPool);
    }
}

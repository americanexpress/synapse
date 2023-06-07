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

import io.r2dbc.mssql.MssqlConnectionConfiguration;
import io.r2dbc.mssql.MssqlConnectionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

/**
 * {@code BaseReactiveMicrosoftSQLDataConfig} contains base configuration for connecting to Microsoft SQL (MSSQL) database.
 */
@Configuration
@EnableR2dbcRepositories
@EnableR2dbcAuditing
public abstract class BaseReactiveMicrosoftSQLDataConfig {

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
     * Creates a MssqlConnectionFactory configured to connect to Mssql database.
     *
     * @return the mssql connection factory
     */
    public MssqlConnectionFactory mssqlConnectionFactory() {
        return new MssqlConnectionFactory(mssqlConnectionConfiguration());
    }

    /**
     * Creates MssqlConnectionConfiguration with configurations to connect to Mssql database.
     * This method can be overriden to add more configurations based on usecase.
     * Ex: adding ssl - add .enableSsl()
     * Ref https://github.com/r2dbc/r2dbc-mssql for more information on possible configuration.
     *
     * @return the mssql connection configuration
     */
    public MssqlConnectionConfiguration mssqlConnectionConfiguration() {
        return MssqlConnectionConfiguration.builder()
                .host(getHost())
                .port(getPort())
                .username(getUserName())
                .password(getPassword())
                .database(getDatabase())
                .build();
    }

    /**
     * Gets host.
     *
     * @return the host
     */
    public String getHost() {
        return environment.getRequiredProperty("spring.r2dbc.mssql.host");
    }

    /**
     * Gets port.
     *
     * @return the port
     */
    public Integer getPort() {
        return environment.getProperty("spring.r2dbc.mssql.port", Integer.class);
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return environment.getRequiredProperty("spring.r2dbc.mssql.username");
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return environment.getRequiredProperty("spring.r2dbc.mssql.password");
    }

    /**
     * Gets database.
     *
     * @return the database
     */
    public String getDatabase() {
        return environment.getProperty("spring.r2dbc.mssql.database");
    }

}

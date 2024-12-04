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
package io.americanexpress.synapse.data.cassandra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
import org.springframework.data.cassandra.config.EnableCassandraAuditing;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

/**
 * {@code BaseCassandraDataConfig} class is used to hold the common configuration for all data-cassandra modules.
 */
@Configuration
@EnableCassandraRepositories
@EnableCassandraAuditing
public abstract class BaseCassandraDataConfig extends AbstractCassandraConfiguration {

    private static final String SPRING_DATA_CASSANDRA = "spring.data.cassandra.";

    private final Environment environment;

    private final String propertiesPrefix;

    public BaseCassandraDataConfig(Environment environment) {
        this.environment = environment;
        this.propertiesPrefix = SPRING_DATA_CASSANDRA;
    }

    public BaseCassandraDataConfig(Environment environment, String databaseName) {
        this.environment = environment;
        this.propertiesPrefix = SPRING_DATA_CASSANDRA + databaseName + ".";
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.valueOf(environment.getRequiredProperty(propertiesPrefix + "schema-action"));
    }

    @Override
    protected String getKeyspaceName() {
        return environment.getRequiredProperty(propertiesPrefix + "keyspace-name");
    }

    @Override
    protected String getContactPoints() {
        return environment.getRequiredProperty(propertiesPrefix + "contact-points");
    }

    @Override
    protected int getPort() {
        return Integer.parseInt(environment.getRequiredProperty(propertiesPrefix + "port"));
    }

    @Override
    protected String getLocalDataCenter() {
        return environment.getRequiredProperty(propertiesPrefix + "local-datacenter");
    }

    protected String getUserName() {
        return environment.getProperty(propertiesPrefix + "username");
    }

    protected String getPassword() {
        return environment.getProperty(propertiesPrefix + "password");
    }

    @Override
    public CqlSessionFactoryBean cassandraSession() {
        CqlSessionFactoryBean bean = super.cassandraSession();
        bean.setUsername(getUserName());
        bean.setPassword(getPassword());
        return bean;
    }

    public abstract String[] getEntityBasePackages();
}

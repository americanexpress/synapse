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

    private final Environment environment;

    public BaseCassandraDataConfig(Environment environment) {
        this.environment = environment;
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.valueOf(environment.getRequiredProperty("spring.data.cassandra.schema-action"));
    }

    @Override
    protected String getKeyspaceName() {
        return environment.getRequiredProperty("spring.data.cassandra.keyspace-name");
    }

    @Override
    protected String getContactPoints() {
        return environment.getRequiredProperty("spring.data.cassandra.contact-points");
    }

    @Override
    protected int getPort() {
        return Integer.parseInt(environment.getRequiredProperty("spring.data.cassandra.port"));
    }

    @Override
    protected String getLocalDataCenter() {
        return environment.getRequiredProperty("spring.data.cassandra.local-datacenter");
    }

    protected String getUserName() {
        return environment.getProperty("spring.data.cassandra.username");
    }

    protected String getPassword() {
        return environment.getProperty("spring.data.cassandra.password");
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

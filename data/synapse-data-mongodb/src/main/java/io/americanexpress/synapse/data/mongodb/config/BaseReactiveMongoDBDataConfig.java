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
package io.americanexpress.synapse.data.mongodb.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;

/**
 * {@code BaseReactiveMongoDBDataConfig} class is used to hold the common configuration for all reactive data-mongodb modules.
 */
@Configuration
@EnableReactiveMongoAuditing
public abstract class BaseReactiveMongoDBDataConfig extends AbstractReactiveMongoConfiguration implements BaseMongoDBConfig {

    /**
     * Used to acquire environment variables.
     */
    protected Environment environment;

    /**
     * Instantiates a new Base reactive MongoDB data config.
     *
     * @param environment the environment
     */
    protected BaseReactiveMongoDBDataConfig(Environment environment) {
        this.environment = environment;
    }

    @Override
    protected String getDatabaseName() {
        return environment.getRequiredProperty("spring.data.mongodb.database");
    }

    @Override
    public MongoClient reactiveMongoClient() {
        ConnectionString connectionString = new ConnectionString(environment.getRequiredProperty("spring.data.mongodb.uri"));
        MongoClientSettings mongoClientSettings = setMongoClientSettings(connectionString);
        return this.createReactiveMongoClient(mongoClientSettings);
    }
}

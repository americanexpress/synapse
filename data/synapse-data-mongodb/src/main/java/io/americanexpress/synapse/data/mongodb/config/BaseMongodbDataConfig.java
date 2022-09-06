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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * <code>BaseMongodbDataConfig</code> class is used to hold the common configuration for all data-mongodb modules.
 */
@Configuration
@EnableMongoAuditing
@EnableMongoRepositories
public abstract class BaseMongodbDataConfig {

    protected static final String ENTITY_PACKAGE_NAME = ".entity";

    /**
     * Used to acquire environment variables.
     */
    @Autowired
    protected Environment environment;

    /**
     * Instantiates a new Base postgres data config.
     *
     * @param environment the environment
     */
    public BaseMongodbDataConfig(Environment environment) {
        this.environment = environment;
    }
}

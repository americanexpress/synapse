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
package io.americanexpress.data.book.config;


import io.americanexpress.synapse.data.mongodb.config.BaseReactiveMongoDBDataConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

/**
 * BookDataConfig is the configuration class to load all the properties for the book data module.
 */
@Configuration
@PropertySource("classpath:/data-book-application.properties")
@EnableAutoConfiguration
@EnableReactiveMongoRepositories(basePackages = BookDataConfig.PACKAGE_NAME)
public class BookDataConfig extends BaseReactiveMongoDBDataConfig {

    /**
     * The Package name.
     */
    static final String PACKAGE_NAME = "io.americanexpress.data.book";

    /**
     * Instantiates a new Data training config.
     *
     * @param environment the environment
     */
    public BookDataConfig(Environment environment) {
        super(environment);
    }
}

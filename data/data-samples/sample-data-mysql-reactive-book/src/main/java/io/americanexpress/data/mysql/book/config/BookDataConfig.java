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
package io.americanexpress.data.mysql.book.config;

import io.americanexpress.synapse.data.mysql.config.BaseReactiveMySqlConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

/**
 * {@code BookDataConfig} example of using BaseReactiveMySqlConfig
 */
@Configuration
@PropertySource("classpath:data-mysql-book-application.properties")
@EnableR2dbcRepositories(basePackages = "io.americanexpress.data.mysql.book.dao")
public class BookDataConfig extends BaseReactiveMySqlConfig {
    /**
     * Instantiates a new Base postgres data config.
     *
     * @param environment      the environment.
     */
    public BookDataConfig(Environment environment) {
        super(environment);
    }

}

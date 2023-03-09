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

import com.fasterxml.jackson.databind.ObjectMapper;
import io.americanexpress.synapse.data.couchbase.config.BaseReactiveCouchbaseDataConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.couchbase.repository.config.EnableReactiveCouchbaseRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static io.americanexpress.data.book.config.BookDataConfig.PACKAGE_NAME;

@Configuration
@PropertySource("classpath:/data-book-application.properties")
@EnableReactiveCouchbaseRepositories(basePackages = PACKAGE_NAME)
@EnableTransactionManagement
public class BookDataConfig extends BaseReactiveCouchbaseDataConfig {

    /**
     * The Package name.
     */
    static final String PACKAGE_NAME = "io.americanexpress.data.book";

    protected BookDataConfig(Environment environment, ObjectMapper objectMapper) {
        super(environment, objectMapper);
    }
}

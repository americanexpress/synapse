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

import io.americanexpress.synapse.data.db2.config.BaseDb2Config;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

/**
 * {@code BookDataConfig} is the configuration class to load all the properties for the book data module.
 */
@Configuration
@PropertySource("classpath:/data-book-application.properties")
@EnableJpaRepositories(basePackages = {"io.americanexpress.data.book"})
public class BookDataConfig extends BaseDb2Config {

    /**
     * Creates a new instance of {@code BookDataConfig} with the given parameters.
     * @param environment the environment containing application secrets
     */
    public BookDataConfig(Environment environment) {
        super(environment);
    }

    /**
     * Set the packages to Scan property to the entityManagerFactory.
     *
     * @param entityManagerFactory the entity manager factory
     */
    @Override
    protected void setPackagesToScan(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        entityManagerFactory.setPackagesToScan("io.americanexpress.data.book");
    }
}

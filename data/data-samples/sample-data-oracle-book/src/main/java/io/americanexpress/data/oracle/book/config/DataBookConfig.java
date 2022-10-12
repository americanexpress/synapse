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
package io.americanexpress.data.oracle.book.config;

import io.americanexpress.synapse.data.oracle.config.BaseOracleDataConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

/**
 * {@code DataBookConfig} example of using BaseOracleDataConfig
 */
@Configuration
@PropertySource("classpath:data-oracle-book-application.properties")
@EnableJpaRepositories(basePackages = "io.americanexpress.data.oracle.book.dao")
@EnableAutoConfiguration
public class DataBookConfig extends BaseOracleDataConfig {

    private static final String PACKAGE_SCAN_NAME = "io.americanexpress.data.oracle.book.entity";

    /**
     * Instantiates a new Base postgres data config.
     *
     * @param environment      the environment
     */
    public DataBookConfig(Environment environment) {
        super(environment);
    }

    /**
     * Scans package containing jpa entity
     * @param entityManagerFactoryBean
     */
    @Override
    protected void setPackagesToScan(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        entityManagerFactoryBean.setPackagesToScan(PACKAGE_SCAN_NAME);
    }
}

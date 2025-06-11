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
package io.americanexpress.synapse.data.postgres.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

/**
 * Configuration class for the sample Postgres data module.
 * <p>
 * Loads properties from {@code kocho-application.properties}, enables JPA repositories,
 * and configures entity scanning for the {@code io.americanexpress.synapse.data.postgres.entity} package.
 * </p>
 *
 * @author Aziz Ali
 */
@Configuration
@PropertySource("classpath:/kocho-application.properties")
@EnableJpaRepositories(basePackages = "io.americanexpress.synapse.data.postgres.entity")
@EnableAutoConfiguration
public class DataSampleConfig extends BasePostgresDataConfig {

    static final String PACKAGE_NAME = "io.americanexpress.synapse.data.postgres";

    public DataSampleConfig(Environment environment) {
        super(environment);
    }

    @Override
    protected void setPackagesToScan(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        entityManagerFactoryBean.setPackagesToScan(PACKAGE_NAME + ENTITY_PACKAGE_NAME);
    }
}

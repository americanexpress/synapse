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
package ${package}.config;

import io.americanexpress.synapse.data.postgres.config.BasePostgresDataConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;


/**
 * {@code ${className}DataConfig} class sets the configurations for the data module.
 * @author ${author}
 */
@PropertySource("classpath:data-application.properties")
@Configuration
@EnableJpaRepositories(basePackages = "${package}")
@EnableAutoConfiguration
@EnableJpaAuditing
public class ${className}DataConfig extends BasePostgresDataConfig{

    /**
     * Set the packages to Scan property to the localContainerEntityManagerFactoryBean.
     *
     * @param localContainerEntityManagerFactoryBean the LocalContainerEntityManagerFactoryBean
     */
    @Override
    protected void setPackagesToScan(LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
            localContainerEntityManagerFactoryBean.setPackagesToScan(PACKAGE_NAME + ENTITY_PACKAGE_NAME);
    }

}

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

import io.americanexpress.synapse.service.rest.config.BaseObservabilityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * {@code ${className}ObservabilityConfig} class sets the
 * configuration setting up Tanzu Wavefront.
 * @author ${author}
 */
@Configuration
@PropertySource("classpath:/service-application.properties")
public class ${className}ObservabilityConfig extends BaseObservabilityConfig {
    public ${className}ObservabilityConfig(Environment environment) {
        super(environment);
    }
}
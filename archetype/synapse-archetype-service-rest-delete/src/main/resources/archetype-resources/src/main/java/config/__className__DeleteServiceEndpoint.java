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

import io.americanexpress.synapse.service.rest.config.ServiceRestConfig;
import ${package}.service.${className}DeleteService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * {@code ${className}DeleteServiceEndpoint} class sets the endpoints
 * for the {@link ${className}DeleteService}.
 * @author ${author}
 */
public class ${className}DeleteServiceEndpoint {
    /**
     * The constant BASE_URL
     */
    public static final String BASE_URL = "${baseUrl}";
}
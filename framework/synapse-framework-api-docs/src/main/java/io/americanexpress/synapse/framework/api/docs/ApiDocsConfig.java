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
package io.americanexpress.synapse.framework.api.docs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * This class is used to configure Swagger documentation.
 *
 * @author Gabriel Jimenez
 */
@Configuration
public class ApiDocsConfig {

    // These variables are defaulted to below values,
    // but could be overridden and changes by client utilizing this framework.
    private static final String teamName = "Team Synapse";
    private static final String teamWebsite = "https://americanexpress.io/synapse/";
    private static final String teamEmailAddress = "synapse@aexp.com";
    public static final String title = "Synapse APIs";
    private static final String description = "These are the specifications of the Synapse APIs";


    /**
     * This is default implementation of the OpenAPIDocumentation.
     * This could and should be overwritten in the API Implementing the base service framework that adds this as
     * a dependency.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title(title)
                        .description(description)
                        .license(new License().name("Apache 2.0").url(teamWebsite))
                        .contact(new Contact().name(teamName).url(teamWebsite).email(teamEmailAddress)));
    }
}


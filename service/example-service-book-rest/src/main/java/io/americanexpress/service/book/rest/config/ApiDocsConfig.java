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
package io.americanexpress.service.book.rest.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * This class is used to configure Swagger documentation.
 *
 * @author Gabriel Jimenez
 */
@OpenAPIDefinition(info = @io.swagger.v3.oas.annotations.info.Info(title = ApiDocsConfig.title, version = "v1.0.0", description = "Synapse Service"))
@Configuration
public class ApiDocsConfig {

    private static final String CORRELATION_ID = "Correlation-ID";
    private static final String CLIENT_APP_ID = "Client-App-ID";
    private static final String HEADER = "Header";

    // These variables are defaulted to below values, but could be overridden and changes by client utilizing this framework.
    private String teamName = "Team Synapse";
    private String teamWebsite = "https://americanexpress.io/synapse/";
    private String teamEmailAddress = "synapse@aexp.com";
    public static final String title = "Synapse APIs";
    private String description = "These are the specifications of the Synapse APIs";


    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamWebsite() {
        return teamWebsite;
    }

    public void setTeamWebsite(String teamWebsite) {
        this.teamWebsite = teamWebsite;
    }

    public String getTeamEmailAddress() {
        return teamEmailAddress;
    }

    public void setTeamEmailAddress(String teamEmailAddress) {
        this.teamEmailAddress = teamEmailAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    @Bean
//    public GroupedOpenApi publicApi() {
//        String[] packagesToScan = {"io.americanexpress.synapse.service", "io.americanexpress.service.book.rest.controller", "io.americanexpress.service.book.rest.service"};
//        return builder()
//                .group("synapse")
//                .packagesToScan(packagesToScan)
//                .pathsToMatch("/v1")
////                .addOperationCustomizer(endpointCustomizer)
//                .build();
//    }

    @Bean
    public OpenAPI synapseOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("test2")
                        .description("test")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url(teamWebsite)))
                .externalDocs(new ExternalDocumentation()
                        .description("test3")
                        .url(teamWebsite));

    }


}


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
package com.americanexpress.synapse.framework.api.docs;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * This class is used to configure Swagger documentation.
 *
 * @author Gabriel Jimenez
 */
@Configuration
@EnableSwagger2
public class ApiDocsConfig {

    private String teamName = "Synapse";
    private String teamWebsite = "https://synapse-team-website";
    private String teamEmailAddress = "Gabriel.A.Jimenez@aexp.com";
    private String title = "Synapse APIs";
    private String description = "These are the specifications of the Synapse APIs";

    private static final String CORRELATION_ID = "Correlation-ID";
    private static final String CLIENT_APP_ID = "Client-App-ID";
    private static final String HEADER = "Header";


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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Used to get basic information on the page.
     *
     * @return group name, headers, end points, etc.
     */
    @Bean
    public Docket synapseApis() {
        //Register the REST controllers to use Swagger
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInformation())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(getApiKeys())
                .securityContexts(Lists.newArrayList(getSecurityContext()));
    }

    /**
     * Used to return the API information such as contact details, description, etc.
     *
     * @return the API information
     */
    private ApiInfo getApiInformation() {
        Contact contact = new Contact(getTeamName(), getTeamWebsite(), getTeamEmailAddress());
        return new ApiInfoBuilder().title(getTitle())
                .description(getDescription())
                .contact(contact)
                .version("1.0")
                .build();
    }

    /**
     * Used to get the headers for the API.
     *
     * @return the API keys
     */
    private List<ApiKey> getApiKeys() {
        return Lists.newArrayList(new ApiKey(CORRELATION_ID, CORRELATION_ID, HEADER),
                new ApiKey(CLIENT_APP_ID, CLIENT_APP_ID, HEADER));
    }

    /**
     * Used to provide default authorization to each API operation.
     *
     * @return the security context
     */
    private SecurityContext getSecurityContext() {
        return SecurityContext.builder().securityReferences(getDefaultSecurityReferences()).forPaths(PathSelectors.any()).build();
    }

    /**
     * Gets the default security references to pass headers values for authorization.
     *
     * @return the list of new headers those are need for global authorization
     */
    private List<SecurityReference> getDefaultSecurityReferences() {
        AuthorizationScope[] authorizationScopes = {};
        return Lists.newArrayList(new SecurityReference(CORRELATION_ID, authorizationScopes),
                new SecurityReference(CLIENT_APP_ID, authorizationScopes));
    }


}


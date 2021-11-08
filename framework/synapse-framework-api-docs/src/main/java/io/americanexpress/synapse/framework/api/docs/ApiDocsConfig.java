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

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RestController;


/**
 * This class is used to configure Swagger documentation.
 *
 * @author Gabriel Jimenez
 */
@Configuration
@PropertySource("classpath:/framework-api-docs-application.properties")
public class ApiDocsConfig {

    private static final String CORRELATION_ID = "Correlation-ID";
    private static final String CLIENT_APP_ID = "Client-App-ID";
    private static final String HEADER = "Header";

    // These variables are defaulted to below values, but could be overridden and changes by client utilizing this framework.
    private String teamName = "Team Synapse";
    private String teamWebsite = "https://americanexpress.io/synapse/";
    private String teamEmailAddress = "synapse@aexp.com";
    private String title = "Synapse APIs";
    private String description = "These are the specifications of the Synapse APIs";

//    @Value("${prop.swagger.enabled:true}")
//    private boolean enableSwagger;


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
//
//
//    /**
//     * Used to get basic information on the page.
//     *
//     * @return group name, headers, end points, etc.
//     */
//    @Bean
//    public Docket synapseApi() {
//        //Register the REST controllers to use Swagger
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(this.getApiInformation())
//                .select()
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
//                .paths(PathSelectors.any())
//                .build()
//                .securitySchemes(getApiKeys())
//                .securityContexts(Lists.newArrayList(this.getSecurityContext()));
//    }
//
//    /**
//     * Used to return the API information such as contact details, description, etc.
//     *
//     * @return the API information
//     */
//    private ApiInfo getApiInformation() {
//        Contact contact = new Contact(this.getTeamName(), this.getTeamWebsite(), this.getTeamEmailAddress());
//        return new ApiInfoBuilder().title(this.getTitle())
//                .description(this.getDescription())
//                .contact(contact)
//                .version("1.0")
//                .build();
//    }
//
////    private Predicate<String> postPaths() {
//////        return or(regex("/api/posts.*"), regex("/api/javainuse.*"));
////    }
//
//    /**
//     * Used to get the headers for the API.
//     *
//     * @return the API keys
//     */
//    private List<SecurityScheme> getApiKeys() {
//        return Lists.newArrayList(new ApiKey(CORRELATION_ID, CORRELATION_ID, HEADER),
//                new ApiKey(CLIENT_APP_ID, CLIENT_APP_ID, HEADER));
//    }
//
//    /**
//     * Used to provide default authorization to each API operation.
//     *
//     * @return the security context
//     */
//    private SecurityContext getSecurityContext() {
//        return SecurityContext.builder().securityReferences(getDefaultSecurityReferences()).forPaths(PathSelectors.any()).build();
//    }
//
//    /**
//     * Gets the default security references to pass headers values for authorization.
//     *
//     * @return the list of new headers those are need for global authorization
//     */
//    private List<SecurityReference> getDefaultSecurityReferences() {
//        AuthorizationScope[] authorizationScopes = {};
//        return Lists.newArrayList(new SecurityReference(CORRELATION_ID, authorizationScopes),
//                new SecurityReference(CLIENT_APP_ID, authorizationScopes));
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password(passwordEncoder().encode("user")).roles("USER")
//                .and()
//                .withUser("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("USER", "ADMIN");
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    private static final String[] AUTH_LIST = {
//            "/swagger-resources/**",
//            "/swagger-ui.html**",
//            "/v2/api-docs",
//            "/v3/api-docs",
//            "/webjars/**"
//    };

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        if (enableSwagger)
//            web.ignoring().antMatchers("/v2/api-docs",
//                    "/configuration/ui",
//                    "/swagger-resources/**",
//                    "/configuration/security",
//                    "/swagger-ui.html",
//                    "/webjars/**");
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers(AUTH_LIST).authenticated().and().httpBasic();
//    }

    @Bean
    public GroupedOpenApi publicApi() {
        String[] packagesToScan = {"io.americanexpress.service"};

        return GroupedOpenApi.builder()
                .group("synapse")
                .pathsToMatch("/public/**")
                .packagesToScan(packagesToScan)
                .pathsToMatch("/v1")
                .build();
    }

    @Bean
    public OpenAPI synapseOpenAPI() {
        return new OpenAPI()
                .info(new Info().title(title)
                        .description(description)
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url(teamWebsite)))
                .externalDocs(new ExternalDocumentation()
                        .description(description)
                        .url(teamWebsite));
    }


}


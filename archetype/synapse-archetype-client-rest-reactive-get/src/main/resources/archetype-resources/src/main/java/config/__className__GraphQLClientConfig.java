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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import ${package}.client.${className}GraphQLClient;
import ${package}.handler.${className}GraphQLResponseErrorHandler;

import io.americanexpress.synapse.client.rest.config.BaseRestClientConfig;

/**
 * {@code ${className}GraphQLClientConfig} class sets the configurations
 * for the {@link ${className}GraphQLClient}.
 * @author ${author}
 */
@Import(BaseRestClientConfig.class)
@PropertySource("classpath:client-application.properties")
@ComponentScan("${package}")
@Configuration
public class ${className}GraphQLClientConfig extends BaseRestClientConfig {

	/**
	 * Used to connect to the ${apiName} GraphQL API.
	 */
	private final ${className}GraphQLClient graphQLClient;
	
	/**
	 * Used to handle errors from the ${apiName} GraphQL API.
	 */
	private final ${className}GraphQLResponseErrorHandler graphQLResponseErrorHandler;
	
	/**
	 * Argument constructor creates a new instance of ${className}GraphQLClientConfig with given values.
	 * @param graphQLClient used to connect to the ${apiName} GraphQL API
	 * @param graphQLResponseErrorHandler used to handle errors from the ${apiName} GraphQL API
	 */
	@Autowired
	public ${className}GraphQLClientConfig(${className}GraphQLClient graphQLClient, ${className}GraphQLResponseErrorHandler graphQLResponseErrorHandler) {
		this.graphQLClient = graphQLClient;
		this.graphQLResponseErrorHandler = graphQLResponseErrorHandler;
	}
	
	/**
	 * Initialize this GraphQL client.
	 */
	@Value("${client.url}")
	@Override
	protected void initialize(String destinationUrl) {
		initializeClient(destinationUrl, graphQLClient, graphQLResponseErrorHandler);
	}
}

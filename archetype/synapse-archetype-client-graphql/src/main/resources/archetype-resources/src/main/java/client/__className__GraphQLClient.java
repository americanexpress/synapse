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
package ${package}.client;

import org.springframework.beans.factory.annotation.Autowired;

import ${package}.factory.${className}ClientHttpHeadersFactory;
import ${package}.model.${className}GraphQLClientResponse;
import ${package}.model.${className}GraphQLData;

import io.americanexpress.synapse.client.graphql.client.BaseGraphQLClient;
import io.americanexpress.synapse.client.graphql.client.GraphQLClient;

/**
 * {@code ${className}GraphQLClient} class is the client
 * used to query from the ${apiName} GraphQL API.
 * @author ${author}
 *
 */
@GraphQLClient
public class ${className}GraphQLClient extends BaseGraphQLClient<${className}GraphQLData, ${className}GraphQLClientResponse, ${className}ClientHttpHeadersFactory> {

	/**
     * Argument constructor creates a new instance of ${className}GraphQLClient with given values.
     * @param clientHttpHeadersFactory HTTP headers factory used to produce the custom HTTP headers required to consume the back end service
     */
	@Autowired
	public ${className}GraphQLClient(${className}ClientHttpHeadersFactory clientHttpHeadersFactory) {
		super(clientHttpHeadersFactory);
	}
}

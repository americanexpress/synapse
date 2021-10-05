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

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ${package}.config.${className}GraphQLClientTestConfig;
import ${package}.model.${className}GraphQLClientResponse;

import io.americanexpress.synapse.client.graphql.model.GraphQLClientRequest;
import io.americanexpress.synapse.client.rest.model.ClientHeaders;
import io.americanexpress.synapse.framework.test.CommonAssertionMessages;
import io.americanexpress.synapse.utilities.common.io.IOUtils;

/**
 * {@code ${className}GraphQLClientIT} class performs integration tests
 * for the {@link ${className}GraphQLClient}.
 * 
 * This sample integration test below assumes that you have specified
 * a GraphQL query in {@code src/test/resources}.
 * @author ${author}
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ${className}GraphQLClientTestConfig.class)
class ${className}GraphQLClientIT {

	@Autowired
	private ${className}GraphQLClient graphQLClient;
	
	@Test
	void callMonoService_givenValidRequest_expectedValidResponse() throws Exception {
		String query = IOUtils.readFileToAString("sample-query.txt");
		GraphQLClientRequest graphQLClientRequest = new GraphQLClientRequest();
		graphQLClientRequest.setQuery(query);
		${className}GraphQLClientResponse graphQLClientResponse = graphQLClient.callMonoService(new ClientHeaders(), graphQLClientRequest);
		assertNotNull(graphQLClientResponse, CommonAssertionMessages.VALUE_NULL);
	}
}

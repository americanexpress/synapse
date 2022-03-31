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

import io.americanexpress.synapse.client.rest.model.ClientHeaders;
import io.americanexpress.synapse.framework.test.CommonAssertionMessages;
import reactor.core.publisher.Mono;

import ${package}.config.${className}ReactiveRestClientTestConfig;
import ${package}.model.${className}ClientRequest;
import ${package}.model.${className}ClientResponse;

/**
 * {@code ${className}PutReactiveRestClientIT} class performs integration tests
 * for the {@link ${className}PutReactiveRestClient}.
 * <p>
 * Be sure that the ${apiName} REST API is running
 * prior to running this integration test.
 * @author ${author}
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ${className}ReactiveRestClientTestConfig.class)
class ${className}PutReactiveRestClientIT {

	@Autowired
	private ${className}PutReactiveRestClient reactiveRestClient;
	
	@Test
	void callMonoService_givenValidRequest_expectedValidResponse() throws Exception {
		// TODO: please add any client headers required by the back end API
		ClientHeaders clientHeaders = new ClientHeaders();
		${className}ClientRequest clientRequest = new ${className}ClientRequest();
		Mono<${className}ClientResponse> clientResponseMono = reactiveRestClient.callMonoService(clientHeaders, clientRequest);
		${className}ClientResponse clientResponse = clientResponseMono.block();
		assertNotNull(clientResponse, CommonAssertionMessages.VALUE_NULL);
	}
}

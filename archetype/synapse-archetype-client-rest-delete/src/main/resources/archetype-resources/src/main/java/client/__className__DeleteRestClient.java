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
import org.springframework.http.HttpMethod;

import io.americanexpress.synapse.client.rest.client.BaseRestClient;
import io.americanexpress.synapse.client.rest.client.RestClient;

import ${package}.factory.${className}ClientHttpHeadersFactory;
import ${package}.model.${className}ClientRequest;
import ${package}.model.${className}ClientResponse;

/**
 * {@code ${className}DeleteRestClient} class is the client
 * used to connect to the ${apiName} REST API.
 * @author ${author}
 *
 */
@RestClient
public class ${className}DeleteRestClient extends BaseRestClient<${className}ClientRequest, ${className}ClientResponse, ${className}ClientHttpHeadersFactory> {

	/**
	 * Argument constructor creates a new instance of ${className}DeleteRestClient with given values.
	 * @param clientHttpHeadersFactory HTTP headers factory used to produce the custom HTTP headers required to consume the back end service
	 */
	@Autowired
	public ${className}DeleteRestClient(${className}ClientHttpHeadersFactory clientHttpHeadersFactory) {
		super(clientHttpHeadersFactory, HttpMethod.GET);
	}
}

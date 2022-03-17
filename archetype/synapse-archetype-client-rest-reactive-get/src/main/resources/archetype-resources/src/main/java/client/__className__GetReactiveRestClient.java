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

import io.americanexpress.synapse.client.rest.client.BaseGetReactiveRestClient;
import io.americanexpress.synapse.client.rest.client.ReactiveRestClient;

import ${package}.factory.${className}ClientHttpHeadersFactory;
import ${package}.handler.${className}ReactiveRestResponseErrorHandler;
import ${package}.model.${className}ClientRequest;
import ${package}.model.${className}ClientResponse;

/**
 * {@code ${className}GetReactiveRestClient} class is the client
 * used to query from the ${apiName} REST API.
 * @author ${author}
 *
 */
@ReactiveRestClient
public class ${className}GetReactiveRestClient extends BaseGetReactiveRestClient<${className}ClientRequest, ${className}ClientResponse, ${className}ClientHttpHeadersFactory> {

	/**
     * Argument constructor creates a new instance of ${className}GetReactiveRestClient with given values.
     * @param clientHttpHeadersFactory HTTP headers factory used to produce the custom HTTP headers required to consume the back end service
     * @param reactiveRestResponseErrorHandler used to handle errors from the reactive REST client
     */
	@Autowired
	public ${className}GetReactiveRestClient(${className}ClientHttpHeadersFactory clientHttpHeadersFactory, ${className}ReactiveRestResponseErrorHandler reactiveRestResponseErrorHandler) {
		super(clientHttpHeadersFactory, reactiveRestResponseErrorHandler);
	}
}

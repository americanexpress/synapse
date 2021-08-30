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
package io.americanexpress.synapse.client.rest.client;

import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import io.americanexpress.synapse.client.rest.factory.BaseClientHttpHeadersFactory;
import io.americanexpress.synapse.client.rest.model.BaseClientRequest;
import io.americanexpress.synapse.client.rest.model.BaseClientResponse;
import io.americanexpress.synapse.client.rest.model.ClientHeaders;
import io.americanexpress.synapse.client.rest.model.QueryParameter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * {@code BaseReactiveRestClient} class specifies the prototypes for all reactive REST clients.
 *
 * @param <I> input request type
 * @param <O> output response type
 * @param <H> httpHeadersFactory used to set the HTTP headers for each web service call
 * @author Paolo Claudio
 */
public abstract class BaseReactiveRestClient<I extends BaseClientRequest, O extends BaseClientResponse, H extends BaseClientHttpHeadersFactory<I>> extends BaseClient<I, O, H> {

	/**
	 * Used to connect to a back end web service in a non-blocking, reactive manner.
	 */
	protected WebClient webClient;
	
	/**
	 * Argument constructor creates a new instance of BaseReactiveRestClient with given values.
	 * @param httpHeadersFactory HTTP headers factory used to produce the custom HTTP headers required to consume the back end service
     * @param httpMethod HTTP method of the back end service
	 */
	protected BaseReactiveRestClient(H httpHeadersFactory, HttpMethod httpMethod) {
		super(httpHeadersFactory, httpMethod);
	}
	
	/**
	 * Get the webClient.
	 * @return the webClient
	 */
	public WebClient getWebClient() {
		return webClient;
	}
	
	/**
	 * Set the webClient.
	 * @param webClient the webClient to set
	 */
	public void setWebClient(WebClient webClient) {
		this.webClient = webClient;
	}
	
	/**
     * Get the mono response from the service given the HTTP headers and request body.
     *
     * @param clientHeaders      headers for the back end service
     * @param clientRequest      body of the request
     * @param pathVariables      variables needed to be added to URI
     * @return the mono response body from the back end service
     */
	public Mono<O> callMonoService(ClientHeaders clientHeaders, I clientRequest, String... pathVariables) {
		return callMonoService(clientHeaders, clientRequest, null, pathVariables);
	}
	
	/**
     * Get the mono response from the service given the HTTP headers and request body.
     *
     * @param clientHeaders      headers for the back end service
     * @param clientRequest      body of the request
     * @param queryParameters    parameters needed to be added to URI
     * @param pathVariables      variables needed to be added to URI
     * @return the mono response body from the back end service
     */
	public Mono<O> callMonoService(ClientHeaders clientHeaders, I clientRequest, List<QueryParameter> queryParameters, String... pathVariables) {
		
		// Get the updated URL which may change in each client request due to path variables and/or query parameters
		String updatedUrl = UrlBuilder.build(url, queryParameters, pathVariables);
		
		return webClient.method(httpMethod)
			.uri(updatedUrl)
			.headers(httpHeaders ->
				httpHeaders.addAll(httpHeadersFactory.create(clientHeaders, clientRequest, updatedUrl)))
			.body(Mono.just(clientRequest), clientRequestType)
			.retrieve()
			.bodyToMono(clientResponseType);
	}
	
	/**
     * Get the flux of responses from the service given the HTTP headers and request body.
     *
     * @param clientHeaders      headers for the back end service
     * @param clientRequest      body of the request
     * @param pathVariables      variables needed to be added to URI
     * @return the flux of response bodies from the back end service
     */
	public Flux<O> callPolyService(ClientHeaders clientHeaders, I clientRequest, String... pathVariables) {
		return callPolyService(clientHeaders, clientRequest, null, pathVariables);
	}
	
	/**
     * Get the flux of responses from the service given the HTTP headers and request body.
     *
     * @param clientHeaders      headers for the back end service
     * @param clientRequest      body of the request
     * @param queryParameters    parameters needed to be added to URI
     * @param pathVariables      variables needed to be added to URI
     * @return the flux of response bodies from the back end service
     */
	public Flux<O> callPolyService(ClientHeaders clientHeaders, I clientRequest, List<QueryParameter> queryParameters, String... pathVariables) {
		
		// Get the updated URL which may change in each client request due to path variables and/or query parameters
		String updatedUrl = UrlBuilder.build(url, queryParameters, pathVariables);
		
		return webClient.method(httpMethod)
			.uri(updatedUrl)
			.headers(httpHeaders ->
				httpHeaders.addAll(httpHeadersFactory.create(clientHeaders, clientRequest, updatedUrl)))
			.body(Flux.just(clientRequest), clientRequestType)
			.retrieve()
			.bodyToFlux(clientResponseType);
	}
}

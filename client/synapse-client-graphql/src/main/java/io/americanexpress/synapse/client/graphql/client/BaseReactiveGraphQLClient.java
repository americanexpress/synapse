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
package io.americanexpress.synapse.client.graphql.client;

import java.util.List;

import io.americanexpress.synapse.client.graphql.model.BaseGraphQLClientResponse;
import io.americanexpress.synapse.client.graphql.model.BaseGraphQLData;
import io.americanexpress.synapse.client.graphql.model.GraphQLClientRequest;
import io.americanexpress.synapse.client.rest.client.BasePostReactiveRestClient;
import io.americanexpress.synapse.client.rest.handler.BaseReactiveRestResponseErrorHandler;
import io.americanexpress.synapse.client.rest.model.QueryParameter;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Flux;

/**
 * {@code BaseReactiveGraphQLClient} class specifies the prototypes for all non-blocking GraphQL clients.
 *
 * @param <T> type of data in the response
 * @param <O> output response type
 * @author Paolo Claudio
 */
public abstract class BaseReactiveGraphQLClient<T extends BaseGraphQLData, O extends BaseGraphQLClientResponse<T>> extends BasePostReactiveRestClient<GraphQLClientRequest, O> {

	/**
     * Argument constructor creates a new instance of BaseReactiveGraphQLClient with given values.
     * @param reactiveRestResponseErrorHandler used to handle errors from the reactive REST client
     */
	protected BaseReactiveGraphQLClient(BaseReactiveRestResponseErrorHandler reactiveRestResponseErrorHandler) {
		super(reactiveRestResponseErrorHandler);
	}
	
	/**
	 * This operation is unsupported for reactive GraphQL clients.
	 */
	@Override
	public Flux<O> callPolyService(HttpHeaders headers, GraphQLClientRequest clientRequest, String... pathVariables) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * This operation is unsupported for reactive GraphQL clients.
	 */
	@Override
	public Flux<O> callPolyService(HttpHeaders headers, GraphQLClientRequest clientRequest, List<QueryParameter> queryParameters, String... pathVariables) {
		throw new UnsupportedOperationException();
	}
}

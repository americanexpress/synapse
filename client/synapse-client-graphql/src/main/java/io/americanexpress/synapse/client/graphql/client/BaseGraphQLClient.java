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

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import io.americanexpress.synapse.client.graphql.model.BaseGraphQLClientResponse;
import io.americanexpress.synapse.client.graphql.model.BaseGraphQLData;
import io.americanexpress.synapse.client.graphql.model.GraphQLClientRequest;
import io.americanexpress.synapse.client.rest.client.BaseRestClient;
import io.americanexpress.synapse.client.rest.factory.BaseClientHttpHeadersFactory;
import io.americanexpress.synapse.client.rest.model.ClientHeaders;
import io.americanexpress.synapse.client.rest.model.QueryParameter;

/**
 * {@code BaseGraphQLClient} class specifies the prototypes for all blocking GraphQL clients.
 *
 * @param <T> type of data in the response
 * @param <O> output response type
 * @param <H> httpHeadersFactory used to set the HTTP headers for each web service call
 * @author Paolo Claudio
 */
public abstract class BaseGraphQLClient<T extends BaseGraphQLData, O extends BaseGraphQLClientResponse<T>, H extends BaseClientHttpHeadersFactory<GraphQLClientRequest>> extends BaseRestClient<GraphQLClientRequest, O, H> {

	/**
     * Argument constructor creates a new instance of BaseGraphQLClient with given values.
     * @param httpHeadersFactory HTTP headers factory used to produce the custom HTTP headers required to consume the back end service
     */
	protected BaseGraphQLClient(H httpHeadersFactory) {
		super(httpHeadersFactory, HttpMethod.POST);
	}
	
	/**
	 * This operation is unsupported for GraphQL clients.
	 */
	@Override
	public List<O> callPolyService(ClientHeaders clientHeaders, GraphQLClientRequest clientRequest, ParameterizedTypeReference<List<O>> responseEntityType, String... pathVariables) {
		throw new UnsupportedOperationException();
	}

	/**
	 * This operation is unsupported for GraphQL clients.
	 */
	@Override
	public List<O> callPolyService(ClientHeaders clientHeaders, GraphQLClientRequest clientRequest, ParameterizedTypeReference<List<O>> responseEntityType, List<QueryParameter> queryParameters, String... pathVariables) {
		throw new UnsupportedOperationException();
	}
}

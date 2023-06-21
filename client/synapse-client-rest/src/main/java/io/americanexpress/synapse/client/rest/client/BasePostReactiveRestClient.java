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

import io.americanexpress.synapse.client.rest.factory.BaseClientHttpHeadersFactory;
import org.springframework.http.HttpMethod;

import io.americanexpress.synapse.client.rest.handler.BaseReactiveRestResponseErrorHandler;
import io.americanexpress.synapse.client.rest.model.BaseClientRequest;
import io.americanexpress.synapse.client.rest.model.BaseClientResponse;

/**
 * {@code BasePostReactiveRestClient} class specifies the prototypes for all reactive POST REST clients.
 *
 * @param <I> input request type
 * @param <O> output response type
 * @param <H> httpHeadersFactory used to set the HTTP headers for each web service call
 * @author Paolo Claudio
 */
public abstract class BasePostReactiveRestClient<I extends BaseClientRequest, O extends BaseClientResponse, H extends BaseClientHttpHeadersFactory<I>> extends BaseReactiveRestClient<I, O, H> {

	/**
	 * Argument constructor creates a new instance of BasePostReactiveRestClient with given values.
	 * @param httpHeadersFactory HTTP headers factory used to produce the custom HTTP headers required to consume the back end service
	 * @param reactiveRestResponseErrorHandler used to handle errors from the reactive REST client
	 */
	protected BasePostReactiveRestClient(H httpHeadersFactory, BaseReactiveRestResponseErrorHandler reactiveRestResponseErrorHandler) {
		super(httpHeadersFactory, HttpMethod.POST, reactiveRestResponseErrorHandler);
	}
}

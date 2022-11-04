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

import org.springframework.http.HttpMethod;

import io.americanexpress.synapse.client.rest.handler.BaseReactiveRestResponseErrorHandler;
import io.americanexpress.synapse.client.rest.model.BaseClientRequest;
import io.americanexpress.synapse.client.rest.model.BaseClientResponse;

/**
 * {@code BasePutReactiveRestClient} class specifies the prototypes for all reactive POST REST clients.
 *
 * @param <I> input request type
 * @param <O> output response type
 * @author Paolo Claudio
 */
public abstract class BasePutReactiveRestClient<I extends BaseClientRequest, O extends BaseClientResponse> extends BaseReactiveRestClient<I, O> {

	/**
	 * Argument constructor creates a new instance of BasePutReactiveRestClient with given values.
	 * @param reactiveRestResponseErrorHandler used to handle errors from the reactive REST client
	 */
	protected BasePutReactiveRestClient(BaseReactiveRestResponseErrorHandler reactiveRestResponseErrorHandler) {
		super(HttpMethod.PUT, reactiveRestResponseErrorHandler);
	}
}

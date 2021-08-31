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
package io.americanexpress.synapse.client.rest.handler;

import io.americanexpress.synapse.framework.exception.ApplicationClientException;
import io.americanexpress.synapse.framework.exception.model.ErrorCode;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;

import java.util.function.Function;

/**
 * {@code BaseReactiveRestResponseErrorHandler} class specifies the prototypes
 * for handling errors by the reactive REST clients.
 *
 * @author Paolo Claudio
 */
public abstract class BaseReactiveRestResponseErrorHandler extends BaseResponseErrorHandler implements Function<ClientResponse, Mono<? extends Throwable>> {

	/**
     * Applies this function to the given argument.
     *
     * @param clientResponse the function argument
     * @return the function result
     */
    @Override
    public Mono<? extends Throwable> apply(ClientResponse clientResponse) {
    	HttpStatus httpStatus = clientResponse.statusCode();
    	return httpStatus.is4xxClientError() ?
    		clientResponse.bodyToMono(String.class).map(errorMessage -> new ApplicationClientException(buildDeveloperMessage(httpStatus, errorMessage), ErrorCode.GENERIC_4XX_ERROR)) :
    		clientResponse.bodyToMono(String.class).map(errorMessage -> {
    			logger.error(errorMessage);
    			return new ApplicationClientException(buildDeveloperMessage(httpStatus, errorMessage), ErrorCode.GENERIC_5XX_ERROR);
    		});
    }
}

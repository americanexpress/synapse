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
package io.americanexpress.synapse.client.rest.interceptor;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;

import reactor.core.publisher.Mono;

/**
 * {@code ClientLoggingExchangeFilterFunction} class logs the client requests and responses when errors occur reactively.
 * @author Paolo Claudio
 *
 */
@Component
public class ClientLoggingExchangeFilterFunction implements ExchangeFilterFunction {

	/**
     * Used to log the message.
     */
    private final XLogger logger = XLoggerFactory.getXLogger(this.getClass());
    
    /**
	 * Apply this filter to the given request and exchange function.
	 * <p>The given {@linkplain ExchangeFunction} represents the next entity
	 * in the chain, to be invoked via
	 * {@linkplain ExchangeFunction#exchange(ClientRequest) invoked} in order to
	 * proceed with the exchange, or not invoked to shortcut the chain.
	 * @param request the current request
	 * @param next the next exchange function in the chain
	 * @return the filtered response
	 */
    @Override
	public Mono<ClientResponse> filter(ClientRequest clientRequest, ExchangeFunction exchangeFunction) {
    	
    	Mono<ClientResponse> clientResponseMono = exchangeFunction.exchange(clientRequest);
    	ClientResponse clientResponse = clientResponseMono.block();
    	
    	if(clientResponse.statusCode().isError()) {
    		// TODO: write a client log formatter for reactive clients
    		logger.info(clientRequest.toString());
    		logger.info(clientResponse.toString());
    	}
    	
		return clientResponseMono;
	}
}

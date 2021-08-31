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
package io.americanexpress.synapse.client.rest.helper;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;

import reactor.core.publisher.Mono;

/**
 * {@code ClientLoggingExchangeFilterFunction} class logs the client requests and responses when errors occur reactively.
 * @author Paolo Claudio
 *
 */
public class ClientLoggingExchangeFilterFunction {

	/**
     * Used to log the message.
     */
    private static final XLogger logger = XLoggerFactory.getXLogger(ClientLoggingExchangeFilterFunction.class);
    
    /**
     * Default constructor creates a new instance of ClientLoggingExchangeFilterFunction with default values.
     */
    private ClientLoggingExchangeFilterFunction() {

    	// A class containing only static methods is a utility class that requires a private no-argument default constructor
    }
    
    /**
     * Log the client request.
     * @return the exchange filter function containing the client request logging
     */
    public static ExchangeFilterFunction logClientRequest() {
    	return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            logger.info("Client Request: URI={}, HTTP Method={}, HTTP Headers={}",
            	clientRequest.url(), clientRequest.method(), clientRequest.headers());
            return Mono.just(clientRequest);
        });
    }
    
    /**
     * Log the client response.
     * @return the exchange filter function conatining the client response logging
     */
    public static ExchangeFilterFunction logClientResponse() {
    	return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            logger.info("Client Response: HTTP Status={}, HTTP Headers={}",
            	clientResponse.statusCode(), clientResponse.headers());
            return Mono.just(clientResponse);
        });
    }
}

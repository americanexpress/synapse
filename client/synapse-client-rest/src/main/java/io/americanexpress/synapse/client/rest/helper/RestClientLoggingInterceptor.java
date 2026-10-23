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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import io.americanexpress.synapse.client.rest.model.ClientHeaderKey;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * {@code RestClientLoggingInterceptor} class logs the client requests and responses when errors occur.
 * 
 * @author Paolo Claudio
 */
@Component
public class RestClientLoggingInterceptor implements ClientHttpRequestInterceptor {

    /**
     * Used to log the message.
     */
    private final XLogger logger = XLoggerFactory.getXLogger(getClass());

    /**
     * Used to format the log message.
     */
    private final RestClientLogFormatter restClientLogFormatter;
    
    /**
     * Argument constructor creates a new instance of RestClientLoggingInterceptor with given values.
     * @param restClientLogFormatter used to format the log message
     */
    @Autowired
    public RestClientLoggingInterceptor(RestClientLogFormatter restClientLogFormatter) {
    	this.restClientLogFormatter = restClientLogFormatter;
    }

    /**
     * Intercept the request and response for clients when failures occur and log them.
     *
     * @param request   HTTP request
     * @param body      in bytes
     * @param execution client HTTP request execution
     * @return the client HTTP response
     * @throws IOException whenever an issue occurs while trying to perform the input/output operation
     */
    @Override
    @NonNull
    public ClientHttpResponse intercept(@NonNull HttpRequest request, @NonNull byte[] body,
                                        @NonNull ClientHttpRequestExecution execution) throws IOException {
        ClientHttpResponse response = execution.execute(request, body);
        logFailure(request, body, response);
        return response;
    }

    /**
     * Log failed calls. Logging must never break the actual call, so any failure here is swallowed.
     */
    private void logFailure(HttpRequest request, byte[] body, ClientHttpResponse response) {
        try {
            HttpStatusCode status = response.getStatusCode();
            if (!status.is4xxClientError() && !status.is5xxServerError()) {
                return;
            }
            if (restClientLogFormatter.isRedactSensitiveLogs()) {
                logger.info("Client call failed: HTTP Method={}, URI={}, HTTP Status={}, Correlation-ID={}",
                        request.getMethod(), restClientLogFormatter.redactSensitiveDataFromUri(request.getURI()),
                        status, request.getHeaders().getFirst(ClientHeaderKey.CORRELATION_IDENTIFIER_KEY.getValue()));
            } else {
                logger.info(restClientLogFormatter.formatClientRequest(request, body));
                logger.info(restClientLogFormatter.formatClientResponse(response));
            }
        } catch (Exception e) {
            // Only the exception type is logged since its message could contain sensitive data
            logger.warn("Unable to log the failed client call: {}", e.getClass().getName());
        }
    }
}

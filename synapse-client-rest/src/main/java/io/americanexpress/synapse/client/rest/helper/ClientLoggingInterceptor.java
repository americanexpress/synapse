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
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * {@code ClientLoggingInterceptor} class logs the client requests and responses when errors occur.
 *
 * @author Paolo Claudio
 */
@Component
public class ClientLoggingInterceptor implements ClientHttpRequestInterceptor {

    /**
     * Used to log the message.
     */
    private final XLogger logger = XLoggerFactory.getXLogger(getClass());

    /**
     * Used to format the log message.
     */
    private final ClientLogFormatter clientLogFormatter;
    
    /**
     * Argument constructor creates a new instance of ClientLogFormatter with given values.
     * @param clientLogFormatter used to format the log message
     */
    @Autowired
    public ClientLoggingInterceptor(ClientLogFormatter clientLogFormatter) {
    	this.clientLogFormatter = clientLogFormatter;
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
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        ClientHttpResponse response = execution.execute(request, body);

        HttpStatus.Series httpStatusSeries = response.getStatusCode().series();
        if (httpStatusSeries == HttpStatus.Series.CLIENT_ERROR || httpStatusSeries == HttpStatus.Series.SERVER_ERROR) {
            logger.info(clientLogFormatter.formatClientRequest(request, body));
            logger.info(clientLogFormatter.formatClientResponse(response));
        }

        return response;
    }
}

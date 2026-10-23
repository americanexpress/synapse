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

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;

/**
 * {@code BaseResponseErrorHandler} class specifies the prototypes
 * for handling response errors by the clients.
 * 
 * @author Paolo Claudio
 *
 */
abstract class BaseResponseErrorHandler {

	/**
	 * Used to log messages.
	 */
	protected final XLogger logger = XLoggerFactory.getXLogger(getClass());

	/**
	 * When {@code synapse.client.rest.logging.redact-sensitive-logs=false} response bodies are kept out of
	 * developer messages and logs
	 */
	@Value("${synapse.client.rest.logging.redact-sensitive-logs:true}")
	protected boolean redactSensitiveLogs = true;

	/**
	 * External provider error message.
	 */
    protected final String EXTERNAL_PROVIDER_ERROR = "There was an error from the external provider in " + getClass().getName() + ". Error message: ";

    /**
     * Build the developer message.
     * @param statusCode containing the HTTP status code
     * @param responseBody the response body, omitted from the message unless sensitive log redaction is disabled
     * @return the developer message
     */
    protected String buildDeveloperMessage(HttpStatusCode statusCode, String responseBody) {
        if (redactSensitiveLogs) {
            return EXTERNAL_PROVIDER_ERROR + " " + statusCode;
        }
        return EXTERNAL_PROVIDER_ERROR + " " + statusCode + " " + responseBody;
    }
}

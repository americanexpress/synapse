package io.americanexpress.synapse.client.rest.handler;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.http.HttpStatus;

abstract class BaseResponseErrorHandler {

	protected final XLogger logger = XLoggerFactory.getXLogger(getClass());

    protected final String EXTERNAL_PROVIDER_ERROR = "There was an error from the external provider in " + this.getClass().getName() + ". Error message: ";

    protected String buildDeveloperMessage(HttpStatus statusCode, String responseBody) {
        return EXTERNAL_PROVIDER_ERROR + " " + statusCode.toString() + " " + responseBody;
    }
}

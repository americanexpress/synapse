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
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * {@code BaseRestResponseErrorHandler} is the base class that handles all the errors thrown by the Rest Clients.
 * This class throws a custom ApplicationException wrapping the original error messages sent by the web service called and propagating it all the way to the ControllersExceptionHandler.
 * 99% of the time the children classes will NOT have to override these methods, but they could if needed.
 *
 * @author Alexei Morgado
 */
public abstract class BaseRestResponseErrorHandler extends BaseResponseErrorHandler implements ResponseErrorHandler {

    /**
     * Indicates whether response has error.
     *
     * @param httpResponse     the http response
     * @throws IOException the io exception
     * @return true if response contains error.
     */
    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {

        return (((HttpStatus)httpResponse
                .getStatusCode())
                .series() == HttpStatus.Series.CLIENT_ERROR || ((HttpStatus)httpResponse
                .getStatusCode())
                .series() == HttpStatus.Series.SERVER_ERROR);
    }

    /**
     * Handles error in client http response.
     *
     * @param httpResponse     the http response
     * @throws IOException the io exception
     */
    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        String responseBody = getResponseBodyAsString(httpResponse);
        String developerMessage = buildDeveloperMessage(httpResponse.getStatusCode(), responseBody);
        logError(httpResponse, developerMessage);

        //The first parameter is for the developer message to be populated in ControllerExceptionsHandler class.
        throw new ApplicationClientException(developerMessage, ErrorCode.GENERIC_4XX_ERROR);
    }

    /**
     * Log error.
     *
     * @param httpResponse     the http response
     * @param developerMessage the developer message
     * @throws IOException the io exception
     */
    protected void logError(ClientHttpResponse httpResponse, String developerMessage) throws IOException {
        //Only error level when server error family to avoid spam of email alerts.
        if (((HttpStatus) httpResponse
                .getStatusCode())
                .series() == HttpStatus.Series.SERVER_ERROR) {
            logger.error(developerMessage);
        }
    }

    /**
     * Gets response body as string.
     *
     * @param response the response
     * @return the response body as string
     * @throws IOException the io exception
     */
    protected String getResponseBodyAsString(ClientHttpResponse response) throws IOException {
        return StreamUtils.copyToString(response.getBody(), Charset.defaultCharset());
    }
}

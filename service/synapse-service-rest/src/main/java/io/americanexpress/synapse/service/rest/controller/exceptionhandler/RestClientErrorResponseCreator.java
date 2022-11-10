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
package io.americanexpress.synapse.service.rest.controller.exceptionhandler;

import io.americanexpress.synapse.framework.exception.helper.ErrorMessagePropertyReader;
import io.americanexpress.synapse.framework.exception.model.ErrorCode;
import io.americanexpress.synapse.service.rest.model.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;

/**
 * {@code RestClientErrorResponseCreator} handles creation of error response for rest client response exceptions
 */
@Component
public class RestClientErrorResponseCreator {

    private final ErrorMessagePropertyReader messagePropertyReader;

    @Autowired
    public RestClientErrorResponseCreator(ErrorMessagePropertyReader messagePropertyReader) {
        this.messagePropertyReader = messagePropertyReader;
    }

    /**
     * Helper to create the error response entity from the restClientResponseException
     *
     * @param restClientResponseException RestClientResponseException
     * @return ResponseEntity<ErrorResponse>
     */
    public ResponseEntity<ErrorResponse> create(RestClientResponseException restClientResponseException) {
        String userMessage = messagePropertyReader.getErrorMessage(ErrorCode.GENERIC_4XX_ERROR);
        String developerMessage = String.format("There was an error from the external provider. Error message: %s %s", restClientResponseException.getStatusText(), restClientResponseException.getResponseBodyAsString());
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.GENERIC_4XX_ERROR, ControllerExceptionHandler.GENERIC_4XX_HEADER_MESSAGE, userMessage, developerMessage);

        return ResponseEntity.badRequest().body(errorResponse);
    }
}

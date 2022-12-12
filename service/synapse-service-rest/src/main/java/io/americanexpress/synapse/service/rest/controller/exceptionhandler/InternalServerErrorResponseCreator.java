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

import io.americanexpress.synapse.framework.exception.ApplicationServerException;
import io.americanexpress.synapse.framework.exception.helper.ErrorMessagePropertyReader;
import io.americanexpress.synapse.framework.exception.model.ErrorCode;
import io.americanexpress.synapse.service.rest.model.ErrorResponse;
import io.americanexpress.synapse.utilities.common.cryptography.CryptoUtil;
import org.slf4j.ext.XLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

/**
 * {@code InternalServerErrorResponseCreator} handles internal server errors
 */
@Component
public class InternalServerErrorResponseCreator {

    /**
     * Used to create the error message based on the error code by reading the value in error-messages.properties.
     */
    private final ErrorMessagePropertyReader messagePropertyReader;

    /**
     * Used to log the exceptions
     */
    private final MappedDiagnosticContextRequestFieldSetter mappedDiagnosticContextRequestFieldSetter;

    /**
     * Instantiates a new Internal server error response creator.
     *
     * @param messagePropertyReader                     the message property reader
     * @param mappedDiagnosticContextRequestFieldSetter the mapped diagnostic context request field setter
     */
    @Autowired
    public InternalServerErrorResponseCreator(ErrorMessagePropertyReader messagePropertyReader, MappedDiagnosticContextRequestFieldSetter mappedDiagnosticContextRequestFieldSetter) {
        this.messagePropertyReader = messagePropertyReader;
        this.mappedDiagnosticContextRequestFieldSetter = mappedDiagnosticContextRequestFieldSetter;
    }

    /**
     * Handle all the internal server errors.
     *
     * @param throwable          thrown from the application
     * @param httpServletRequest containing the consumer's request
     * @return the response entity containing the error response
     */
    public ResponseEntity<ErrorResponse> create(Throwable throwable, HttpServletRequest httpServletRequest) {
        mappedDiagnosticContextRequestFieldSetter.set(XLogger.Level.ERROR, throwable, httpServletRequest);

        String userMessage = messagePropertyReader.getErrorMessage(ErrorCode.GENERIC_5XX_ERROR, null);
        String developerMessage = CryptoUtil.tryEncrypt(ApplicationServerException.getStackTrace(throwable, System.lineSeparator()));

        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.GENERIC_5XX_ERROR, ControllerExceptionHandler.GENERIC_5XX_HEADER_MESSAGE, userMessage, developerMessage);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

}

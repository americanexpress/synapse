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
package io.americanexpress.synapse.api.rest.reactive.controller.exceptionhandler;

import io.americanexpress.synapse.framework.exception.model.ErrorCode;
import io.americanexpress.synapse.service.reactive.model.ErrorResponse;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.support.WebExchangeBindException;
import java.util.StringJoiner;

/**
 * {@code InputValidationErrorHandler} creates the error response containing the input validation errors.
 *
 * @author Wendy Hu
 */
@Component
public class InputValidationErrorHandler {

    /**
     * Logs exceptions.
     */
    private final XLogger logger = XLoggerFactory.getXLogger(getClass());

    /**
     * Handles input validation errors.
     * @param exception the exception
     * @return the error response
     */
    public ErrorResponse handleInputValidationErrorMessage(WebExchangeBindException exception) {
        logger.entry(exception);

        // Add all error messages separated by commas
        StringJoiner errorMessageJoiner = new StringJoiner(", ");
        String defaultErrorMessage;
        String field;
        for (final FieldError fieldError : exception.getFieldErrors()) {
            defaultErrorMessage = fieldError.getDefaultMessage();
            field = fieldError.getField();
            if (!field.contains("Valid")) {
                errorMessageJoiner.add(field + " " + defaultErrorMessage);
            } else {
                errorMessageJoiner.add(defaultErrorMessage);
            }
        }

        // Create the error response
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.GENERIC_4XX_ERROR,
                ErrorCode.GENERIC_4XX_ERROR.getMessage(),
                errorMessageJoiner.toString(),
                "Input validation");

        logger.exit(errorResponse);
        return errorResponse;
    }
}

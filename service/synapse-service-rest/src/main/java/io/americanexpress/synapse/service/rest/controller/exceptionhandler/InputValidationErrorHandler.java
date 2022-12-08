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

import io.americanexpress.synapse.framework.exception.model.ErrorCode;
import io.americanexpress.synapse.service.rest.model.ErrorResponse;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.StringJoiner;

/**
 * {@code InputValidationErrorHandler} class creates the error response containing the input validation errors.
 *
 * @author Alexei Morgado
 */
@Component
public class InputValidationErrorHandler {

    /**
     * Used to log the input validation error handler.
     */
    private final XLogger logger = XLoggerFactory.getXLogger(getClass());

    /**
     * Create the error response with all of the input violations detected by Hibernate Validator.
     *
     * @param bindingResult containing the error messages
     * @return the error response with HTTP status code 400
     */
    public ErrorResponse handleInputValidationErrorMessage(BindingResult bindingResult) {
        logger.entry(bindingResult);

        // Add all the error messages separated by commas
        StringJoiner errorMessageJoiner = new StringJoiner(", ");
        String defaultErrorMessage;
        String field;
        for (final FieldError fieldError : bindingResult.getFieldErrors()) {
            defaultErrorMessage = fieldError.getDefaultMessage();
            field = fieldError.getField();
            if (!field.contains("Valid")) {
                errorMessageJoiner.add(field + " " + defaultErrorMessage);
            } else {
                errorMessageJoiner.add(defaultErrorMessage);
            }
        }

        // Create the error response
        final ErrorResponse errorResponse = new ErrorResponse(ErrorCode.GENERIC_4XX_ERROR,
                ControllerExceptionHandler.GENERIC_4XX_HEADER_MESSAGE,
                errorMessageJoiner.toString(),
                "Input validation");

        logger.exit(errorResponse);
        return errorResponse;
    }
}

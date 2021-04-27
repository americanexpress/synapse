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
package com.americanexpress.synapse.framework.exception.helper;

import com.americanexpress.synapse.framework.exception.model.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * ErrorMessagePropertyReader class reads the error messages from error-messages.properties.
 * The error messages were loaded into the MessageSource bean from ExceptionConfig.
 *
 * @author Gabriel Jimenez
 */
@Component
public class ErrorMessagePropertyReader {

    /**
     * Contains the error messages loaded from ExceptionConfig that read from error-messages.properties.
     */
    private final MessageSource errorMessageSource;

    /**
     * Argument constructor creates a new instance of ErrorMessagePropertyReader with given values.
     *
     * @param errorMessageSource containing the error messages from error-messages.properties
     */
    public ErrorMessagePropertyReader(@Autowired MessageSource errorMessageSource) {
        this.errorMessageSource = errorMessageSource;
    }

    /**
     * Get the error message from the error-messages.properties.
     *
     * @param errorCode containing the error code
     * @param args      to be set in the error message
     * @return the error message from the error-messages.properties
     */
    public String getErrorMessage(ErrorCode errorCode, String... args) {
        return errorMessageSource.getMessage(errorCode.name(), args, Locale.getDefault());
    }
}

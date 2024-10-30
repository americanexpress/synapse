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
package io.americanexpress.synapse.framework.exception.model;

/**
 * {@code ErrorCode} enum is used to return to the consumer a specific error message.
 * <p>
 * ErrorCode enum class hold keys for the resource file:
 * /src/main/resources/error-messages.properties
 *
 * @author sazam3
 */
public enum GenericErrorCodes implements GenericErrorCode {

    /**
     * Used for all of the generic 4XX series errors, including the 4XX series errors received from external providers.
     */
    VALIDATION_ERROR("VALIDATION_ERROR", "Validation error occurred"),
    AUTHORIZATION_ERROR("AUTHORIZATION_ERROR", "Authorization error"),
    UNKNOWN_ERROR("UNKNOWN_ERROR", "An unknown error has occurred");

    /**
     * Gets the code of the ErrorCode.
     */
    private String code;

    /**
     * Gets the message of the ErrorCode.
     */
    private String message;

    /**
     * Takes in HttpStatus and a message as a string.
     * @param code The http status of the error code.
     * @param message The error message of the error code.
     */
    GenericErrorCodes(String code, String message) {
        this.code = code;
        this.message = message;
    }


    @Override
    public String getCode() {
        return code;
    }

    /**
     * Gets message as a String.
     * @return the message of the error code.
     */
    public String getMessage() {
        return message;
    }
}

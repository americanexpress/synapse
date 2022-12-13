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

import org.springframework.http.HttpStatus;

/**
 * <code>ErrorCode</code> enum is used to return to the consumer a specific error message.
 * <p>
 * ErrorCode enum class hold keys for the resource file:
 * /src/main/resources/error-messages.properties
 *
 * @author Alexei Morgado, Gabriel Jimenez
 */
public enum ErrorCode {

    /**
     * Used for all of the generic 4XX series errors, including the 4XX series errors received from external providers.
     */
    GENERIC_4XX_ERROR(HttpStatus.BAD_REQUEST, "Invalid Request."),

    /**
     *
     */
    NOT_FOUND(HttpStatus.NOT_FOUND, "Not Found."),

    /**
     * Used for all of the generic 5XX series errors.
     */
    GENERIC_5XX_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Error."),

    /**
     * Used to validate the HTTP headers received at the HTTP layer.
     */
    MISSING_HTTP_HEADER_ERROR(HttpStatus.BAD_REQUEST, "Bad Request."),

    /**
     * Used for when the consumer is forbidden to access a resource.
     */
    AUTHENTICATION_ERROR(HttpStatus.FORBIDDEN, "Fobidden");

    /**
     * Gets the HttpStatus of the ErrorCode.
     */
    private HttpStatus httpStatus;

    /**
     * Gets the message of the ErrorCode.
     */
    private String message;

    /**
     * Takes in HttpStatus and a message as a string.
     * @param httpStatus The http status of the error code.
     * @param message The error message of the error code.
     */
    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    /**
     * Gets HttpStatus.
     * @return
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    /**
     * Gets message as a String.
     * @return
     */
    public String getMessage() {
        return message;
    }
}

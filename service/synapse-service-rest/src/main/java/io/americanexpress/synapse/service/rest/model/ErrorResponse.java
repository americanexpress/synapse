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
package io.americanexpress.synapse.service.rest.model;

import io.americanexpress.synapse.framework.exception.model.ErrorCode;

/**
 * {@code ErrorResponse} class is the response model for 4XX and 5XX series errors.
 *
 */
public class ErrorResponse {

    /**
     * Error code.
     */
    private ErrorCode code;

    /**
     * Friendly header message that gives a brief overview of the error.
     */
    private String message;

    /**
     * Friendly user message to the consumer of the error.
     */
    private String moreInfo;

    /**
     * Message for the developer of the error and possibly a solution to fix the error.
     */
    private String developerMessage;

    /**
     * Argument constructor creates a new instance of ErrorResponse with given values.
     *
     * @param code             error code
     * @param message          friendly header message that gives a brief overview of the error
     * @param moreInfo         friendly user message to the consumer of the error
     * @param developerMessage message for the developer of the error and possibly a solution to fix the error
     */
    public ErrorResponse(ErrorCode code, String message, String moreInfo, String developerMessage) {
        this.code = code;
        this.message = message;
        this.moreInfo = moreInfo;
        this.developerMessage = developerMessage;
    }

    /**
     * Get the errorCode.
     *
     * @return the errorCode
     */
    public ErrorCode getCode() {
        return code;
    }

    /**
     * Get the userMessage.
     *
     * @return the userMessage
     */
    public String getMoreInfo() {
        return moreInfo;
    }

    /**
     * Get the headerMessage.
     *
     * @return the headerMessage
     */
    public String getMessage() {
        return message;
    }

    /**
     * Get the developerMessage.
     *
     * @return the developerMessage
     */
    public String getDeveloperMessage() {
        return developerMessage;
    }

    /**
     * Set the developerMessage.
     *
     * @param developerMessage the developerMessage
     */
    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }
}

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
package io.americanexpress.synapse.framework.exception;

import io.americanexpress.synapse.framework.exception.model.ErrorCode;

/**
 * The type Application client exception.
 */
@Deprecated
public class ApplicationClientException extends RuntimeException {

    /**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Used to provide a human readable message to the developer to understand a recoverable solution to the error.
     */
    private final String developerMessage;

    /**
     * Error code enum containing the specific error codes, corresponding to error-error-messages.properties.
     */
    private final ErrorCode errorCode;

    /**
     * Optional arguments used to be passed to the error-error-messages.properties.
     */
    private final String[] messageArguments;

    /**
     * Gets developer message.
     *
     * @return the developer message
     */
    public String getDeveloperMessage() {
        return developerMessage;
    }

    /**
     * Gets message key.
     *
     * @return the message key
     */
    public ErrorCode getErrorCode() {
        return errorCode;
    }

    /**
     * Get message arguments string [ ].
     *
     * @return the string [ ]
     */
    public String[] getMessageArguments() {
        return messageArguments;
    }

    /**
     * Instantiates a new Application client exception.
     *
     * @param developerMessage the developer message
     * @param errorCode       the erorr code message key
     * @param messageArguments the message arguments
     */
    public ApplicationClientException(String developerMessage, ErrorCode errorCode, String... messageArguments) {
        super();
        this.developerMessage = developerMessage;
        this.errorCode = errorCode;
        this.messageArguments = messageArguments;
    }
}


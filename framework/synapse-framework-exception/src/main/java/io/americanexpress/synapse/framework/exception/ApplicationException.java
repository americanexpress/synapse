package io.americanexpress.synapse.framework.exception;

import io.americanexpress.synapse.framework.exception.model.ExceptionCode;

/**
 * {@code ApplicationException} used for exceptions throws at layers that are not related to any request protocol.
 *
 * @author John Robert Martinez Ponce
 */
public class ApplicationException extends RuntimeException {

    private final String developerMessage;

    private final ExceptionCode exceptionCode;

    private String[] messageArguments;

    public ApplicationException(ExceptionCode exceptionCode, String developerMessage, String[] messageArguments) {
        this.developerMessage = developerMessage;
        this.exceptionCode = exceptionCode;
        this.messageArguments = messageArguments;
    }

    public ApplicationException(ExceptionCode exceptionCode, String developerMessage) {
        this.developerMessage = developerMessage;
        this.exceptionCode = exceptionCode;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }

    public String[] getMessageArguments() {
        return messageArguments;
    }

    public void setMessageArguments(String[] messageArguments) {
        this.messageArguments = messageArguments;
    }
}
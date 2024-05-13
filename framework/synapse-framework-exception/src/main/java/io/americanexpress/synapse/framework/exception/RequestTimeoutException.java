package io.americanexpress.synapse.framework.exception;

public class RequestTimeoutException extends BaseException {

    /**
     * Argument constructor creates a new instance of RequestTimeoutException with given values.
     * Use this constructor when checked exceptions are caught in order to pass the original exception.
     *
     * @param cause of the original caught exception
     */
    public RequestTimeoutException(String developerMessage, Throwable cause) {
        super(developerMessage, cause);
    }
}

package io.americanexpress.synapse.framework.exception;

/**
 * The type Request Timeout exception.
 */
public class ApplicationRequestTimeoutException extends BaseApplicationException {

    /**
     * Argument constructor creates a new instance of ApplicationRequestTimeoutException with given values.
     * Use this constructor when checked exceptions are caught in order to pass the original exception.
     *
     * @param developerMessage message from the developer.
     */
    public ApplicationRequestTimeoutException(String developerMessage) {
        super(developerMessage);
    }

    /**
     * Argument constructor creates a new instance of ApplicationRequestTimeoutException with given values.
     * Use this constructor when checked exceptions are caught in order to pass the original exception.
     *
     * @param developerMessage message from the developer.
     * @param cause of the original caught exception
     */
    public ApplicationRequestTimeoutException(String developerMessage, Throwable cause) {
        super(developerMessage, cause);
    }
}

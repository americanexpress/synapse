package io.americanexpress.synapse.framework.exception;

/**
 * The type Not Found exception.
 */
public class ApplicationNotFoundException extends BaseApplicationException {

    /**
     * Argument constructor creates a new instance of ApplicationNotFoundException with given values.
     * Use this constructor when checked exceptions are caught in order to pass the original exception.
     *
     * @param developerMessage message from the developer.
     */
    public ApplicationNotFoundException(String developerMessage) {
        super(developerMessage);
    }

    /**
     * Argument constructor creates a new instance of ApplicationNotFoundException with given values.
     * Use this constructor when checked exceptions are caught in order to pass the original exception.
     *
     * @param developerMessage message from the developer.
     * @param cause of the original caught exception
     */
    public ApplicationNotFoundException(String developerMessage, Throwable cause) {
        super(developerMessage, cause);
    }
}

package io.americanexpress.synapse.framework.exception;

/**
 * The type Validation exception.
 */
public class ApplicationValidationException extends BaseApplicationException {

    /**
     * Argument constructor creates a new instance of ApplicationValidationException with given values.
     * Use this constructor when checked exceptions are caught in order to pass the original exception.
     *
     * @param developerMessage message from the developer.
     */
    public ApplicationValidationException(String developerMessage) {
        super(developerMessage);
    }

    /**
     * Argument constructor creates a new instance of ApplicationValidationException with given values.
     * Use this constructor when checked exceptions are caught in order to pass the original exception.
     *
     * @param developerMessage message from the developer.
     * @param cause of the original caught exception
     */
    public ApplicationValidationException(String developerMessage, final Throwable cause) {
        super(developerMessage, cause);
    }
}

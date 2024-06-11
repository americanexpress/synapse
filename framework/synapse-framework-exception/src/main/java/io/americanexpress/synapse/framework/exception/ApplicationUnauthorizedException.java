package io.americanexpress.synapse.framework.exception;

/**
 * The type Unauthorized exception.
 */
public class ApplicationUnauthorizedException extends BaseApplicationException {

    /**
     * Argument constructor creates a new instance of ApplicationUnauthorizedException with given values.
     * Use this constructor when checked exceptions are caught in order to pass the original exception.
     *
     * @param developerMessage message from the developer.
     */
    public ApplicationUnauthorizedException(String developerMessage) {
        super(developerMessage);
    }

    /**
     * Argument constructor creates a new instance of ApplicationUnauthorizedException with given values.
     * Use this constructor when checked exceptions are caught in order to pass the original exception.
     *
     * @param developerMessage message from the developer.
     * @param cause of the original caught exception
     */
    public ApplicationUnauthorizedException(String developerMessage, final Throwable cause) {
        super(developerMessage, cause);
    }
}

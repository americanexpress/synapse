package io.americanexpress.synapse.framework.exception;

/**
 * The type generic exception.
 */
public class ApplicationException extends BaseApplicationException {

    /**
     * Argument constructor creates a new instance of ApplicationException with given values.
     * Use this constructor when checked exceptions are caught in order to pass the original exception.
     *
     * @param developerMessage message from the developer.
     */
    public ApplicationException(String developerMessage) {
        super(developerMessage);
    }

    /**
     * Argument constructor creates a new instance of ServiceException with given values.
     * Use this constructor when checked exceptions are caught in order to pass the original exception.
     *
     * @param cause of the original caught exception
     */
    public ApplicationException(String developerMessage, final Throwable cause) {
        super(developerMessage, cause);
    }
}

package io.americanexpress.synapse.framework.exception;

public class ServiceException extends BaseException {
    /**
     * Argument constructor creates a new instance of ServiceException with given values.
     * Use this constructor when checked exceptions are caught in order to pass the original exception.
     *
     * @param cause of the original caught exception
     */
    public ServiceException(String developerMessage, final Throwable cause) {
        super(developerMessage, cause);
    }
}

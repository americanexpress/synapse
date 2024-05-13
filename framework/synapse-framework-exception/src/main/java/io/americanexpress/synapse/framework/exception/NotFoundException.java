package io.americanexpress.synapse.framework.exception;

public class NotFoundException extends BaseException {

    /**
     * Argument constructor creates a new instance of NotFoundException with given values.
     * Use this constructor when checked exceptions are caught in order to pass the original exception.
     *
     * @param cause of the original caught exception
     */
    public NotFoundException(String developerMessage, Throwable cause) {
        super(developerMessage, cause);
    }
}

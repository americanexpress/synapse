package io.americanexpress.synapse.framework.exception;

public abstract class BaseException extends RuntimeException {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Used to provide a human readable message to the developer to understand a recoverable solution to the error.
     */
    private final String developerMessage;

    /**
     * Gets developer message.
     *
     * @return the developer message
     */
    public String getDeveloperMessage() {
        return developerMessage;
    }

    /**
     * Argument constructor creates a new instance of ApplicationException with given values.
     * Use this constructor when checked exceptions are caught in order to pass the original exception.
     *
     * @param cause of the original caught exception
     */
    public BaseException(String developerMessage, Throwable cause) {
        super(cause);
        this.developerMessage = developerMessage;
    }
}

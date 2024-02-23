package io.americanexpress.synapse.framework.exception.model;

/**
 * {@code ExceptionCode} contains a list of generic exception codes, that area going to be used to provide more
 * information about the Exception.
 *
 * @author John Robert Martinez Ponce
 */
public enum ExceptionCode {

    /**
     * Used for when a validation did not proceed as expected.
     */
    VALIDATION_EXCEPTION("Invalid data"),

    /**
     * Used for when a data point was not found.
     */
    NOT_FOUND("Data not found"),

    /**
     * Used for processes that are interrupted in the middle of a transaction.
     */
    UNABLE_PROCESS("Unable to process");

    private final String message;

    ExceptionCode(String message) {
        this.message = message;
    }

    /**
     * Retrieves the static exception message from the ENUM.
     *
     * @return a String with the message
     */
    public String getMessage() {
        return message;
    }
}

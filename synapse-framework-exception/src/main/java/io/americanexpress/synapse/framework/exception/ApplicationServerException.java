package io.americanexpress.synapse.framework.exception;

/**
 * The type Application server exception.
 */
public class ApplicationServerException extends RuntimeException {

    /**
     * Argument constructor creates a new instance of ApplicationException with given values.
     * Use this constructor when checked exceptions are caught in order to pass the original exception.
     *
     * @param cause of the original caught exception
     */
    public ApplicationServerException(final Throwable cause) {
        super(cause);
    }

    /**
     * Get the complete stack trace as a string.
     *
     * @param throwable     containing the stack trace
     * @param lineSeparator the line separator
     * @return the complete stack trace as a string
     */
    public static String getStackTrace(final Throwable throwable, final String lineSeparator) {
        final StringBuilder stackTraceBuilder = new StringBuilder(throwable.toString()).append(lineSeparator);
        for (final StackTraceElement stackTraceElement : throwable.getStackTrace()) {
            stackTraceBuilder.append(stackTraceElement.toString()).append(System.lineSeparator());
        }
        return stackTraceBuilder.toString();
    }
}

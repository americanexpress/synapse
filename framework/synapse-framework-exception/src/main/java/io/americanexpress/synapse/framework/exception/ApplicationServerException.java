/*
 * Copyright 2020 American Express Travel Related Services Company, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.americanexpress.synapse.framework.exception;

/**
 * The type Application server exception.
 */
@Deprecated
public class ApplicationServerException extends RuntimeException {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
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

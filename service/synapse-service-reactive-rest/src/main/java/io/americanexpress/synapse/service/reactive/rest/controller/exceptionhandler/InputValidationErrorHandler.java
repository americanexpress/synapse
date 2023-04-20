package io.americanexpress.synapse.service.reactive.rest.controller.exceptionhandler;

import io.americanexpress.synapse.framework.exception.model.ErrorCode;
import io.americanexpress.synapse.service.reactive.rest.model.ErrorResponse;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.StringJoiner;

/**
 * {@code InputValidationErrorHandler} creates the error response containing the input validation errors.
 *
 * @author Wendy Hu
 */
@Component
public class InputValidationErrorHandler {

    private final XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ErrorResponse handleInputValidationErrorMessage(WebExchangeBindException exception) {
        logger.entry(exception);

        // Add all error messages separated by commas
        StringJoiner errorMessageJoiner = new StringJoiner(", ");
        String defaultErrorMessage;
        String field;
        for (final FieldError fieldError : exception.getFieldErrors()) {
            defaultErrorMessage = fieldError.getDefaultMessage();
            field = fieldError.getField();
            if (!field.contains("Valid")) {
                errorMessageJoiner.add(field + " " + defaultErrorMessage);
            } else {
                errorMessageJoiner.add(defaultErrorMessage);
            }
        }

        // Create the error response
        return new ErrorResponse(ErrorCode.GENERIC_4XX_ERROR,
                ErrorCode.GENERIC_4XX_ERROR.getMessage(),
                errorMessageJoiner.toString(),
                "Input validation");
    }
}
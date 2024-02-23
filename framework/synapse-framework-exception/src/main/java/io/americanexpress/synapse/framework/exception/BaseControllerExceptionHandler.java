package io.americanexpress.synapse.framework.exception;

import io.americanexpress.synapse.framework.exception.model.ErrorCode;
import io.americanexpress.synapse.framework.exception.model.ExceptionCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

public abstract class BaseControllerExceptionHandler<T, R, E> {

    /**
     * Used to log the exceptions.
     */
    private final XLogger logger = XLoggerFactory.getXLogger(getClass());

    @ExceptionHandler(ApplicationException.class)
    public T handleApplicationException(final ApplicationException applicationException) {

        E errorCode = mapExceptionCode(applicationException.getExceptionCode());
        ErrorResponse errorResponse = new ErrorResponse(
                errorCode,
                errorCode.getMessage(),
                applicationException.getMessageArguments() == null ? null : Arrays.toString(applicationException.getMessageArguments()),
                applicationException.getDeveloperMessage() == null ? null : applicationException.getDeveloperMessage()
        );

        return createExceptionResponse(errorResponse);
    }

    abstract T createExceptionResponse(R response);

    protected abstract ErrorCode mapExceptionCode(ExceptionCode exceptionCode);

}

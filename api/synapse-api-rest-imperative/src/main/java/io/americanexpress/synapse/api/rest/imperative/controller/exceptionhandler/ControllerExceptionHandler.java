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
package io.americanexpress.synapse.api.rest.imperative.controller.exceptionhandler;

import io.americanexpress.synapse.framework.exception.ApplicationClientException;
import io.americanexpress.synapse.framework.exception.ApplicationServerException;
import io.americanexpress.synapse.framework.exception.helper.ErrorMessagePropertyReader;
import io.americanexpress.synapse.framework.exception.model.ErrorCode;
import io.americanexpress.synapse.service.imperative.model.ErrorResponse;
import io.americanexpress.synapse.framework.exception.BaseException;
import io.americanexpress.synapse.framework.exception.RequestTimeoutException;
import io.americanexpress.synapse.framework.exception.ServiceException;
import io.americanexpress.synapse.framework.exception.NotFoundException;
import io.americanexpress.synapse.utilities.common.cryptography.CryptoUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

/**
 * {@code ControllerExceptionHandler} class handles all the exceptions and errors thrown by the application, excluding Spring Security.
 *
 * @author Alexei Morgado
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

    /**
     * Used to log the exceptions.
     */
    private final XLogger logger = XLoggerFactory.getXLogger(getClass());

    /**
     * Used to create the error message based on the error code by reading the value in error-messages.properties.
     */
    private final ErrorMessagePropertyReader errorMessagePropertyReader;

    /**
     * Used to handle input validation errors.
     */
    private final InputValidationErrorHandler inputValidationErrorHandler;

    /**
     * Argument constructor creates a new instance of ControllersExceptionsHandler with given values.
     * @param errorMessagePropertyReader  used to create the error message based on the error code by reading the value in error-messages.properties
     * @param inputValidationErrorHandler used to handle input validation errors
     */
    public ControllerExceptionHandler(@Autowired ErrorMessagePropertyReader errorMessagePropertyReader, @Autowired InputValidationErrorHandler inputValidationErrorHandler) {
        this.errorMessagePropertyReader = errorMessagePropertyReader;
        this.inputValidationErrorHandler = inputValidationErrorHandler;
    }

    /**
     * Handle application server exception response entity.
     * @param applicationServerException the application server exception
     * @param httpServletRequest         the http servlet request
     * @return errorResponseEntity of type ResponseEntity<ErrorResponse>
     */
    @Deprecated
    @ExceptionHandler(ApplicationServerException.class)
    public ResponseEntity<ErrorResponse> handleApplicationServerException(final ApplicationServerException applicationServerException, final HttpServletRequest httpServletRequest) {
        logger.entry(applicationServerException);
        final ResponseEntity<ErrorResponse> errorResponseEntity = handleInternalServerError(applicationServerException, httpServletRequest);
        this.logger.exit(errorResponseEntity);
        return errorResponseEntity;
    }

    /**
     * Handle application client exception response entity.
     * @param applicationClientException the application client exception
     * @return errorResponseEntity of type ResponseEntity<ErrorResponse>
     */
    @Deprecated
    @ExceptionHandler(ApplicationClientException.class)
    public ResponseEntity<ErrorResponse> handleApplicationClientException(final ApplicationClientException applicationClientException) {
        logger.entry(applicationClientException);

        ResponseEntity<ErrorResponse> errorResponseEntity;

        if (applicationClientException.getCause() == null) {
            ErrorCode errorCode = applicationClientException.getErrorCode();
            String message = errorMessagePropertyReader.getErrorMessage(errorCode, applicationClientException.getMessageArguments() != null
                    ? applicationClientException.getMessageArguments() : new String[]{StringUtils.EMPTY});
            String developerMessage = StringUtils.isNotBlank(applicationClientException.getDeveloperMessage()) ? applicationClientException.getDeveloperMessage() : StringUtils.EMPTY;
            ErrorResponse errorResponse = new ErrorResponse(errorCode, errorCode.getMessage(), message, developerMessage);
            errorResponseEntity = ResponseEntity.status(errorCode.getHttpStatus().value()).body(errorResponse);
        } else {
            errorResponseEntity = handleInternalServerError(applicationClientException);
        }

        logger.exit(errorResponseEntity);
        return errorResponseEntity;
    }

    /**
     * Handle MethodArgumentNotValidExceptions that were thrown by the application due to violations during input validation.
     * @param methodArgumentNotValidException thrown by the application
     * @return the error response with HTTP status code 400
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        logger.warn("Method argument is not valid", methodArgumentNotValidException);
        ErrorResponse errorResponse = inputValidationErrorHandler.handleInputValidationErrorMessage(methodArgumentNotValidException.getBindingResult());
        final ResponseEntity<ErrorResponse> errorResponseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        logger.exit(errorResponseEntity);
        return errorResponseEntity;
    }
    
    /**
     * Handle BindingExceptions that were thrown by the application due to constraint violations on request models.
     * @param bindException thrown by the application
     * @return the error response with HTTP status code 400
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindException(BindException bindException) {
        logger.warn("Request fields are not valid", bindException);
        ErrorResponse errorResponse = inputValidationErrorHandler.handleInputValidationErrorMessage(bindException.getBindingResult());
        ResponseEntity<ErrorResponse> errorResponseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        logger.exit(errorResponseEntity);
        return errorResponseEntity;
    }

    /**
     * Handle HttpMessageNotReadableExceptions due to malformed requests.
     *
     * @param httpMessageNotReadableException thrown by the application
     * @return the error response with HTTP status code 400
     */
    @Deprecated
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException httpMessageNotReadableException) {
        logger.warn("HTTP message is not readable", httpMessageNotReadableException);
        String userMessage = httpMessageNotReadableException.getMessage();
        ErrorCode errorCode = ErrorCode.GENERIC_4XX_ERROR;
        final ErrorResponse errorResponse = new ErrorResponse(errorCode, errorCode.getMessage(), userMessage, "Input validation");
        final ResponseEntity<ErrorResponse> errorResponseEntity = ResponseEntity.status(errorCode.getHttpStatus().value()).body(errorResponse);
        logger.exit(errorResponseEntity);
        return errorResponseEntity;
    }

    /**
     * Handle any Exception or Error not already caught by other exception handler methods in this class.
     * @param throwable containing the Exception or Error thrown by the application
     * @return the error response with HTTP status code 500
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> handleThrowable(Throwable throwable) {
        logger.catching(throwable);
        final ResponseEntity<ErrorResponse> errorResponseEntity = handleInternalServerError(throwable);
        logger.exit(errorResponseEntity);
        return errorResponseEntity;
    }

    /**
     * Handle internal server errors.
     * @param throwable containing the Exception or Error thrown by the application
     * @return the error response with HTTP status code 500
     */
    private ResponseEntity<ErrorResponse> handleInternalServerError(Throwable throwable) {
        String message = errorMessagePropertyReader.getErrorMessage(ErrorCode.GENERIC_5XX_ERROR);
        String fullStackTrace = ApplicationServerException.getStackTrace(throwable, System.lineSeparator());
        ErrorCode errorCode = ErrorCode.GENERIC_5XX_ERROR;
        ErrorResponse errorResponse = new ErrorResponse(errorCode, errorCode.getMessage(), message, CryptoUtil.encrypt(fullStackTrace));
        return ResponseEntity.status(errorCode.getHttpStatus().value()).body(errorResponse);
    }

    /**
     * This method will handle all the internal server errors. Meaning all the 500s family
     * errors which is when we have an exception in our code and we catch and rethrow it or a
     * runtime exception is thrown somewhere.
     *
     * @param throwable the error that was thrown
     * @return response of type ResponseEntity<ErrorResponse>
     */
    @Deprecated
    private ResponseEntity<ErrorResponse> handleInternalServerError(final Throwable throwable, final HttpServletRequest httpServletRequest) {
        logger.catching(throwable);
        String message = errorMessagePropertyReader.getErrorMessage(ErrorCode.GENERIC_5XX_ERROR);
        String fullStackTrace = ApplicationServerException.getStackTrace(throwable, System.lineSeparator());
        ErrorCode  errorCode = ErrorCode.GENERIC_5XX_ERROR;
        ErrorResponse errorResponse = new ErrorResponse(errorCode, errorCode.getMessage(), message, CryptoUtil.encrypt(fullStackTrace));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    /**
     * This method will handle all the internal server errors. Meaning all the 500s family
     * errors which is when we have an exception in our code and we catch and rethrow it or a
     * runtime exception is thrown somewhere.
     *
     * @param serviceException the error that was thrown
     * @return response of type ResponseEntity<ErrorResponse>
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResponse> handleServiceException(final ServiceException serviceException) {
        logger.entry(serviceException);
        var msg = Optional.ofNullable(serviceException.getDeveloperMessage())
                .orElseGet(() -> CryptoUtil.encrypt(ApplicationServerException.getStackTrace(serviceException, System.lineSeparator())));
        final ResponseEntity<ErrorResponse> errorResponseEntity =
                handleServerError(serviceException, ErrorCode.GENERIC_5XX_ERROR, HttpStatus.INTERNAL_SERVER_ERROR,
                        Optional.ofNullable(serviceException.getDeveloperMessage())
                        .orElseGet(() -> CryptoUtil.encrypt(ApplicationServerException.getStackTrace(serviceException, System.lineSeparator()))));
        this.logger.exit(errorResponseEntity);
        return errorResponseEntity;
    }

    /**
     * This method will handle all the internal server errors. Meaning all the 500s family
     * errors which is when we have an exception in our code and we catch and rethrow it or a
     * runtime exception is thrown somewhere.
     *
     * @param requestTimeoutException the error that was thrown
     * @return response of type ResponseEntity<ErrorResponse>
     */
    @ExceptionHandler(RequestTimeoutException.class)
    public ResponseEntity<ErrorResponse> handleRequestTimeOutException(final RequestTimeoutException requestTimeoutException) {
        logger.entry(requestTimeoutException);
        final ResponseEntity<ErrorResponse> errorResponseEntity =
                handleServerError(requestTimeoutException, ErrorCode.GENERIC_5XX_ERROR, HttpStatus.INTERNAL_SERVER_ERROR,
                        requestTimeoutException.getDeveloperMessage());
        this.logger.exit(errorResponseEntity);
        return errorResponseEntity;
    }

    /**
     * This method will handle all the not found errors.
     * Meaning when read or delete operations are performed on a resource that does not exist.
     * @param notFoundException the error that was thrown
     * @return response of type ResponseEntity<ErrorResponse>
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleServiceNotFoundException(final NotFoundException notFoundException) {
        logger.entry(notFoundException);
        final ResponseEntity<ErrorResponse> errorResponseEntity =
                handleServerError(notFoundException, ErrorCode.GENERIC_4XX_ERROR, HttpStatus.NOT_FOUND,
                        notFoundException.getDeveloperMessage());
        this.logger.exit(errorResponseEntity);
        return errorResponseEntity;
    }

    /**
     * This method will handle all the internal server errors. Meaning all the 500s family
     * errors which is when we have an exception in our code and we catch and rethrow it or a
     * runtime exception is thrown somewhere.
     *
     * @param baseException the error that was thrown
     * @param errorCode the error code
     * @param httpStatus the http status
     * @param developerMessage the developer message
     *
     * @return response of type ResponseEntity<ErrorResponse>
     */
    private ResponseEntity<ErrorResponse> handleServerError( final BaseException baseException, final ErrorCode errorCode,
            final HttpStatus httpStatus, String developerMessage) {
        logger.catching(baseException);
        String message = errorMessagePropertyReader.getErrorMessage(ErrorCode.GENERIC_5XX_ERROR);
        ErrorResponse errorResponse = new ErrorResponse(errorCode, errorCode.getMessage(), message, developerMessage);
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}

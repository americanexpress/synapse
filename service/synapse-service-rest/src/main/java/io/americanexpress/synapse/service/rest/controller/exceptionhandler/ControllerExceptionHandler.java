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
package io.americanexpress.synapse.service.rest.controller.exceptionhandler;

import io.americanexpress.synapse.framework.exception.ApplicationClientException;
import io.americanexpress.synapse.framework.exception.ApplicationServerException;
import io.americanexpress.synapse.framework.exception.helper.ErrorMessagePropertyReader;
import io.americanexpress.synapse.framework.exception.model.ErrorCode;
import io.americanexpress.synapse.service.rest.model.ErrorResponse;
import io.americanexpress.synapse.utilities.common.cryptography.CryptoUtil;
import org.apache.catalina.connector.ClientAbortException;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

import javax.servlet.http.HttpServletRequest;

/**
 * {@code ControllerExceptionHandler} class handles all the exceptions and errors thrown by the application, excluding Spring Security.
 *
 * @author Alexei Morgado
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

    /**
     * The friendly header message for 4XX series errors.
     */
    static final String GENERIC_4XX_HEADER_MESSAGE = "Invalid Request.";

    /**
     * The friendly header message for 5XX series errors.
     */
    static final String GENERIC_5XX_HEADER_MESSAGE = "Internal Error";

    /**
     * Used to log the exceptions
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
     * Used to log the exceptions.
     */
    private final MappedDiagnosticContextRequestFieldSetter mappedDiagnosticContextRequestFieldSetter;

    /**
     * Used to create the internal server error response.
     */
    private final InternalServerErrorResponseCreator internalServerErrorResponseCreator;

    /**
     * Used to create the rest client error response.
     */
    private final RestClientErrorResponseCreator restClientErrorResponseCreator;

    /**
     * Argument constructor creates a new instance of ControllersExceptionsHandler with given values.
     * @param errorMessagePropertyReader  used to create the error message based on the error code by reading the value in error-messages.properties
     * @param inputValidationErrorHandler used to handle input validation errors
     * @param mappedDiagnosticContextRequestFieldSetter used to log exceptions
     * @param internalServerErrorResponseCreator used to create error responses for internal server error
     * @param restClientErrorResponseCreator used to create error responses for rest client error
     */
    public ControllerExceptionHandler(@Autowired ErrorMessagePropertyReader errorMessagePropertyReader, @Autowired InputValidationErrorHandler inputValidationErrorHandler,
                                      MappedDiagnosticContextRequestFieldSetter mappedDiagnosticContextRequestFieldSetter, InternalServerErrorResponseCreator internalServerErrorResponseCreator, RestClientErrorResponseCreator restClientErrorResponseCreator) {
        this.errorMessagePropertyReader = errorMessagePropertyReader;
        this.inputValidationErrorHandler = inputValidationErrorHandler;
        this.mappedDiagnosticContextRequestFieldSetter = mappedDiagnosticContextRequestFieldSetter;
        this.internalServerErrorResponseCreator = internalServerErrorResponseCreator;
        this.restClientErrorResponseCreator = restClientErrorResponseCreator;
    }

    /**
     * Handle application server exception response entity.
     *
     * @param applicationServerException the application server exception
     * @param httpServletRequest         the http servlet request
     * @return errorResponseEntity of type ResponseEntity<ErrorResponse>
     */
    @ExceptionHandler(ApplicationServerException.class)
    public ResponseEntity<ErrorResponse> handleApplicationServerException(final ApplicationServerException applicationServerException, final HttpServletRequest httpServletRequest) {
        logger.entry(applicationServerException);
        ResponseEntity<ErrorResponse> errorResponseEntity = internalServerErrorResponseCreator.create(applicationServerException, httpServletRequest);
        this.logger.exit(errorResponseEntity);
        return errorResponseEntity;
    }

    /**
     * Handle application client exception response entity.
     *
     * @param applicationClientException the application client exception
     * @return errorResponseEntity of type ResponseEntity<ErrorResponse>
     */
    @ExceptionHandler(ApplicationClientException.class)
    public ResponseEntity<ErrorResponse> handleApplicationClientException(final ApplicationClientException applicationClientException, HttpServletRequest httpServletRequest) {
        logger.entry(applicationClientException);

        ErrorCode errorCode = applicationClientException.getErrorCode();
        String message = errorMessagePropertyReader.getErrorMessage(errorCode, applicationClientException.getMessageArguments());
        String developerMessage = applicationClientException.getDeveloperMessage();
        ErrorResponse errorResponse = new ErrorResponse(errorCode, ControllerExceptionHandler.GENERIC_4XX_HEADER_MESSAGE, message, developerMessage);
        ResponseEntity<ErrorResponse> errorResponseEntity = ResponseEntity.badRequest().body(errorResponse);

        logger.exit(errorResponseEntity);
        return errorResponseEntity;
    }

    /**
     * Handle MethodArgumentNotValidExceptions that were thrown by the application due to violations during input validation.
     *
     * @param methodArgumentNotValidException thrown by the application
     * @return the error response with HTTP status code 400
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        logger.warn("Method argument is not valid", methodArgumentNotValidException);
        ErrorResponse errorResponse = inputValidationErrorHandler.handleInputValidationErrorMessage(methodArgumentNotValidException.getBindingResult());
        ResponseEntity<ErrorResponse> errorResponseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        logger.exit(errorResponseEntity);
        return errorResponseEntity;
    }
    
    /**
     * Handle BindingExceptions that were thrown by the application due to constraint violations on request models.
     *
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
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException httpMessageNotReadableException) {
        logger.warn("HTTP message is not readable", httpMessageNotReadableException);
        String userMessage = httpMessageNotReadableException.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.GENERIC_4XX_ERROR, GENERIC_4XX_HEADER_MESSAGE, userMessage, "Input validation");
        ResponseEntity<ErrorResponse> errorResponseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        logger.exit(errorResponseEntity);
        return errorResponseEntity;
    }

    /**
     * Handle all the exceptions and errors that have not been handled by another more specific exception handler method.
     *
     * @param throwable          thrown from the application
     * @param httpServletRequest containing the consumer's request
     * @return the response entity containing the error response
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> handleThrowable(Throwable throwable, HttpServletRequest httpServletRequest) {
        ResponseEntity<ErrorResponse> errorResponseEntity = internalServerErrorResponseCreator.create(throwable, httpServletRequest);
        logger.exit(errorResponseEntity);
        return errorResponseEntity;
    }

    /**
     * Handle the HTTP REST client and server exceptions.
     *
     * @param restClientResponseException an HTTP REST client response exception
     * @param httpServletRequest          containing the consumer's request
     * @return the response entity containing the error response
     */
    @ExceptionHandler({RestClientResponseException.class, HttpStatusCodeException.class, HttpClientErrorException.class, HttpServerErrorException.class})
    public ResponseEntity<ErrorResponse> handleRestClientResponseException(RestClientResponseException restClientResponseException, HttpServletRequest httpServletRequest) {
        mappedDiagnosticContextRequestFieldSetter.set(XLogger.Level.ERROR, restClientResponseException, httpServletRequest);

        return restClientErrorResponseCreator.create(restClientResponseException);
    }

    /**
     * Handle the REST client exception.
     *
     * @param restClientException an HTTP REST client exception
     * @param httpServletRequest  containing the consumer's request
     * @return the response entity containing the error response
     */
    @ExceptionHandler({RestClientException.class})
    public ResponseEntity<ErrorResponse> handleRestClientException(RestClientException restClientException, HttpServletRequest httpServletRequest) {
        mappedDiagnosticContextRequestFieldSetter.set(XLogger.Level.ERROR, restClientException, httpServletRequest);

        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.GENERIC_4XX_ERROR, ControllerExceptionHandler.GENERIC_4XX_HEADER_MESSAGE,
                errorMessagePropertyReader.getErrorMessage(ErrorCode.GENERIC_4XX_ERROR),
                CryptoUtil.tryJasyptEncrypt(ApplicationServerException.getStackTrace(restClientException, System.lineSeparator())));

        return ResponseEntity.badRequest().body(errorResponse);
    }

    /**
     * Handle the client abort exception.
     *
     * @param clientAbortException the client abort exception
     * @param httpServletRequest   containing the consumer's request
     * @return the response entity containing the error response
     */
    @ExceptionHandler(ClientAbortException.class)
    public ResponseEntity<ErrorResponse> handleClientAbortException(ClientAbortException clientAbortException, HttpServletRequest httpServletRequest) {
        mappedDiagnosticContextRequestFieldSetter.set(XLogger.Level.WARN, clientAbortException, httpServletRequest);

        // Create and return the error response
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.GENERIC_4XX_ERROR,
                ControllerExceptionHandler.GENERIC_4XX_HEADER_MESSAGE,
                "The user has exited prior to service completion.",
                "ClientAbortException was caught.");

        // Note: there is currently no industry HTTP standard status code for client abort exception
        //       i.e. although the client chose to close the connection implies a 4XX series,
        //       there is no existing, matching 4XX status code of this description,
        //       so we are returning custom 499 instead
        // Considerations: 408 REQUEST TIMEOUT (the request did not timeout but rather, the response is waiting)
        return ResponseEntity.status(499).body(errorResponse);
    }

}

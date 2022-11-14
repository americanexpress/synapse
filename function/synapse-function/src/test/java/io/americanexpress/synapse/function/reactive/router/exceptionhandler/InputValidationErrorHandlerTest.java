///*
// * Copyright 2020 American Express Travel Related Services Company, Inc.
// *
// * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
// * in compliance with the License. You may obtain a copy of the License at
// *
// * http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software distributed under the License
// * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
// * or implied. See the License for the specific language governing permissions and limitations under
// * the License.
// */
//package io.americanexpress.synapse.function.reactive.router.exceptionhandler;
//
//import io.americanexpress.synapse.framework.exception.model.ErrorCode;
//import io.americanexpress.synapse.function.reactive.model.ErrorResponse;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertAll;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class InputValidationErrorHandlerTest {
//
//    private static final InputValidationErrorHandler INPUT_VALIDATION_ERROR_HANDLER = new InputValidationErrorHandler();
//
//    @Mock
//    private BindingResult bindingResult;
//
//    @Test
//    void handleInputValidationErrorMessage_givenNullBindingResult_expectedNullPointerException() {
//        BindingResult bindingResult = null;
//        assertThrows(NullPointerException.class, () -> INPUT_VALIDATION_ERROR_HANDLER.handleInputValidationErrorMessage(bindingResult));
//    }
//
//    @Test
//    void handleInputValidationErrorMessage_givenBindingResultContainsNoErrors_expectedNullPointerException() {
//        when(bindingResult.getFieldErrors()).thenReturn(null);
//        assertThrows(NullPointerException.class, () -> INPUT_VALIDATION_ERROR_HANDLER.handleInputValidationErrorMessage(bindingResult));
//    }
//
//    @Test
//    void handleInputValidationErrorMessage_givenBindingResultContainsEmptyErrors_expectedNoUserMessage() {
//        when(bindingResult.getFieldErrors()).thenReturn(new ArrayList<>());
//        ErrorResponse actual = INPUT_VALIDATION_ERROR_HANDLER.handleInputValidationErrorMessage(bindingResult);
//        assertErrorResponse("", actual);
//    }
//
//    @Test
//    void handleInputValidationErrorMessage_givenBindingResultContainsFieldWithDefaultMessage_expectedFieldDefaultErrorMessage() {
//        String expectedField = "field";
//        String expectedDefaultMessage = "defaultMessage";
//        List<FieldError> fieldErrors = new ArrayList<>();
//        FieldError fieldError = new FieldError("name", expectedField, expectedDefaultMessage);
//        fieldErrors.add(fieldError);
//        when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);
//        ErrorResponse actual = INPUT_VALIDATION_ERROR_HANDLER.handleInputValidationErrorMessage(bindingResult);
//        assertErrorResponse(expectedField + " " + expectedDefaultMessage, actual);
//    }
//
//    @Test
//    void handleInputValidationErrorMessage_givenBindingResultContainsFieldWithValid_expectedDefaultErrorMessage() {
//        String expectedDefaultMessage = "defaultMessage";
//        List<FieldError> fieldErrors = new ArrayList<>();
//        FieldError fieldError = new FieldError("name", "Valid", expectedDefaultMessage);
//        fieldErrors.add(fieldError);
//        when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);
//        ErrorResponse actual = INPUT_VALIDATION_ERROR_HANDLER.handleInputValidationErrorMessage(bindingResult);
//        assertErrorResponse(expectedDefaultMessage, actual);
//    }
//
//    private void assertErrorResponse(String expectedUserMessage, ErrorResponse actualErrorResponse) {
//        ErrorCode expectedErrorCode = ErrorCode.GENERIC_4XX_ERROR;
//        String expectedHeaderMessage = ControllerExceptionHandler.GENERIC_4XX_HEADER_MESSAGE;
//        String expectedDeveloperMessage = "Input validation";
//        assertAll("Error response values",
//                () -> assertEquals(expectedErrorCode, actualErrorResponse.getCode()),
//                () -> assertEquals(expectedHeaderMessage, actualErrorResponse.getMessage()),
//                () -> assertEquals(expectedUserMessage, actualErrorResponse.getMoreInfo()),
//                () -> assertEquals(expectedDeveloperMessage, actualErrorResponse.getDeveloperMessage()));
//    }
//}

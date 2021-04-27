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
package com.americanexpress.synapse.framework.exception;

import com.americanexpress.synapse.framework.exception.helper.ErrorMessagePropertyReader;
import com.americanexpress.synapse.framework.exception.model.ErrorCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;


import static org.junit.jupiter.api.Assertions.assertThrows;

class ErrorMessagePropertyReaderTest {

	private static final ErrorMessagePropertyReader ERROR_MESSAGE_PROPERTY_READER = new ErrorMessagePropertyReader(getErrorMessageSource());

	private static final MessageSource getErrorMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:error-messages");
        return messageSource;
	}

	@Test
	void getErrorMessage_givenNullErrorCode_expectedNullPointerException() {
		assertThrows(NullPointerException.class, () -> ERROR_MESSAGE_PROPERTY_READER.getErrorMessage(null));
	}

	@Test
	void getErrorMessage_givenErrorCodeWithNoArguments_expectedErrorMessage() {
		String[] args = null;
		String expected = "Your client has issued a malformed or illegal request.";
		String actual = ERROR_MESSAGE_PROPERTY_READER.getErrorMessage(ErrorCode.GENERIC_4XX_ERROR, args);
		Assertions.assertEquals(expected, actual);
	}

	@Test
	void getErrorMessage_givenErrorCodeWithArguments_expectedErrorMessageWithArguments() {
		String[] args = {"Correlation-ID"};
		String expected = "Missing required HTTP header: Correlation-ID.";
		String actual = ERROR_MESSAGE_PROPERTY_READER.getErrorMessage(ErrorCode.MISSING_HTTP_HEADER_ERROR, args);
		Assertions.assertEquals(expected, actual);
	}
}

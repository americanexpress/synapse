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
package io.americanexpress.synapse.client.rest.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

/**
 * {@code RestClientLogFormatterTest} verifies the URI redaction of the formatter.
 */
class RestClientLogFormatterTest {

	private final RestClientLogFormatter formatter = new RestClientLogFormatter();

	@Test
	void redactSensitiveDataFromUri_nullUri_returnsNull() {
		assertNull(formatter.redactSensitiveDataFromUri(null));
	}

	@ParameterizedTest
	@CsvSource(delimiterString = " -> ", value = {
		"https://api.example.com/v1/token -> https://api.example.com/v1/token",
		"https://api.example.com -> https://api.example.com",
		"https://api.example.com/ -> https://api.example.com/",
		"https://api.example.com:8443/v1 -> https://api.example.com:8443/v1",
		"http://localhost:8080/a/b/c -> http://localhost:8080/a/b/c",
		"https://bob:s3cret@api.example.com/v1 -> https://[REDACTED]@api.example.com/v1",
		"https://bob@api.example.com/v1 -> https://[REDACTED]@api.example.com/v1",
		"https://api.example.com/v1?api_key=s3cret&x=1 -> https://api.example.com/v1?[REDACTED]",
		"https://api.example.com/v1#access_token=s3cret -> https://api.example.com/v1#[REDACTED]",
		"https://bob:s3cret@api.example.com:8443/v1/token?client_secret=abc&x=1#frag -> https://[REDACTED]@api.example.com:8443/v1/token?[REDACTED]#[REDACTED]",
		"https://api.example.com/a%20b/c?q=%20 -> https://api.example.com/a%20b/c?[REDACTED]",
		"https://[2001:db8::1]:8443/v1?token=abc -> https://[2001:db8::1]:8443/v1?[REDACTED]"
	})
	void redactSensitiveDataFromUri_replacesUserInfoQueryAndFragment(String uri, String expected) {
		assertEquals(expected, formatter.redactSensitiveDataFromUri(URI.create(uri)));
	}

	@Test
	void redactSensitiveDataFromUri_relativeUri_keepsPathAndMarksQuery() {
		assertEquals("/v1/token?[REDACTED]", formatter.redactSensitiveDataFromUri(URI.create("/v1/token?token=abc")));
	}

	@Test
	void redactSensitiveDataFromUri_opaqueUriWithoutHost_doesNotLeakContent() {
		assertEquals("mailto://", formatter.redactSensitiveDataFromUri(URI.create("mailto:bob:s3cret@example.com?subject=x")));
	}

	@Test
	void redactSensitiveDataFromUri_processingFailure_returnsRedactedAndDoesNotThrow() {
		var uri = Mockito.mock(URI.class);
		Mockito.when(uri.getScheme()).thenThrow(new IllegalStateException("boom"));
		assertEquals("[REDACTED]", formatter.redactSensitiveDataFromUri(uri));
	}
}

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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.atomic.AtomicBoolean;

import io.americanexpress.synapse.framework.exception.ApplicationClientException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.mock.http.client.MockClientHttpRequest;
import org.springframework.mock.http.client.MockClientHttpResponse;

/**
 * {@code RestClientLoggingTest} verifies that sensitive data is not logged.
 */
class RestClientLoggingTest {

	private static final String SECRET = "synthetic-secret-value";
	private static final byte[] BODY = ("{\"username\":\"bob\",\"password\":\"" + SECRET + "\"}").getBytes();

	private MockClientHttpRequest request() {
		var request = new MockClientHttpRequest(HttpMethod.POST, URI.create("https://user:" + SECRET + "@host:8443/path?api_key=" + SECRET));
		request.getHeaders().setBearerAuth(SECRET);
		request.getHeaders().add("Cookie", "sid=" + SECRET);
		request.getHeaders().setContentType(MediaType.APPLICATION_JSON);
		return request;
	}

	private ClientHttpResponse response() {
		var response = new MockClientHttpResponse(("{\"access_token\":\"" + SECRET + "\"}").getBytes(), HttpStatus.FORBIDDEN);
		response.getHeaders().add("Set-Cookie", "sid=" + SECRET);
		return response;
	}

	@Test
	void defaultFormatter_doesNotLogHeadersBodiesOrUriSecrets() throws IOException {
		var formatter = new RestClientLogFormatter();
		var output = formatter.formatClientRequest(request(), BODY) + formatter.formatClientResponse(response());
		assertFalse(output.contains(SECRET));
		assertTrue(output.contains("https://[REDACTED]@host:8443/path?[REDACTED]"));
		assertTrue(output.contains("403"));
	}

	@Test
	void redactDisabled_formatterKeepsLegacyOutput() throws IOException {
		var formatter = new RestClientLogFormatter(false);
		var request = formatter.formatClientRequest(request(), BODY);
		var response = formatter.formatClientResponse(response());
		assertTrue(request.contains(SECRET));
		assertTrue(request.contains("HTTP Headers="));
		assertTrue(response.contains("access_token"));
	}

	@Test
	void interceptor_defaultLeavesResponseReadable() throws IOException {
		var interceptor = new RestClientLoggingInterceptor(new RestClientLogFormatter());
		var response = interceptor.intercept(request(), BODY, (req, body) -> response());
		assertTrue(new String(response.getBody().readAllBytes()).contains("access_token"));
	}

	@Test
	void errorHandler_doesNotThrowOnLoggingSecrets() {
		var handler = new io.americanexpress.synapse.client.rest.handler.BaseRestResponseErrorHandler() { };
		org.junit.jupiter.api.Assertions.assertThrows(io.americanexpress.synapse.framework.exception.ApplicationClientException.class,
			() -> handler.handleError(response()));
	}

	@Test
	void interceptor_loggingFailureDoesNotBreakTheCall() throws IOException {
		var failingFormatter = new RestClientLogFormatter() {
			@Override
			public String redactSensitiveDataFromUri(URI uri) {
				throw new IllegalStateException(SECRET);
			}
		};
		var response = new RestClientLoggingInterceptor(failingFormatter).intercept(request(), BODY, (req, body) -> response());
		assertTrue(new String(response.getBody().readAllBytes()).contains("access_token"));
	}

	@Test
	void interceptor_legacyLoggingFailureDoesNotBreakTheCall() throws IOException {
		var failingFormatter = new RestClientLogFormatter(false) {
			@Override
			public String formatClientRequest(org.springframework.http.HttpRequest httpRequest, byte[] bodyBytes) {
				throw new IllegalStateException(SECRET);
			}
		};
		var response = new RestClientLoggingInterceptor(failingFormatter).intercept(request(), BODY, (req, body) -> response());
		assertTrue(new String(response.getBody().readAllBytes()).contains("access_token"));
	}

	@Test
	void errorHandler_redactDisabled_keepsResponseBodyInDeveloperMessage() {
		var handler = new io.americanexpress.synapse.client.rest.handler.BaseRestResponseErrorHandler() {
			{
				redactSensitiveLogs = false;
			}
		};
		var ex = org.junit.jupiter.api.Assertions.assertThrows(ApplicationClientException.class,
                                                               () -> handler.handleError(response()));
		assertTrue(ex.getDeveloperMessage().contains(SECRET));
	}

	@Test
	void errorHandler_defaultKeepsStatusButNotBody() {
		var handler = new io.americanexpress.synapse.client.rest.handler.BaseRestResponseErrorHandler() { };
		var ex = org.junit.jupiter.api.Assertions.assertThrows(ApplicationClientException.class,
			() -> handler.handleError(response()));
		assertTrue(ex.getDeveloperMessage().contains("403"));
		assertFalse(ex.getDeveloperMessage().contains("access_token"));
	}

	@ParameterizedTest
	@CsvSource({"200,false", "302,false", "399,false", "400,true", "403,true", "499,true", "500,true", "503,true", "599,true"})
	void interceptor_logsOnlyClientAndServerErrorsIncludingNonStandardCodes(int statusCode, boolean expectedLogged) throws IOException {
		var logged = new AtomicBoolean();
		var formatter = new RestClientLogFormatter() {
			@Override
			public String redactSensitiveDataFromUri(URI uri) {
				logged.set(true);
				return super.redactSensitiveDataFromUri(uri);
			}
		};
		var response = new RestClientLoggingInterceptor(formatter).intercept(request(), BODY,
			(req, body) -> new MockClientHttpResponse("{}".getBytes(), HttpStatusCode.valueOf(statusCode)));
		assertEquals(statusCode, response.getStatusCode().value());
		assertEquals(expectedLogged, logged.get());
	}
}

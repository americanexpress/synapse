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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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
		assertTrue(output.contains("https://host:8443/path"));
		assertTrue(output.contains("403"));
	}

	@Test
	void optInFormatter_redactsSensitiveHeadersAndBodies() throws IOException {
		var formatter = new RestClientLogFormatter(true);
		var output = formatter.formatClientRequest(request(), BODY) + formatter.formatClientResponse(response());
		assertFalse(output.contains(SECRET));
		assertTrue(output.contains("bob"));
		assertTrue(output.contains(RestClientLogFormatter.REDACTED));
	}

	@Test
	void redactBody_handlesFormAndXml() {
		var formatter = new RestClientLogFormatter(true);
		var output = formatter.redactBody("grant_type=x&client_secret=" + SECRET + "&a=b <password>" + SECRET + "</password>");
		assertFalse(output.contains(SECRET));
		assertTrue(output.contains("grant_type=x"));
	}
}

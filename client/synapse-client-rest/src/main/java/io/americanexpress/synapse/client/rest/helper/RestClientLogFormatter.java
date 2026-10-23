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

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

/**
 * {@code RestClientLogFormatter} class is used to log client requests and responses.
 * By default ({@code synapse.client.rest.logging.redact-sensitive-logs=true}) only non-sensitive
 * metadata is formatted: headers and bodies are omitted and the URI is stripped of user info and query.
 * When the property is set to {@code false} the full URI, headers and bodies are formatted,
 * which may expose credentials or personal data.
 *
 * @author Paolo Claudio
 */
@Component
public class RestClientLogFormatter {

	static final String REDACTED = "[REDACTED]";

	private final boolean redactSensitiveLogs;

	/**
	 * Creates a formatter that redacts sensitive data. Kept for backward compatibility with code
	 * that instantiates or extends this class without arguments; Spring uses the annotated constructor.
	 */
	public RestClientLogFormatter() {
		this(true);
	}

	/**
	 * Creates a formatter.
	 * @param redactSensitiveLogs false to format the full URI, headers and bodies
	 */
	@Autowired
	public RestClientLogFormatter(@Value("${synapse.client.rest.logging.redact-sensitive-logs:true}") boolean redactSensitiveLogs) {
		this.redactSensitiveLogs = redactSensitiveLogs;
		if (!redactSensitiveLogs) {
			LoggerFactory.getLogger(RestClientLogFormatter.class).warn(
				"synapse.client.rest.logging.redact-sensitive-logs is false: full REST client requests and responses, "
					+ "including headers and bodies, are logged and may expose sensitive data.");
		}
	}

	/**
	 * Format the client request.
	 * @param httpRequest containing the request information
	 * @param bodyBytes containing the request body
	 * @return the formatted client request
	 */
	public String formatClientRequest(HttpRequest httpRequest, byte[] bodyBytes) {
		if (redactSensitiveLogs) {
			return String.format("Client Request: URI=%s, HTTP Method=%s",
								 redactSensitiveDataFromUri(httpRequest.getURI()), httpRequest.getMethod());
		}
		var formattedClientRequest = new String(bodyBytes, getCharset(httpRequest));
		return String.format("Client Request: URI=%s, HTTP Method=%s, HTTP Headers=%s, Request=%s",
			httpRequest.getURI(), httpRequest.getMethod(), httpRequest.getHeaders(), formattedClientRequest);
	}

	/**
	 * Format the client response. When redaction is disabled this reads the response body.
	 * @param clientHttpResponse containing the response information
	 * @return the formatted client response
	 * @throws IOException whenever an input/output exception occurs
	 */
	public String formatClientResponse(ClientHttpResponse clientHttpResponse) throws IOException {
		if (redactSensitiveLogs) {
			return String.format("Client Response: HTTP Status=%s", clientHttpResponse.getStatusCode());
		}
		var formattedClientResponse = new String(StreamUtils.copyToByteArray(clientHttpResponse.getBody()), getCharset(clientHttpResponse));
		return String.format("Client Response: HTTP Status=%s, HTTP Headers=%s, Response=%s",
			clientHttpResponse.getStatusCode(), clientHttpResponse.getHeaders(), formattedClientResponse);
	}

	/**
	 * Replace the user info, query and fragment of the URI with {@code [REDACTED]} as they may carry secrets.
	 * This method never throws; if the URI cannot be processed the whole URI is replaced with {@code [REDACTED]}.
	 * @param uri the URI
	 * @return the URI containing only the scheme, host, port and path, with removed parts marked as redacted
	 */
	public String redactSensitiveDataFromUri(URI uri) {
		if (uri == null) {
			return null;
		}
		try {
			var sb = new StringBuilder();
			if (uri.getScheme() != null) {
				sb.append(uri.getScheme()).append("://");
			}
			if (uri.getRawUserInfo() != null) {
				sb.append(REDACTED).append('@');
			}
			if (uri.getHost() != null) {
				sb.append(uri.getHost());
				if (uri.getPort() != -1) {
					sb.append(':').append(uri.getPort());
				}
			}
			if (uri.getRawPath() != null) {
				sb.append(uri.getRawPath());
			}
			if (uri.getRawQuery() != null) {
				sb.append('?').append(REDACTED);
			}
			if (uri.getRawFragment() != null) {
				sb.append('#').append(REDACTED);
			}
			return sb.toString();
		} catch (RuntimeException e) {
			return REDACTED;
		}
	}

	/**
	 * Get the charset based on the HTTP message or ISO_8859_1 if none is found.
	 * @param httpMessage containing the charset information
	 * @return the charset based on the HTTP message or ISO_8859_1 if none is found
	 */
	private Charset getCharset(HttpMessage httpMessage) {
		return Optional.ofNullable(httpMessage)
			.map(HttpMessage::getHeaders)
			.map(HttpHeaders::getContentType)
			.map(MediaType::getCharset)
			.orElse(StandardCharsets.ISO_8859_1);
	}

	/**
	 * @return true if sensitive data is kept out of the formatted output
	 */
	public boolean isRedactSensitiveLogs() {
		return redactSensitiveLogs;
	}
}

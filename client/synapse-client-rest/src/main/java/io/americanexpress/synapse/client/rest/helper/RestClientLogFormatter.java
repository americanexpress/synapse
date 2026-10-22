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
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
 * By default only non-sensitive metadata is formatted. Headers and bodies are formatted
 * (with redaction) only when payload logging is explicitly enabled through the
 * {@code synapse.client.rest.logging.include-payload} property.
 *
 * @author Paolo Claudio
 */
@Component
public class RestClientLogFormatter {

	static final String REDACTED = "[REDACTED]";

	private static final String SENSITIVE_NAMES =
		"authorization|proxy-authorization|cookie|set-cookie|x-api-key|api[-_]?key|apikey|secret|client[-_]?secret|"
			+ "password|passwd|pwd|token|access[-_]?token|refresh[-_]?token|id[-_]?token|credential|assertion|signature";

	private static final Pattern SENSITIVE_HEADER = Pattern.compile(".*(" + SENSITIVE_NAMES + ").*", Pattern.CASE_INSENSITIVE);

	/** Matches JSON ("name":"value") and form/query (name=value) pairs with sensitive names. */
	private static final Pattern SENSITIVE_JSON_FIELD = Pattern.compile(
		"(\"[^\"]*(?:" + SENSITIVE_NAMES + ")[^\"]*\"\\s*:\\s*)(\"(?:\\\\.|[^\"\\\\])*\"|[^,}\\]\\s]+)", Pattern.CASE_INSENSITIVE);
	private static final Pattern SENSITIVE_FORM_FIELD = Pattern.compile(
		"([?&;]?[^=&\\s\"]*(?:" + SENSITIVE_NAMES + ")[^=&\\s\"]*=)([^&\\s\"]*)", Pattern.CASE_INSENSITIVE);
	private static final Pattern SENSITIVE_XML_FIELD = Pattern.compile(
		"(<([^>/\\s]*(?:" + SENSITIVE_NAMES + ")[^>/\\s]*)[^>]*>)[^<]*(</)", Pattern.CASE_INSENSITIVE);

	private final boolean includePayload;

	/**
	 * Creates a formatter that logs metadata only.
	 */
	public RestClientLogFormatter() {
		this(false);
	}

	/**
	 * Creates a formatter.
	 * @param includePayload true to also format redacted headers and bodies (opt-in diagnostics)
	 */
	@Autowired
	public RestClientLogFormatter(@Value("${synapse.client.rest.logging.include-payload:false}") boolean includePayload) {
		this.includePayload = includePayload;
	}

	/**
	 * @return true if redacted headers and bodies are included in formatted output
	 */
	public boolean isPayloadIncluded() {
		return includePayload;
	}

	/**
	 * Format the client request.
	 * @param httpRequest containing the request information
	 * @param bodyBytes bytes of the request
	 * @return the formatted client request
	 */
	public String formatClientRequest(HttpRequest httpRequest, byte[] bodyBytes) {
		var formatted = String.format("Client Request: URI=%s, HTTP Method=%s",
			sanitizeUri(httpRequest.getURI()), httpRequest.getMethod());
		if (!includePayload) {
			return formatted;
		}
		var body = bodyBytes == null ? "" : new String(bodyBytes, getCharset(httpRequest));
		return String.format("%s, HTTP Headers=%s, Request=%s",
			formatted, redactHeaders(httpRequest.getHeaders()), redactBody(body));
	}

	/**
	 * Format the client response.
	 * @param clientHttpResponse containing the response information
	 * @return the formatted client response
	 * @throws IOException whenever an input/output exception occurs
	 */
	public String formatClientResponse(ClientHttpResponse clientHttpResponse) throws IOException {
		var formatted = String.format("Client Response: HTTP Status=%s", clientHttpResponse.getStatusCode());
		if (!includePayload) {
			return formatted;
		}
		var body = new String(StreamUtils.copyToByteArray(clientHttpResponse.getBody()), getCharset(clientHttpResponse));
		return String.format("%s, HTTP Headers=%s, Response=%s",
			formatted, redactHeaders(clientHttpResponse.getHeaders()), redactBody(body));
	}

	/**
	 * Remove user info, query and fragment from the URI as they may carry secrets.
	 * @param uri the URI
	 * @return the sanitized URI
	 */
	public String sanitizeUri(URI uri) {
		if (uri == null) {
			return null;
		}
		var sb = new StringBuilder();
		if (uri.getScheme() != null) {
			sb.append(uri.getScheme()).append("://");
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
		return sb.toString();
	}

	String redactHeaders(HttpHeaders headers) {
		return headers.entrySet().stream()
			.map(e -> e.getKey() + "=" + (SENSITIVE_HEADER.matcher(e.getKey()).matches() ? REDACTED : e.getValue()))
			.collect(Collectors.joining(", ", "[", "]"));
	}

	String redactBody(String body) {
		var result = SENSITIVE_JSON_FIELD.matcher(body).replaceAll("$1\"" + REDACTED + "\"");
		result = SENSITIVE_FORM_FIELD.matcher(result).replaceAll("$1" + REDACTED);
		return SENSITIVE_XML_FIELD.matcher(result).replaceAll("$1" + REDACTED + "$3");
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
}

package io.americanexpress.synapse.client.rest.interceptor;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

/**
 * {@code ClientLogFormatter} class is used to log client requests and responses.
 * @author Paolo Claudio
 *
 */
@Component
public class ClientLogFormatter {
	
	/**
	 * Format the client request.
	 * @param httpRequest containing the request information
	 * @param bodyBytes bytes of the request
	 * @return the formatted client request
	 */
	public String formatClientRequest(HttpRequest httpRequest, byte[] bodyBytes) {
		URI uri = httpRequest.getURI();
		HttpMethod httpMethod = httpRequest.getMethod();
		HttpHeaders httpHeaders = httpRequest.getHeaders();
		String formattedClientRequest = new String(bodyBytes, getCharset(httpRequest));
		return String.format("Client Request: URI=%s, HTTP Method=%s, URI=%s, Request=%s",
			uri, httpMethod, httpHeaders, formattedClientRequest);
	}

	/**
	 * Format the client response.
	 * @param clientHttpResponse containing the response information
	 * @return the formatted client response
	 * @throws IOException whenever an input/output exception occurs
	 */
	public String formatClientResponse(ClientHttpResponse clientHttpResponse) throws IOException {
		HttpStatus httpStatus = clientHttpResponse.getStatusCode();
		HttpHeaders httpHeaders = clientHttpResponse.getHeaders();
		String formattedClientResponse = new String(StreamUtils.copyToByteArray(clientHttpResponse.getBody()), getCharset(clientHttpResponse));
		return String.format("Client Response: HTTP Status=%s, HTTP Headers=%s, Response=%s",
			httpStatus, httpHeaders, formattedClientResponse);
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

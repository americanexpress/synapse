package io.americanexpress.synapse.client.rest.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.List;

import io.americanexpress.synapse.client.rest.model.QueryParameter;
import io.americanexpress.synapse.framework.test.CommonAssertionMessages;
import org.junit.jupiter.api.Test;

class UrlBuilderTest {

	private static final String MOCK_BASE_ENDPOINT = "https://www.americanexpress.com";
	
	@Test
	void build_givenNullUrlNullQueryParametersNullPathVariables_expectedEmptyString() {
		String[] pathVariables = null;
		String actual = UrlBuilder.build(null, null, pathVariables);
		assertNull( actual, CommonAssertionMessages.VALUE_NOT_NULL);
	}
	
	@Test
	void build_givenUrlNullQueryParametersNullPathVariables_expectedUnchangedUrl() {
		String[] pathVariables = null;
		String actual = UrlBuilder.build(MOCK_BASE_ENDPOINT, null, pathVariables);
		assertEquals(MOCK_BASE_ENDPOINT, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
	}
	
	@Test
	void build_givenUrlNullQueryParametersPathVariable_expectedUrlWithPathVariable() {
		String pathVariable = "examples";
		String expected = MOCK_BASE_ENDPOINT + "/" + pathVariable;
		String actual = UrlBuilder.build(MOCK_BASE_ENDPOINT, null, pathVariable);
		assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
	}
	
	@Test
	void build_givenUrlQueryParameterPathVariable_expectedUrlWithPathVariableAndQueryParameter() {
		String pathVariable = "pets";
		String key = "name";
		String value = "Sparky";
		List<QueryParameter> queryParameters = Arrays.asList(new QueryParameter(key, value));
		String expected = MOCK_BASE_ENDPOINT + "/" + pathVariable + "?" + key + "=" + value;
		String actual = UrlBuilder.build(MOCK_BASE_ENDPOINT, queryParameters, pathVariable);
		assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
	}
}

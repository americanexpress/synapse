package io.americanexpress.synapse.client.rest.client;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import io.americanexpress.synapse.client.rest.model.QueryParameter;

/**
 * {@code UrlBuilder} class builds a new URL that includes path variables and/or query parameters.
 * @author Paolo Claudio
 */
final class UrlBuilder {
	
	/**
	 * Default constructor creates a new instance of PathVariableUriCreator with default values.
	 */
	private UrlBuilder() {
		
		// A class containing only static methods is a utility class that requires a private no-argument default constructor
	}

	/**
	 * Get the new URL if there are path variables and/or query parameters.
	 * @param url base URL
	 * @param queryParameters query parameters
	 * @param pathVariables path variables
	 * @return the new URL if any path variables and/or query parameters are present; otherwise the same url will be returned
	 */
	static String build(String url, List<QueryParameter> queryParameters, String... pathVariables) {
		return Optional.ofNullable(url)
			.filter(StringUtils::isNotBlank)
			.map(uniformResourceLocator -> new StringBuilder(uniformResourceLocator)
				.append(PathVariableUriCreator.create(pathVariables))
				.append(QueryParameterUriCreator.create(queryParameters))
				.toString())
			.orElse(url);
	}
}

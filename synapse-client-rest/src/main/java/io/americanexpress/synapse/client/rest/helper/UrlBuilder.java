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

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import io.americanexpress.synapse.client.rest.model.QueryParameter;

/**
 * {@code UrlBuilder} class builds a new URL that includes path variables and/or query parameters.
 * 
 * @author Paolo Claudio
 */
public final class UrlBuilder {
	
	/**
	 * Default constructor creates a new instance of UrlBuilder with default values.
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
	public static String build(String url, List<QueryParameter> queryParameters, String... pathVariables) {
		return Optional.ofNullable(url)
			.filter(StringUtils::isNotBlank)
			.map(uniformResourceLocator -> new StringBuilder(uniformResourceLocator)
				.append(PathVariableUriCreator.create(pathVariables))
				.append(QueryParameterUriCreator.create(queryParameters))
				.toString())
			.orElse(url);
	}
}

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

import io.americanexpress.synapse.client.rest.model.QueryParameter;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * {@code QueryParameterUriCreator} class creates a new URI that includes query parameters.
 * @author Paolo Claudio
 */
final class QueryParameterUriCreator {

	/**
	 * Default constructor creates a new instance of QueryParameterUriCreator with default values.
	 */
	private QueryParameterUriCreator() {
		
		// A class containing only static methods is a utility class that requires a private no-argument default constructor
	}
	
    /**
     * Get the new URI if there are query parameters present in the format of
     * <code>?key1=value1&key2=value2</code>
     *
     * @param queryParameters parameters that need to be added to the URI
     * @return the new URI containing the query parameters if any are present; empty string otherwise
     **/
    static String create(List<QueryParameter> queryParameters) {
    	
    	// If there are any query parameters, join them together 
    	// Otherwise, return an empty string
    	return Optional.ofNullable(queryParameters)
    		.filter(CollectionUtils::isNotEmpty)
    		.map(queryParameterElements -> {
    			
    			// Join each query parameter in the form key1=value1 delimited by &
    			String formattedQueryParameters = queryParameterElements.stream()
	    			.filter(queryParameterElement -> Objects.nonNull(queryParameterElement)
	    				&& StringUtils.isNotBlank(queryParameterElement.getKey())
	    				&& StringUtils.isNotBlank(queryParameterElement.getValue()))
	    			.map(QueryParameter::format)
	    			.collect(Collectors.joining("&"));
    			
    			return StringUtils.isNotBlank(formattedQueryParameters) ? "?" + formattedQueryParameters : StringUtils.EMPTY;
    		})
    		.orElse(StringUtils.EMPTY);
    }
}

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
package io.americanexpress.synapse.client.rest.client;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * {@code PathVariableUriCreator} class creates a new URI that includes query parameters.
 * @author Paolo Claudio
 */
final class PathVariableUriCreator {

	/**
	 * Default constructor creates a new instance of PathVariableUriCreator with default values.
	 */
	private PathVariableUriCreator() {
		
		// A class containing only static methods is a utility class that requires a private no-argument default constructor
	}
	
    /**
     * Get the new URI if there are path variables present in the format of
     * <code>/pathVariable1/pathVariable2</code>
     *
     * @param pathVariables variables that need to be added to the URI
     * @return the new URI containing the path variables if any are present; empty string otherwise
     */
    static String create(String... pathVariables) {

    	// If there are any path variables, join them together
    	// Otherwise, return an empty string
    	return Optional.ofNullable(pathVariables)
    		.filter(ArrayUtils::isNotEmpty)
    		.map(pathVariableElements -> {
    			
    			// Join each path variable in the form of pathVariable1/pathVariable2
    			String formattedPathVariables = Arrays.stream(pathVariableElements)
    				.filter(StringUtils::isNotBlank)
    				.collect(Collectors.joining("/"));
    			
    			return StringUtils.isNotBlank(formattedPathVariables) ? "/" + formattedPathVariables : StringUtils.EMPTY;
    		})
    		.orElse(StringUtils.EMPTY);
    }
}

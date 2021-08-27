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

import io.americanexpress.synapse.client.rest.model.QueryParameter;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.StringJoiner;

/**
 * {@code QueryParameterUriCreator} class is used to create a new URI that includes query parameters.
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
     * Get the new URI if there are query parameters present.
     *
     * @param queryParameters parameters that need to be added to the URI
     * @return the new URI containing the query parameters if any are present; empty string otherwise
     **/
    public static String create(List<QueryParameter> queryParameters) {
    	
        // Set new uri to old URI
        StringBuilder uriBuilder = new StringBuilder();
        if (CollectionUtils.isNotEmpty(queryParameters)) {

            // Needed in between query parameters and end of original URI
            StringJoiner queryParameterJoiner = new StringJoiner("&");
            for (QueryParameter queryParameter : queryParameters) {
                String key = queryParameter.getKey();
                String value = queryParameter.getValue();

                // Additional check so that no one can make a query parameter null = null
                if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
                    String formattedQueryParameter = queryParameter.formattedQueryParameter(key, value);
                    queryParameterJoiner.add(formattedQueryParameter);
                }
            }
            String joinedQueryParameters = queryParameterJoiner.toString();

            // Meaning we were able to add a key=value string to the StringJoiner
            if (StringUtils.isNotBlank(joinedQueryParameters)) {
                uriBuilder.append("?" + joinedQueryParameters);
            }
        }
        return uriBuilder.toString();
    }
}

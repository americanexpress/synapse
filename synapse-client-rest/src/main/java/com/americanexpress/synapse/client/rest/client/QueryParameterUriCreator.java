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
package com.americanexpress.synapse.client.rest.client;

import com.americanexpress.synapse.client.rest.model.QueryParameter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.StringJoiner;

/**
 * QueryParameterUriCreator class is used to create a new URI that includes query parameters.
 *
 */
@Component
class QueryParameterUriCreator {

    /**
     * Get the new URI if there are query parameters present.
     *
     * @param queryParameters parameters that need to be added to the URI
     * @return new URI
     **/
    public String createQueryParameterUri(List<QueryParameter> queryParameters) {
        // Set new uri to old URI
        StringBuilder uriBuilder = new StringBuilder();
        if (!CollectionUtils.isEmpty(queryParameters)) {

            // Needed in between query parameters and end of original URI
            StringJoiner joiner = new StringJoiner("&");
            String formattedQueryParameter;
            String key;
            String value;
            for (QueryParameter queryParameter : queryParameters) {
                key = queryParameter.getKey();
                value = queryParameter.getValue();

                // Additional check so that no one can make a query parameter null = null
                if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
                    formattedQueryParameter = queryParameter.formattedQueryParameter(key, value);
                    joiner.add(formattedQueryParameter);
                }
            }
            String joinedQueryParameters = joiner.toString();

            // Meaning we were able to add a key=value string to the StringJoiner
            if (StringUtils.isNotBlank(joinedQueryParameters)) {
                uriBuilder.append("?" + joinedQueryParameters);
            }
        }
        return uriBuilder.toString();
    }
}

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

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;

import java.util.StringJoiner;

/**
 * PathVariableUriCreator class is used to create a new URI that includes query parameters.
 *
 */
@Component
class PathVariableUriCreator {

    /**
     * Get the new URI if there are query parameters present.
     *
     * @param pathVariables variables that need to be added to the URI
     * @return new URI
     **/
    public String createPathVariableUri(String... pathVariables) {

        // Set new uri to old URI
        StringBuilder uriBuilder = new StringBuilder();
        if (ArrayUtils.isNotEmpty(pathVariables)) {
            uriBuilder.append("/");
            StringJoiner joiner = new StringJoiner("/");
            for (String pathVariable : pathVariables) {
                joiner.add(pathVariable);
            }
            uriBuilder.append(joiner);
        }
        return uriBuilder.toString();
    }
}

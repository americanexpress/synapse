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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PathVariableUriCreatorTest {

    private static final String MOCK_URL = "http://example.com";

    @Test
    public void createPathVariableUri_nullQueryParameters() {
        StringBuilder urlBuilder = new StringBuilder(MOCK_URL);
        PathVariableUriCreator pathVariableUriCreator = new PathVariableUriCreator();
        String actual = pathVariableUriCreator.createPathVariableUri();
        urlBuilder.append(actual);
        assertEquals(MOCK_URL, urlBuilder.toString(), "It will be empty so that nothing is added to the original URI");
    }

    @Test
    public void createPathVariableUri_clean() {
        StringBuilder urlBuilder = new StringBuilder(MOCK_URL);
        PathVariableUriCreator pathVariableUriCreator = new PathVariableUriCreator();
        String actual = pathVariableUriCreator.createPathVariableUri("111111");
        urlBuilder.append(actual);
        assertEquals(MOCK_URL + "/111111", urlBuilder.toString());
    }

    @Test
    public void createPathVariableUri_cleanMoreThanOneVariable() {
        StringBuilder urlBuilder = new StringBuilder(MOCK_URL);
        PathVariableUriCreator pathVariableUriCreator = new PathVariableUriCreator();
        String[] pathVariables = {"111111", "123"};
        String actual = pathVariableUriCreator.createPathVariableUri(pathVariables);
        urlBuilder.append(actual);
        assertEquals(MOCK_URL + "/111111/123", urlBuilder.toString());
    }
}

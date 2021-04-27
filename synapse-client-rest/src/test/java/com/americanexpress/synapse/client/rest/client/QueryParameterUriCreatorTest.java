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
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueryParameterUriCreatorTest {

    private static final String MOCK_URL = "http://example.com";

    @Test
    public void createQueryParameterUri_nullQueryParameters() {
        StringBuilder urlBuilder = new StringBuilder(MOCK_URL);
        QueryParameterUriCreator queryParameterUriCreator = new QueryParameterUriCreator();
        String actual = queryParameterUriCreator.createQueryParameterUri(null);
        urlBuilder.append(actual);
        assertEquals(MOCK_URL, urlBuilder.toString(), "It will be empty so that nothing is added to the original URI");
    }

    @Test
    public void createQueryParameterUri_nullValue() {
        StringBuilder urlBuilder = new StringBuilder(MOCK_URL);
        QueryParameterUriCreator queryParameterUriCreator = new QueryParameterUriCreator();
        List<QueryParameter> queryParameters = new ArrayList<>();
        QueryParameter queryParameter = new QueryParameter("name", null);
        queryParameters.add(queryParameter);
        String actual = queryParameterUriCreator.createQueryParameterUri(queryParameters);
        urlBuilder.append(actual);
        assertEquals(MOCK_URL, urlBuilder.toString());
    }

    @Test
    public void createQueryParameterUri_nullKey() {
        StringBuilder urlBuilder = new StringBuilder(MOCK_URL);
        QueryParameterUriCreator queryParameterUriCreator = new QueryParameterUriCreator();
        List<QueryParameter> queryParameters = new ArrayList<>();
        QueryParameter queryParameter = new QueryParameter(null, "bob");
        queryParameters.add(queryParameter);
        String actual = queryParameterUriCreator.createQueryParameterUri(queryParameters);
        urlBuilder.append(actual);
        assertEquals(MOCK_URL, urlBuilder.toString());
    }

    @Test
    public void createQueryParameterUri_nullKeyAndValue() {
        StringBuilder urlBuilder = new StringBuilder(MOCK_URL);
        QueryParameterUriCreator queryParameterUriCreator = new QueryParameterUriCreator();
        List<QueryParameter> queryParameters = new ArrayList<>();
        QueryParameter queryParameter = new QueryParameter(null, null);
        queryParameters.add(queryParameter);
        String actual = queryParameterUriCreator.createQueryParameterUri(queryParameters);
        urlBuilder.append(actual);
        assertEquals(MOCK_URL, urlBuilder.toString());
    }

    @Test
    public void createQueryParameterUri_clean() {
        StringBuilder urlBuilder = new StringBuilder(MOCK_URL);
        QueryParameterUriCreator queryParameterUriCreator = new QueryParameterUriCreator();
        List<QueryParameter> queryParameters = new ArrayList<>();
        QueryParameter queryParameter = new QueryParameter("name", "bob");
        queryParameters.add(queryParameter);
        String actual = queryParameterUriCreator.createQueryParameterUri(queryParameters);
        urlBuilder.append(actual);
        assertEquals(MOCK_URL + "?name=bob", urlBuilder.toString());
    }

    @Test
    public void createQueryParameterUri_cleanMoreThanOneParameter() {
        StringBuilder urlBuilder = new StringBuilder(MOCK_URL);
        QueryParameterUriCreator queryParameterUriCreator = new QueryParameterUriCreator();
        List<QueryParameter> queryParameters = new ArrayList<>();
        QueryParameter queryParameter = new QueryParameter("name", "bob");
        QueryParameter queryParameter1 = new QueryParameter("age", "57");
        queryParameters.add(queryParameter);
        queryParameters.add(queryParameter1);
        String actual = queryParameterUriCreator.createQueryParameterUri(queryParameters);
        urlBuilder.append(actual);
        assertEquals(MOCK_URL + "?name=bob&age=57", urlBuilder.toString());
    }
}

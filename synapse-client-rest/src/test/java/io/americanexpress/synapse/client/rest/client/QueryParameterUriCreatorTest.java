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
import io.americanexpress.synapse.framework.test.CommonAssertionMessages;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QueryParameterUriCreatorTest {

    @Test
    void createQueryParameterUri_nullQueryParameters() {
        String actual = QueryParameterUriCreator.create(null);
        assertEquals(StringUtils.EMPTY, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }
    
    @Test
    void createQueryParameterUri_queryParametersContainsNull() {
    	List<QueryParameter> queryParameters = new ArrayList<>();
    	queryParameters.add(null);
        String actual = QueryParameterUriCreator.create(queryParameters);
        assertEquals(StringUtils.EMPTY, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void createQueryParameterUri_nullValue() {
        List<QueryParameter> queryParameters = new ArrayList<>();
        QueryParameter queryParameter = new QueryParameter("name", null);
        queryParameters.add(queryParameter);
        String actual = QueryParameterUriCreator.create(queryParameters);
        assertEquals(StringUtils.EMPTY, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void createQueryParameterUri_nullKey() {
        List<QueryParameter> queryParameters = new ArrayList<>();
        QueryParameter queryParameter = new QueryParameter(null, "bob");
        queryParameters.add(queryParameter);
        String actual = QueryParameterUriCreator.create(queryParameters);
        assertEquals(StringUtils.EMPTY, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void createQueryParameterUri_nullKeyAndValue() {
        List<QueryParameter> queryParameters = new ArrayList<>();
        QueryParameter queryParameter = new QueryParameter(null, null);
        queryParameters.add(queryParameter);
        String actual = QueryParameterUriCreator.create(queryParameters);
        assertEquals(StringUtils.EMPTY, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void createQueryParameterUri_clean() {
        List<QueryParameter> queryParameters = new ArrayList<>();
        QueryParameter queryParameter = new QueryParameter("name", "bob");
        queryParameters.add(queryParameter);
        String actual = QueryParameterUriCreator.create(queryParameters);
        assertEquals("?name=bob", actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void createQueryParameterUri_cleanMoreThanOneParameter() {
        List<QueryParameter> queryParameters = new ArrayList<>();
        QueryParameter queryParameter = new QueryParameter("name", "bob");
        QueryParameter queryParameter1 = new QueryParameter("age", "57");
        queryParameters.add(queryParameter);
        queryParameters.add(queryParameter1);
        String actual = QueryParameterUriCreator.create(queryParameters);
        assertEquals("?name=bob&age=57", actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }
}

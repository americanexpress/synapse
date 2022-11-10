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
import io.americanexpress.synapse.framework.test.CommonAssertionMessages;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@code QueryParameterUriCreatorTest} tests the {@link QueryParameterUriCreator}.
 */
class QueryParameterUriCreatorTest {

    @Test
    void create_givenNullQueryParameters_expectedEmptyString() {
        String actual = QueryParameterUriCreator.create(null);
        assertEquals(StringUtils.EMPTY, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }
    
    @Test
    void create_givenQueryParametersContainsNull_expectedEmptyString() {
    	List<QueryParameter> queryParameters = new ArrayList<>();
    	queryParameters.add(null);
        String actual = QueryParameterUriCreator.create(queryParameters);
        assertEquals(StringUtils.EMPTY, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void create_givenQueryParameterNullKeyAndNullValue_expectedEmptyString() {
        List<QueryParameter> queryParameters = Arrays.asList(new QueryParameter(null, null));
        String actual = QueryParameterUriCreator.create(queryParameters);
        assertEquals(StringUtils.EMPTY, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }
    
    @Test
    void create_givenQueryParameterNullKey_expectedEmptyString() {
        List<QueryParameter> queryParameters = Arrays.asList(new QueryParameter(null, "bob"));
        String actual = QueryParameterUriCreator.create(queryParameters);
        assertEquals(StringUtils.EMPTY, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }
    
    @Test
    void create_givenQueryParameterNullValue_expectedEmptyString() {
        List<QueryParameter> queryParameters = Arrays.asList(new QueryParameter("name", null));
        String actual = QueryParameterUriCreator.create(queryParameters);
        assertEquals(StringUtils.EMPTY, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void create_givenQueryParameter_expectedQueryParameter() {
    	String key = "name";
    	String value = "Bob";
        List<QueryParameter> queryParameters = Arrays.asList(new QueryParameter(key, value));
        String expected = "?" + key + "=" + value;
        String actual = QueryParameterUriCreator.create(queryParameters);
        assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void create_givenMultipleQueryParameters_expectedMultipleQueryParameters() {
    	String key1 = "name";
    	String value1 = "Sparky";
    	String key2 = "age";
    	String value2 = "3";
        List<QueryParameter> queryParameters = Arrays.asList(new QueryParameter(key1, value1), new QueryParameter(key2, value2));
        String expected = "?" + key1 + "=" + value1 + "&" + key2 + "=" + value2;
        String actual = QueryParameterUriCreator.create(queryParameters);
        assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }
}

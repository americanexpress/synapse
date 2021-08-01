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
package io.americanexpress.synapse.service.rest.interceptor;

import io.americanexpress.synapse.framework.exception.ApplicationClientException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

class BaseHttpInterceptorTest extends BaseHttpInterceptor {

    private static final String SAMPLE_HTTP_HEADER = "Correlation-ID";

    private BaseHttpInterceptor baseHttpInterceptor;

    @BeforeEach
    private void initialize() {
        baseHttpInterceptor = mock(BaseHttpInterceptor.class, withSettings().useConstructor().defaultAnswer(CALLS_REAL_METHODS));
    }

    @Test
    void preHandle_givenEmptyRequiredHttpHeaderNames_expectedValidHttpHeaders() throws Exception {
        assertTrue(baseHttpInterceptor.preHandle(null, null, null));
    }

    @Test
    void preHandle_givenMissingHttpHeader_expectedApplicationException() {
        when(baseHttpInterceptor.getRequiredHttpHeaderNames()).thenReturn(Arrays.asList(SAMPLE_HTTP_HEADER));
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        when(httpServletRequest.getHeader(SAMPLE_HTTP_HEADER)).thenReturn(null);
        assertThrows(ApplicationClientException.class, () -> baseHttpInterceptor.preHandle(httpServletRequest, null, null));
    }

    @Test
    void preHandle_givenHttpHeader_expectedValidHttpHeaders() throws Exception {
        when(baseHttpInterceptor.getRequiredHttpHeaderNames()).thenReturn(Arrays.asList(SAMPLE_HTTP_HEADER));
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        when(httpServletRequest.getHeader(SAMPLE_HTTP_HEADER)).thenReturn("Sample Value");
        assertTrue(baseHttpInterceptor.preHandle(httpServletRequest, null, null));
    }
}

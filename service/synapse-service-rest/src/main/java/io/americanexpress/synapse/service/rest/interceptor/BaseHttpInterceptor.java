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
import io.americanexpress.synapse.framework.exception.model.ErrorCode;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.americanexpress.synapse.service.rest.model.ServiceHeaderKey.CORRELATION_IDENTIFIER_KEY;
import static io.americanexpress.synapse.service.rest.model.ServiceHeaderKey.USE_CASE_NAME_KEY;

/**
 * {@code BaseHttpInterceptor} class specifies the prototypes for performing HTTP header validations for a service.
 * 
 * @author Paolo Claudio
 */
public abstract class BaseHttpInterceptor implements HandlerInterceptor {

    /**
     * Used to log the HTTP headers.
     */
    private final XLogger logger = XLoggerFactory.getXLogger(getClass());

    
    /**
     * Required HTTP header names.
     */
    protected List<String> requiredHttpHeaderNames = new ArrayList<>(Arrays.asList(HttpHeaders.CONTENT_TYPE, CORRELATION_IDENTIFIER_KEY.getValue(), USE_CASE_NAME_KEY.getValue()));

    /**
     * Should not filter URIs.
     * Specifies list of paths that should not be filtered.
     * This might include health and actuator endpoints.
     */
    protected List<String> urisExcludedFromFilter = new ArrayList<>();

    /**
     * Validate the required HTTP headers.
     *
     * @param request  containing the request of the service that has the HTTP headers
     * @param response containing the response of the service
     * @param handler  used for the interceptor
     * @return a boolean
     * @throws Exception whenever an issue occurs during the prehandling of this request
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.entry(request, response, handler);

        if(!shouldNotFilter(request)){
            // If any of the required HTTP headers are missing, this request is invalid
            String httpHeaderValue;
            for (String requiredHttpHeaderName : getRequiredHttpHeaderNames()) {
                httpHeaderValue = request.getHeader(requiredHttpHeaderName);
                if (httpHeaderValue == null) {
                    throw new ApplicationClientException("Request HTTP Header " + requiredHttpHeaderName + " is missing.", ErrorCode.MISSING_HTTP_HEADER_ERROR, requiredHttpHeaderName);
                }
            }
        }

        // HTTP header validation is successful
        logger.exit();
        return true;
    }

    /**
     * Get the required HTTP header names to be validated.
     *
     * @return the required HTTP header names
     */
    protected List<String> getRequiredHttpHeaderNames() {
        // Note: it is possible that a service needs no request HTTP header validation
        // Should a service require request HTTP header validation, then override this method
        return requiredHttpHeaderNames;
    }

    /**
     * Subclasses can override this method to control what requests should not be filtered.
     * for example override the method with the following to prevent
     * health endpoint : return request.getRequestURI().equals("/health")
     *
     * @param request the incoming request
     * @return true if url should not be filtered
     */
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return urisExcludedFromFilter.contains(request.getRequestURI());
    }
}

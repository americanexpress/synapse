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
package io.americanexpress.synapse.service.rest.controller.exceptionhandler;

import io.americanexpress.synapse.framework.exception.ApplicationServerException;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * {@code RequestPayloadConverter} converts the request payload into a String format.
 */
@Component
public class RequestPayloadConverter {

    /**
     * Converts the payload into a String format.
     *
     * @param request the request
     * @return the request payload as a string
     */
    public String convert(HttpServletRequest request) {
        String requestPayloadAsAString = null;
        if (null != request) {
            ContentCachingRequestWrapper contentCachingRequestWrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
            if (contentCachingRequestWrapper != null) {
                byte[] requestPayloadBytes = contentCachingRequestWrapper.getContentAsByteArray();
                int requestPayloadBytesLength = requestPayloadBytes.length;
                if (requestPayloadBytesLength > 0) {
                    try {
                        requestPayloadAsAString = new String(requestPayloadBytes, 0, requestPayloadBytesLength, contentCachingRequestWrapper.getCharacterEncoding());
                    } catch (UnsupportedEncodingException unsupportedEncodingException) {
                        throw new ApplicationServerException(unsupportedEncodingException);
                    }
                }
            }
        }
        return requestPayloadAsAString;
    }
}

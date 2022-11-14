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

import io.americanexpress.synapse.utilities.common.cryptography.CryptoUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.slf4j.ext.XLogger;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class MappedDiagnosticContextRequestFieldSetter {

    private final RequestPayloadConverter requestPayloadConverter;

    public MappedDiagnosticContextRequestFieldSetter(RequestPayloadConverter requestPayloadConverter) {
        this.requestPayloadConverter = requestPayloadConverter;
    }

    /**
     * Set.
     *
     * @param logLevel           the log level
     * @param throwable          the throwable
     * @param httpServletRequest the http servlet request
     */
    public void set(XLogger.Level logLevel, Throwable throwable, HttpServletRequest httpServletRequest) {
        try {

            String httpMethod = httpServletRequest.getMethod();
            if (StringUtils.isNotBlank(httpMethod)) {
                MDC.put(MDCKeys.HTTP_METHOD, httpMethod);
            }

            String endPoint = httpServletRequest.getRequestURI();
            if (StringUtils.isNotBlank(endPoint)) {
                MDC.put(MDCKeys.ENDPOINT, endPoint);
            }

            String parameters = httpServletRequest.getQueryString();
            if (StringUtils.isNotBlank(parameters)) {
                MDC.put(MDCKeys.PARAMETERS, parameters);
            }

            String user = httpServletRequest.getRemoteUser();
            if (StringUtils.isNotBlank(user)) {
                MDC.put(MDCKeys.USER, user);
            }
            //The IP of the client or last proxy that sent the request
            String ipAddress = httpServletRequest.getRemoteAddr();
            if (StringUtils.isNotBlank(ipAddress)) {
                MDC.put(MDCKeys.IP_ADDRESS, ipAddress);
            }

            String request = requestPayloadConverter.convert(httpServletRequest);
            if (StringUtils.isNotBlank(request)) {
                MDC.put(MDCKeys.REQUEST, CryptoUtil.tryJasyptEncrypt(request));
            }

            //'Finally' block to guarantee whether an exception is thrown in the previous logic or not, the logger.catching and the MDC.clear() logic will still run.
        } finally {
            MDC.clear();
        }
    }
}

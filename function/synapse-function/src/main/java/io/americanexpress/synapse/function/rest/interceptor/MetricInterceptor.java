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

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * MetricInterceptor class captures metrics about Synapse such as logging API response times.<br>
 * <strong>Example:</strong> RESPONSE TIME: The request 0cfcb89d-0d50-4d4c-889d-083cb817b547 POST: /path/to/api with status 400 took 424 milliseconds.
 *
 * @author Alexei Morgado
 */
@Component
public class MetricInterceptor implements WebFilter {

    /**
     * Used to log the metrics.
     */
    private final XLogger logger = XLoggerFactory.getXLogger(getClass());

    /**
     * Capture the request information.
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  chosen handler to execute, for type and/or instance evaluation
     * @return true if the execution chain should proceed with the next interceptor or the handler itself;
     * else, DispatcherServlet assumes that this interceptor has already dealt with the response itself
     * @throws Exception in case of errors
     */
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        String requestId = UUID.randomUUID().toString();
//        logger.info("REQUEST ID: {}, HOST: {}, HTTP_METHOD: {}, URI : {}", requestId, request.getHeader("host"), request.getMethod(), request.getRequestURI());
//        long startTime = System.currentTimeMillis();
//        request.setAttribute("startTime", startTime);
//        request.setAttribute("requestId", requestId);
//        return true;
//    }

    /**
     * Capture the response information.
     *
     * @throws Exception in case of errors
     */
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
//        super.afterCompletion(request, response, handler, exception);
//        long startTime = (Long) request.getAttribute("startTime");
//        long endTime = System.nanoTime();
//        long executeTime = endTime - startTime;
//        int status = (response).getStatus();
//        logger.info("RESPONSE TIME: REQUEST_ID: {}, HTTP_METHOD: {}, URI: {}, STATUS: {}, TIME: {} nanoseconds.",
//                request.getAttribute("requestId"), request.getMethod(), request.getRequestURI(), status, executeTime);
//    }
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        long startTime = System.currentTimeMillis();
        return chain.filter(exchange).doFinally(signalType -> {
            long totalTime = System.currentTimeMillis() - startTime;
            exchange.getAttributes().put("totalTime", totalTime);
        });
    }
}

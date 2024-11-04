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
package io.americanexpress.synapse.api.rest.imperative.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.americanexpress.synapse.api.rest.imperative.interceptor.MetricInterceptor;
import io.americanexpress.synapse.framework.exception.config.ExceptionConfig;
import io.americanexpress.synapse.utilities.common.config.UtilitiesCommonConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * {@code ServiceRestConfig} class sets common configurations for the service layer.
 *
 * @author Francois Gutt
 */
@ComponentScan(basePackages = "io.americanexpress.synapse.api.rest.imperative")
@Configuration
@Import({ExceptionConfig.class, UtilitiesCommonConfig.class})
public class BaseApiImperativeRestConfig implements WebMvcConfigurer {

    /**
     * Default object mapper.
     */
    private final ObjectMapper defaultObjectMapper;

    /**
     * Default Metric Interceptor.
     */
    protected final MetricInterceptor interceptor;

    /**
     * Constructor taking in objectMapper & metricInterceptor.
     *
     * @param defaultObjectMapper   the default object mapper
     * @param interceptor           the metric interceptor
     */
    public BaseApiImperativeRestConfig(ObjectMapper defaultObjectMapper, MetricInterceptor interceptor) {
        this.defaultObjectMapper = defaultObjectMapper;
        this.interceptor = interceptor;
    }

    /**
     * Adds interceptor to the registry.
     *
     * @param registry interceptor registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).addPathPatterns("/**");
    }
}

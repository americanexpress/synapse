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
package io.americanexpress.api.sample.imperativebook.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.americanexpress.synapse.api.rest.imperative.config.BaseApiImperativeRestConfig;
import io.americanexpress.synapse.api.rest.imperative.interceptor.MetricInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * Configuration class for the Book API.
 * <p>
 * This class extends {@code BaseApiImperativeRestConfig} to provide custom configurations for the Book API,
 * including component scanning and interceptor registration.
 *
 * @author Aziz Ali
 */
@ComponentScan({
        "io.americanexpress.service.sample.imperativebook",
        "io.americanexpress.api.sample.imperativebook"
})
@Configuration
public class BookApiConfig extends BaseApiImperativeRestConfig {

    /**
     * Used to intercept requests for checking headers.
     */
    private final BookHttpHeadersInterceptor bookHttpHeadersInterceptor;


    /**
     * Constructor taking in objectMapper & metricInterceptor.
     *
     * @param defaultObjectMapper the default object mapper
     * @param interceptor         the metric interceptor
     * @param bookHttpHeadersInterceptor the
     */
    public BookApiConfig(ObjectMapper defaultObjectMapper,
                         MetricInterceptor interceptor,
                         BookHttpHeadersInterceptor bookHttpHeadersInterceptor) {
        super(defaultObjectMapper, interceptor);
        this.bookHttpHeadersInterceptor = bookHttpHeadersInterceptor;
    }

    /**
     * Takes any interceptor we provide and binds it to our service endpoint.
     *
     * @param registry the list of interceptors bound to our service
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.bookHttpHeadersInterceptor).order(1);
        super.addInterceptors(registry);
    }

}

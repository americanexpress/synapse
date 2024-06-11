package io.americanexpress.synapse.api.rest.imperative.exceptionhandler.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.americanexpress.service.sample.imperativebook.config.BookServiceConfig;
import io.americanexpress.synapse.api.rest.imperative.config.BaseApiImperativeRestConfig;
import io.americanexpress.synapse.api.rest.imperative.interceptor.MetricInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BookServiceConfig.class)
public class CreateBookExceptionConfig extends BaseApiImperativeRestConfig {
    /**
     * Constructor taking in objectMapper & metricInterceptor.
     *
     * @param defaultObjectMapper the default object mapper
     * @param interceptor         the metric interceptor
     */
    public CreateBookExceptionConfig(ObjectMapper defaultObjectMapper, MetricInterceptor interceptor) {
        super(defaultObjectMapper, interceptor);
    }
}

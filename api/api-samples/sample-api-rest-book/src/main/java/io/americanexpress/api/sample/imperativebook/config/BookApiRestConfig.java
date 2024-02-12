package io.americanexpress.api.sample.imperativebook.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.americanexpress.synapse.api.rest.imperative.config.BaseApiImperativeRestConfig;
import io.americanexpress.synapse.api.rest.imperative.interceptor.MetricInterceptor;

public class BookApiRestConfig extends BaseApiImperativeRestConfig {

    /**
     * Constructor taking in objectMapper & metricInterceptor.
     *
     * @param defaultObjectMapper the default object mapper
     * @param interceptor         the metric interceptor
     */
    public BookApiRestConfig(ObjectMapper defaultObjectMapper, MetricInterceptor interceptor) {
        super(defaultObjectMapper, interceptor);
    }
}

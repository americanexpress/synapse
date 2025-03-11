package io.americanexpress.synapse.api.rest.sample.imperativebook.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.americanexpress.service.sample.imperativebook.config.BookServiceConfig;
import io.americanexpress.synapse.api.rest.imperative.config.BaseApiImperativeRestConfig;
import io.americanexpress.synapse.api.rest.imperative.interceptor.MetricInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * {@code BookApiConfig} sets configuration for the Book API.
 *
 * @author Tanvir Islam
 */
@Configuration
@Import(BookServiceConfig.class)
@ComponentScan("io.americanexpress.synapse.api.rest.sample.imperativebook")
public class BookApiConfig extends BaseApiImperativeRestConfig {

    /**
     * Constructor taking in objectMapper & metricInterceptor.
     *
     * @param defaultObjectMapper the default object mapper
     * @param interceptor         the metric interceptor
     */
    public BookApiConfig(ObjectMapper defaultObjectMapper, MetricInterceptor interceptor) {
        super(defaultObjectMapper, interceptor);
    }
}

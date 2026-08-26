package io.americanexpress.sample.client.elasticsearch.product.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.americanexpress.synapse.client.elasticsearch.config.BaseElasticSearchClientConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * {@code ProductElasticSearchConfig} specifies the configuration for the ElasticSearch client.
 *
 * @author sshre31
 */
@ComponentScan("io.americanexpress.sample.client.elasticsearch.product")
@Configuration
public class ProductElasticSearchConfig extends BaseElasticSearchClientConfig {

    /**
     * Index name.
     */
    public static final String INDEX_NAME = "products";

    /**
     * Constructor taking in objectMapper & metricInterceptor.
     *
     * @param defaultObjectMapper the default object mapper
     */
    public ProductElasticSearchConfig(ObjectMapper defaultObjectMapper, Environment environment) {
        super(defaultObjectMapper, environment);
    }
}

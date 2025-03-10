package io.americanexpress.synapse.client.elasticsearch.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.americanexpress.synapse.framework.exception.config.ExceptionConfig;
import io.americanexpress.synapse.utilities.common.config.UtilitiesCommonConfig;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * {@code BaseElasticSearchClientConfig} specifies the base configuration for the ElasticSearch client.
 *
 * @author sshre31
 */
@Configuration
@ComponentScan(basePackages = "io.americanexpress.synapse.client.elasticsearch")
@Import({ExceptionConfig.class, UtilitiesCommonConfig.class})
public class BaseElasticSearchClientConfig {

    /**
     * Default object mapper.
     */
    private final ObjectMapper defaultObjectMapper;

    /**
     * The environment.
     */
    private final Environment environment;

    /**
     * Constructor taking in objectMapper & metricInterceptor.
     *
     * @param defaultObjectMapper   the default object mapper
     */
    public BaseElasticSearchClientConfig(ObjectMapper defaultObjectMapper, Environment environment) {
        this.defaultObjectMapper = defaultObjectMapper;
        this.environment = environment;
    }

    /**
     * Creates an instance of {@link ElasticsearchClient} to interact with the ElasticSearch cluster.
     *
     * @return the {@link ElasticsearchClient} instance.
     */
    @Bean
    public ElasticsearchClient elasticsearchClient() {
        var elasticSearchUrl = "https://localhost:9200";
        var elasticSearchApiKey = "ZWR4Y0xwTUJUQmZSMW1pVl9DMXA6ZVRCaWtWc21UQy02RTdRYklXbXFkdw==";

        var restClient = RestClient
                .builder(HttpHost.create(elasticSearchUrl))
                .setDefaultHeaders(new Header[]{
                        new BasicHeader(AUTHORIZATION, "ApiKey " + elasticSearchApiKey)
                })
                .build();

        var transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper(defaultObjectMapper));

        return new ElasticsearchClient(transport);
    }
}

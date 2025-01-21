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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * {@code BaseClientConfig}
 *
 * @author sshre31
 */
@Configuration
@ComponentScan(basePackages = "io.americanexpress.synapse.client.elasticsearch")
@Import({ExceptionConfig.class, UtilitiesCommonConfig.class})
public class BaseClientConfig {

    /**
     * Default object mapper.
     */
    private final ObjectMapper defaultObjectMapper;

    /**
     * Constructor taking in objectMapper & metricInterceptor.
     *
     * @param defaultObjectMapper   the default object mapper
     */
    public BaseClientConfig(ObjectMapper defaultObjectMapper) {
        this.defaultObjectMapper = defaultObjectMapper;
    }

    /**
     * Creates an instance of {@link ElasticsearchClient} to interact with the ElasticSearch cluster.
     *
     * @return the {@link ElasticsearchClient} instance.
     */
    @Bean
    public ElasticsearchClient elasticsearchClient(@Autowired Environment environment) {
        var elasticSearchUrl = environment.getRequiredProperty("elastic-client.url");
        var elasticSearchApiKey = environment.getRequiredProperty("elastic-client.apikey");

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

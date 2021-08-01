package io.americanexpress.synapse.client.rest.config;

import io.americanexpress.synapse.client.rest.client.BaseRestClient;
import io.americanexpress.synapse.client.rest.handler.BaseRestResponseErrorHandler;
import io.americanexpress.synapse.client.rest.interceptor.ClientLoggingCustomizer;
import io.americanexpress.synapse.framework.exception.config.ExceptionConfig;
import io.americanexpress.synapse.utilities.common.config.UtilitiesCommonConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * BaseRestClientConfig class is used to set the configurations for the base REST client.
 * Use @import in child config classes to inherit these base configurations.
 *
 * @author Paolo Claudio
 */
@Configuration
@ComponentScan(basePackages = "com.americanexpress.synapse.client.rest")
@Import({ExceptionConfig.class, UtilitiesCommonConfig.class})
public abstract class BaseRestClientConfig {

    /**
     * Logger used for this config.
     */
    protected final XLogger logger = XLoggerFactory.getXLogger(getClass());

    /**
     * Used to log the client request and response.
     */
    @Autowired
    private ClientLoggingCustomizer clientLoggingCustomizer;

    /**
     * Spring environment variable
     * due to the limitations of a backend service.
     */
    @Autowired
    protected Environment environment;

    /**
     * Default object mapper created from the UtilitiesCommonConfig.
     */
    @Autowired
    private ObjectMapper defaultObjectMapper;

    /**
     * Get the object mapper used for serialization for this client.
     *
     * @return the object mapper used for serialization for this client
     */
    protected ObjectMapper getObjectMapper() {
        return defaultObjectMapper;
    }

    /**
     * Generate the default REST template.
     *
     * @return the default REST template
     */
    public RestTemplate defaultRestTemplate() {
        RestTemplate template = new RestTemplateBuilder().customizers(clientLoggingCustomizer).build();
        List<HttpMessageConverter<?>> messagesConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(getObjectMapper());
        converter.setSupportedMediaTypes(Lists.newArrayList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML));
        messagesConverters.add(converter);
        template.setMessageConverters(messagesConverters);
        return template;
    }


    /**
     * Initialize the client, this method could be used directly for the clients that don't need headers factory.
     *
     * @param destinationUrl of the provider
     * @param client         used to connect to the provider
     * @param errorHandler   used to handler errors from the provider
     */
    @SuppressWarnings("rawtypes")
    protected void initializeClient(String destinationUrl, BaseRestClient client, BaseRestResponseErrorHandler errorHandler) {

        // Set the destination URL for the client
        client.setUrl(destinationUrl);

        // Set the rest template for the client
        RestTemplate restTemplate = defaultRestTemplate();
        restTemplate.setErrorHandler(errorHandler);
        client.setRestTemplate(restTemplate);
    }

    /**
     * Prototype to initialize the client.
     *
     * @param destinationUrl of the provider
     */
    protected abstract void initialize(String destinationUrl);
}

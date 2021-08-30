package io.americanexpress.synapse.client.rest.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.americanexpress.synapse.client.rest.interceptor.ClientLoggingCustomizer;
import io.americanexpress.synapse.framework.exception.config.ExceptionConfig;
import io.americanexpress.synapse.utilities.common.config.UtilitiesCommonConfig;

@Configuration
@ComponentScan(basePackages = "com.americanexpress.synapse.client.rest")
@Import({ExceptionConfig.class, UtilitiesCommonConfig.class})
abstract class BaseClientConfig {

	/**
     * Logger used for this config.
     */
    protected final XLogger logger = XLoggerFactory.getXLogger(getClass());

    /**
     * Used to log the client request and response.
     */
    @Autowired
    protected ClientLoggingCustomizer clientLoggingCustomizer;

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
    
    protected List<HttpMessageConverter<?>> getMessageConverters() {
    	List<HttpMessageConverter<?>> messagesConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(getObjectMapper());
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML));
        messagesConverters.add(converter);
        return messagesConverters;
    }
    
    /**
     * Prototype to initialize the client.
     *
     * @param destinationUrl of the provider
     */
    protected abstract void initialize(String destinationUrl);
}

package io.americanexpress.synapse.client.rest.config;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.databind.ObjectMapper;

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
     * Prototype to initialize the client.
     *
     * @param destinationUrl of the provider
     */
    protected abstract void initialize(String destinationUrl);
}

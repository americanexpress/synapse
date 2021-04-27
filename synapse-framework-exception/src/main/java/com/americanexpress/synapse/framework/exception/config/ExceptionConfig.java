package com.americanexpress.synapse.framework.exception.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * ExceptionConfig class sets the configurations for the framework exceptions.
 *
 * @author Gabriel Jimenez
 */
@Configuration
@ComponentScan(basePackages = "com.americanexpress.synapse.framework.exception")
public class ExceptionConfig {

    /**
     * Name of the location that will populate the values of the error message source.
     */
    private static final String ERROR_MESSAGES_FILE_NAME = "error-messages";

    /**
     * Create the message source for errors.
     *
     * @return the message source for errors
     */
    @Bean
    public MessageSource errorMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:" + ERROR_MESSAGES_FILE_NAME);
        // Reload the error messages every 10 seconds
        messageSource.setCacheSeconds(10);
        return messageSource;
    }
}

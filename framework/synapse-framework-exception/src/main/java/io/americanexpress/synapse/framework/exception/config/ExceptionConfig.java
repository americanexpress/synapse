/*
 * Copyright 2020 American Express Travel Related Services Company, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.americanexpress.synapse.framework.exception.config;

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
@ComponentScan(basePackages = "io.americanexpress.synapse.framework.exception")
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

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

/**
 * {@code BaseClientConfig} class specifies the prototypes for setting the configurations
 * for all clients.
 *
 * @author Paolo Claudio
 */
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

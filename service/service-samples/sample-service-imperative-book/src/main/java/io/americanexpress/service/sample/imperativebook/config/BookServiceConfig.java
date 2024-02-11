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
package io.americanexpress.service.sample.imperativebook.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.americanexpress.synapse.service.imperative.config.BaseImperativeServiceConfig;

/**
 * The type Book service config.
 *
 * @author Francois Gutt
 */
public class BookServiceConfig extends BaseImperativeServiceConfig {

    /**
     * Constructor taking in objectMapper & metricInterceptor.
     *
     * @param defaultObjectMapper the default object mapper
     */
    public BookServiceConfig(ObjectMapper defaultObjectMapper) {
        super(defaultObjectMapper);
    }
}

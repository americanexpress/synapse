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
package io.americanexpress.service.book.rest.config;

import io.americanexpress.synapse.service.rest.config.ServiceRestConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;


/**
 * BookConfig is the configuration class for the Book Application.
 */
@Configuration
@PropertySource("classpath:/service-book-application.properties")
@ComponentScan(basePackages = "io.americanexpress.service.book.rest")
@Import({ServiceRestConfig.class})
public class BookConfig {

    /**
     * The constant BOOK_ENDPOINT.
     */
    public static final String BOOK_ENDPOINT = "/v1/books";

}


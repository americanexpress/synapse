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

import io.americanexpress.data.mysql.book.config.BookDataConfig;
import io.americanexpress.synapse.service.rest.config.ServiceRestConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * {@code BookConfig} Book Configuration in enabling services querying an oracle database.
 */
@Configuration
@PropertySource("classpath:service-book-application.properties")
@ComponentScan(basePackages = "io.americanexpress.service.book.rest")
@Import({BookDataConfig.class, ServiceRestConfig.class})
public class BookConfig {

}

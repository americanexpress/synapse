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
package io.americanexpress.service.book.rest.integration;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

/**
 * Singleton PostgreSQL container shared across every IT in this module so Spring's
 * test-context cache can reuse one application context for all suites.
 */
final class BookPostgresContainer {

    private static final PostgreSQLContainer<?> INSTANCE =
            new PostgreSQLContainer<>(DockerImageName.parse("postgres:16-alpine"));

    static {
        INSTANCE.start();
    }

    private BookPostgresContainer() {}

    static void registerProperties(DynamicPropertyRegistry registry) {
        // The default `test` profile in data-book-application.properties wires up H2 and gates
        // the real Postgres config behind @Profile("!test"). ITs activate `integration-test`
        // so DataBookConfig loads, then we point the datasource at the container here.
        registry.add("spring.datasource.jdbcUrl", INSTANCE::getJdbcUrl);
        registry.add("spring.datasource.url", INSTANCE::getJdbcUrl);
        registry.add("spring.datasource.username", INSTANCE::getUsername);
        registry.add("spring.datasource.password", INSTANCE::getPassword);
        registry.add("spring.datasource.driver-class-name", INSTANCE::getDriverClassName);
        registry.add("spring.jpa.properties.hibernate.default_schema", () -> "public");
        registry.add("hibernate.dialect", () -> "org.hibernate.dialect.PostgreSQLDialect");
        registry.add("hibernate.hbm2ddl.auto", () -> "create-drop");
        registry.add("hibernate.show_sql", () -> "false");
        registry.add("hibernate.format_sql", () -> "false");
    }
}

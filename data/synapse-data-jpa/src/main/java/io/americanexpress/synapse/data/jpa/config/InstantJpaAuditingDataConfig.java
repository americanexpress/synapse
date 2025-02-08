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
package io.americanexpress.synapse.data.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.Instant;
import java.util.Optional;

/**
 * {@code InstantJpaAuditingDataConfig} configuration class that includes the JPA Auditing to be saved into the database.
 *
 * @author John Robert Martinez Ponce
 */
@Configuration
@EnableJpaAuditing(dateTimeProviderRef = "instantDateTimeProvider")
public class InstantJpaAuditingDataConfig {

    /**
     * Provides to the Jpa Auditing with the {@link Instant} DateTime, that includes Zone information.
     *
     * @return object of {@link DateTimeProvider}
     */
    @Bean
    public DateTimeProvider instantDateTimeProvider() {
        return () -> Optional.of(Instant.now());
    }
}

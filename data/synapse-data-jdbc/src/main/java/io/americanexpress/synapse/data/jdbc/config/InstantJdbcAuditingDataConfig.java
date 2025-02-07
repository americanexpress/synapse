package io.americanexpress.synapse.data.jdbc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

import java.time.Instant;
import java.util.Optional;

/**
 * {@code InstantJpaAuditingDataConfig} configuration class that includes the JPA Auditing to be saved into the database.
 *
 * @author John Robert Martinez Ponce
 */
@Configuration
@EnableJdbcAuditing(dateTimeProviderRef = "instantDateTimeProvider")
public class InstantJdbcAuditingDataConfig {

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

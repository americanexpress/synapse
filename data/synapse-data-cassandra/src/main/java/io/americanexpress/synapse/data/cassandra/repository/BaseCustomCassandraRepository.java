package io.americanexpress.synapse.data.cassandra.repository;

import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.InsertOptions;
import java.time.Duration;

/**
 * {@code BaseCustomCassandraRepository} is the custom repository for Cassandra.
 * This Repository is used to add ttl to your entities records.
 * The ttl is taken from the bean created in the config which get the ttl value from the property file.
 *
 * @author breisalm
 */
public abstract class BaseCustomCassandraRepository<T> {

    /**
     * The Cassandra template.
     */
    private final CassandraTemplate cassandraTemplate;

    /**
     * The time to live.
     */
    private final int timeToLive;

    /**
     * Creates a new instance of {@code BaseCustomCassandraRepository} given a Cassandra template and time to live.
     *
     * @param cassandraTemplate the Cassandra template.
     * @param timeToLive the time to live.
     */
    protected BaseCustomCassandraRepository(CassandraTemplate cassandraTemplate,
                                            int timeToLive) {
        this.cassandraTemplate = cassandraTemplate;
        this.timeToLive = timeToLive;
    }

    /**
     * Saves the entity.
     *
     * @param entity the entity.
     * @return the saved entity.
     */
    public T save(T entity) {
        return cassandraTemplate.insert(entity,
                        InsertOptions.builder()
                                .ttl(Duration.ofSeconds(timeToLive))
                                .build())
                .getEntity();
    }
}

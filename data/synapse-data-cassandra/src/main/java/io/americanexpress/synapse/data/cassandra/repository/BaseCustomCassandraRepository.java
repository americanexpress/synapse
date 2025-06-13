package io.americanexpress.synapse.data.cassandra.repository;

import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.InsertOptions;

/**
 * {@code BaseCustomCassandraRepository} is the custom repository for Cassandra.
 * This Repository is used to add insert options for the entity.
 *
 * @author breisalm
 */
public abstract class BaseCustomCassandraRepository<T> {

    /**
     * The Cassandra template.
     */
    private final CassandraTemplate cassandraTemplate;

    /**
     * Creates a new instance of {@code BaseCustomCassandraRepository} given a Cassandra template and time to live.
     *
     * @param cassandraTemplate the Cassandra template.
     */
    protected BaseCustomCassandraRepository(CassandraTemplate cassandraTemplate) {
        this.cassandraTemplate = cassandraTemplate;
    }

    /**
     * Saves the entity.
     *
     * @param entity the entity.
     * @return the saved entity.
     */
    public T save(T entity) {
        return cassandraTemplate.insert(entity, getInsertOptions()).getEntity();
    }

    /**
     * Gets the insert options for the entity.
     */
    public abstract InsertOptions getInsertOptions();
}

package io.americanexpress.synapse.data.couchbase.repository;

import io.americanexpress.synapse.data.couchbase.entity.BaseCouchbaseEntity;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

/**
 * @author dliburd
 */
@NoRepositoryBean
public interface BaseReactiveCouchbaseRepository<E extends BaseCouchbaseEntity, I> extends ReactiveSortingRepository<E, I> {
}

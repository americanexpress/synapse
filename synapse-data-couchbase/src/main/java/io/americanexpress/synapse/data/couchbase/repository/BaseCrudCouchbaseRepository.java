package io.americanexpress.synapse.data.couchbase.repository;

import io.americanexpress.synapse.data.couchbase.entity.BaseCouchbaseEntity;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Darien Liburd
 */
@NoRepositoryBean
public interface BaseCrudCouchbaseRepository<T extends BaseCouchbaseEntity, I> extends PagingAndSortingRepository<T, I> {
}

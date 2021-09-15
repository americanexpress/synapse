package io.americanexpress.synapse.data.couchbase.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.env.Environment;
import org.springframework.data.couchbase.repository.config.EnableReactiveCouchbaseRepositories;

/**
 * The type Base couchbase data config.
 *
 * @author Darien Liburd
 */
@EnableReactiveCouchbaseRepositories
public abstract class BaseReactiveCouchbaseDataConfig extends io.americanexpress.synapse.data.couchbase.config.BaseCouchbaseDataConfig {

	protected BaseReactiveCouchbaseDataConfig(final Environment environment, final ObjectMapper objectMapper) {
		super(environment, objectMapper);
	}

}

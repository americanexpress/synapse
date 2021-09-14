package io.americanexpress.synapse.data.couchbase.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.env.Environment;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

/**
 * The type Base couchbase data config.
 *
 * @author Darien Liburd
 */
@EnableCouchbaseRepositories
public abstract class BaseCrudCouchbaseDataConfig extends io.americanexpress.synapse.data.couchbase.config.BaseCouchbaseDataConfig {

	protected BaseCrudCouchbaseDataConfig(final Environment environment, final ObjectMapper objectMapper) {
		super(environment, objectMapper);
	}

}

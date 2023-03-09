package io.americanexpress.book.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.americanexpress.synapse.data.couchbase.config.BaseReactiveCouchbaseDataConfig;
import org.springframework.core.env.Environment;

public class BookDataConfig extends BaseReactiveCouchbaseDataConfig {

    protected BookDataConfig(Environment environment, ObjectMapper objectMapper) {
        super(environment, objectMapper);
    }
}

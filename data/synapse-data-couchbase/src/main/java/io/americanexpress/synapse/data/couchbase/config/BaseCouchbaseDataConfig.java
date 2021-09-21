package io.americanexpress.synapse.data.couchbase.config;

import com.couchbase.client.java.codec.JacksonJsonSerializer;
import com.couchbase.client.java.env.ClusterEnvironment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.env.Environment;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;

import java.time.Duration;

/**
 * The type Base couchbase data config.
 *
 * @author Darien Liburd
 */
public abstract class BaseCouchbaseDataConfig extends AbstractCouchbaseConfiguration {

    private final Environment environment;

    private final ObjectMapper objectMapper;

    protected BaseCouchbaseDataConfig(Environment environment, ObjectMapper objectMapper) {
        this.environment = environment;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void configureEnvironment(ClusterEnvironment.Builder builder) {
        builder.timeoutConfig()
                .connectTimeout(Duration.ofSeconds(25))
                .queryTimeout(Duration.ofSeconds(10))
                .kvTimeout(Duration.ofSeconds(3))
                .build();

        builder.jsonSerializer(JacksonJsonSerializer.create(objectMapper)).build();

        builder.loggerConfig().enableDiagnosticContext(true).build();
    }

    @Override
    public String getConnectionString() {
        return environment.getRequiredProperty("spring.couchbase.connection-string");
    }

    @Override
    public String getUserName() {
        return environment.getRequiredProperty("spring.couchbase.username");
    }

    @Override
    public String getPassword() {
        return environment.getRequiredProperty("spring.couchbase.password");
    }

    @Override
    public String getBucketName() {
        return environment.getRequiredProperty("spring.couchbase.bucket-name");
    }

    @Override
    public String typeKey() {
        return "_dataType";
    }

}

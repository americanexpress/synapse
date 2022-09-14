package io.americanexpress.synapse.data.mongodb.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;

@Configuration
@EnableReactiveMongoAuditing
public abstract class BaseReactiveMongoDBDataConfig extends AbstractReactiveMongoConfiguration {

    /**
     * Used to acquire environment variables.
     */
    protected Environment environment;

    protected BaseReactiveMongoDBDataConfig(Environment environment) {
        this.environment = environment;
    }

    @Override
    protected String getDatabaseName() {
        return environment.getRequiredProperty("spring.data.mongodb.database");
    }

    @Override
    public MongoClient reactiveMongoClient() {
        ConnectionString connectionString = new ConnectionString(environment.getRequiredProperty("spring.data.mongodb.uri"));
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return this.createReactiveMongoClient(mongoClientSettings);
    }
}

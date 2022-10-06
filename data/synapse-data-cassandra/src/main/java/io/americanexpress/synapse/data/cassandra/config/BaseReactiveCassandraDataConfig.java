package io.americanexpress.synapse.data.cassandra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.AbstractReactiveCassandraConfiguration;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
import org.springframework.data.cassandra.config.EnableReactiveCassandraAuditing;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;

@Configuration
@EnableReactiveCassandraAuditing
@EnableReactiveCassandraRepositories
public class BaseReactiveCassandraDataConfig extends AbstractReactiveCassandraConfiguration {

    private final Environment environment;

    public BaseReactiveCassandraDataConfig(Environment environment) {
        this.environment = environment;
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.valueOf(environment.getRequiredProperty("spring.data.cassandra.schema-action"));
    }

    @Override
    protected String getKeyspaceName() {
        return environment.getRequiredProperty("spring.data.cassandra.keyspace-name");
    }

    @Override
    protected String getContactPoints() {
        return environment.getRequiredProperty("spring.data.cassandra.contact-points");
    }

    @Override
    protected int getPort() {
        return Integer.parseInt(environment.getRequiredProperty("spring.data.cassandra.port"));
    }

    @Override
    protected String getLocalDataCenter() {
        return environment.getRequiredProperty("spring.data.cassandra.local-datacenter");
    }

    protected String getUserName() {
        return environment.getProperty("spring.data.cassandra.username");
    }

    protected String getPassword() {
        return environment.getProperty("spring.data.cassandra.password");
    }

    @Override
    public CqlSessionFactoryBean cassandraSession() {
        CqlSessionFactoryBean bean = super.cassandraSession();
        bean.setUsername(getUserName());
        bean.setPassword(getPassword());
        return bean;
    }
}

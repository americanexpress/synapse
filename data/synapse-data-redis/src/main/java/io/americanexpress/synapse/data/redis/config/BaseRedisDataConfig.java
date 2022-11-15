package io.americanexpress.synapse.data.redis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class BaseRedisDataConfig {

    private final Environment environment;

    public BaseRedisDataConfig(Environment environment) {
        this.environment = environment;
    }


}

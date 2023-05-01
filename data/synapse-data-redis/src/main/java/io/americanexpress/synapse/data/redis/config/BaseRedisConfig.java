package io.americanexpress.synapse.data.redis.config;

import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;

/**
 * The type Base redis config.
 */
public abstract class BaseRedisConfig {

    private final Environment environment;

    /**
     * Instantiates a new Base redis config.
     *
     * @param environment the environment
     */
    protected BaseRedisConfig(Environment environment) {
        this.environment = environment;
    }

    /**
     * Redis standalone configuration redis standalone configuration.
     *
     * @return the redis standalone configuration
     */
    protected RedisStandaloneConfiguration redisStandaloneConfiguration() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(getRedisHost());
        redisStandaloneConfiguration.setPort(getRedisPort());
        redisStandaloneConfiguration.setDatabase(getRedisDatabase());
        redisStandaloneConfiguration.setUsername(getRedisUsername());
        redisStandaloneConfiguration.setPassword(RedisPassword.of(getRedisPassword()));
        return redisStandaloneConfiguration;
    }

    /**
     * Gets redis host.
     *
     * @return the redis host
     */
    protected String getRedisHost() {
        return environment.getRequiredProperty("spring.data.redis.host");
    }

    /**
     * Gets redis port.
     *
     * @return the redis port
     */
    protected Integer getRedisPort() {
        return Integer.valueOf(environment.getRequiredProperty("spring.data.redis.port"));
    }

    /**
     * Gets redis database.
     *
     * @return the redis database
     */
    protected Integer getRedisDatabase() {
        return Integer.valueOf(environment.getRequiredProperty("spring.data.redis.database"));
    }

    /**
     * Gets redis username.
     *
     * @return the redis username
     */
    protected String getRedisUsername() {
        return environment.getProperty("spring.data.redis.username");
    }

    /**
     * Gets redis password.
     *
     * @return the redis password
     */
    protected String getRedisPassword() {
        return environment.getProperty("spring.data.redis.password");
    }
}

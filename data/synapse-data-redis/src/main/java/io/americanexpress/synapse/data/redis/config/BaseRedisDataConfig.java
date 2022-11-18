package io.americanexpress.synapse.data.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * {@code BaseRedisDataConfig} class is used to hold the common configuration for all data-redis modules.
 * It provides easy configuration and access to Redis from Spring applications.
 * It offers both low-level and high-level abstractions for interacting with the store,
 * freeing the user from infrastructural concerns.
 */
@Configuration
@EnableRedisRepositories
public class BaseRedisDataConfig {

    /**
     * {@link Environment} interface representing the environment in which the current application is running.
     * Models two key aspects of the application environment (profiles and properties).
     * A profile is a named, logical group of bean definitions to be registered with the container
     * only if the given profile is active. Beans may be assigned to a profile whether defined in XML or via
     * annotations; The role of the {@link Environment} object with relation to profiles is in determining which
     * profiles (if any) are currently active, and which profiles (if any) should be active by default.
     */
    private final Environment environment;

    /**
     * The overloaded constructor for the base redis data config that initialized the {@link Environment}.
     * @param environment the environment
     */
    public BaseRedisDataConfig(Environment environment) {
        this.environment = environment;
    }

    /**
     * {@link JedisConnectionFactory} is configured using an environmental configuration and the client configuration.
     * Jedis supports the following environmental configurations:
     * <li>{@link RedisStandaloneConfiguration}</li>
     * <li>{@link RedisSentinelConfiguration}</li>
     * <li>{@link RedisClusterConfiguration}</li>
     *
     * @return a Jedis Connection factory
     */
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setPassword(RedisPassword.of(environment.getRequiredProperty("spring.redis.password")));
        redisStandaloneConfiguration.setDatabase(Integer.parseInt(environment.getRequiredProperty("spring.redis.database")));
        redisStandaloneConfiguration.setPort(Integer.parseInt(environment.getRequiredProperty("spring.redis.port")));
        redisStandaloneConfiguration.setPassword(environment.getRequiredProperty("spring.redis.password"));

        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    /**
     * Performs automatic serialization/deserialization between the given objects and the underlying binary data in
     * the Redis store. By default, it uses Java serialization for its objects
     * (through {@link}JdkSerializationRedisSerializer} ). For String intensive operations consider
     * the dedicated StringRedisTemplate.
     *
     * @return a redis template
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
}

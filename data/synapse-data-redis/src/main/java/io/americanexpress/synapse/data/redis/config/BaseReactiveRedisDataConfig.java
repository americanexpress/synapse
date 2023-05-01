package io.americanexpress.synapse.data.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * {@code BaseReactiveRedisDataConfig}
 */
@Configuration
public class BaseReactiveRedisDataConfig extends BaseRedisConfig {

    /**
     * Instantiates a new Base reactive redis data config.
     *
     * @param environment the environment
     */
    public BaseReactiveRedisDataConfig(Environment environment) {
        super(environment);
    }

    /**
     * Lettuce connection factory reactive redis connection factory.
     *
     * @return the reactive redis connection factory
     */
    @Bean
    public ReactiveRedisConnectionFactory lettuceConnectionFactory() {
        return new LettuceConnectionFactory(redisStandaloneConfiguration(), lettuceClientConfiguration());
    }

    /**
     * Lettuce client configuration lettuce client configuration.
     *
     * @return the lettuce client configuration
     */
    @Bean
    public LettuceClientConfiguration lettuceClientConfiguration() {
        return LettuceClientConfiguration.builder()
                .build();
    }

}

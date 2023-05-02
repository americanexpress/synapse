/*
 * Copyright 2020 American Express Travel Related Services Company, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.americanexpress.synapse.data.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.RedisSerializationContext;

/**
 * {@code BaseReactiveRedisDataConfig} class is used to hold the common configuration for all reactive data-redis modules.
 */
@Configuration
@EnableRedisRepositories
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
     * Creates a LettuceConnectionFactory bean for reactive redis connection.
     *
     * @return the lettuce connection factory.
     */
    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        return new LettuceConnectionFactory(redisStandaloneConfiguration(), lettuceClientConfiguration());
    }

    /**
     * Creates a LettuceClientConfiguration for configuring LettuceConnection.
     * This method can be overridden by subclasses to fit redis connection configuration needs.
     *
     * @return the lettuce client configuration
     */
    @Bean
    public LettuceClientConfiguration lettuceClientConfiguration() {
        return LettuceClientConfiguration.builder()
                .build();
    }

    /**
     * Performs automatic serialization/deserialization between the given objects and the underlying binary data in the Redis store
     * This method can be overridden by subclasses to fit redis access needs.
     *
     * @return the ReactiveRedisTemplate
     */
    @Bean
    public ReactiveRedisTemplate reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {
        return new ReactiveRedisTemplate<>(factory, getSerializationContext());
    }

    /**
     * This method returns the RedisSerializationContext which is used to tell ReactiveRedisTemplate
     * what the retrieved object from Redis should be deserialized/serialized to.
     * This method can be overridden by subclasses to fit their redis serialization needs.
     *
     * @return RedisSerializationContext
     */
    protected RedisSerializationContext getSerializationContext() {
        return RedisSerializationContext.string();
    }

}

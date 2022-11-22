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
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

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
        JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
        jedisConFactory.setHostName("localhost");
        jedisConFactory.setPort(6379);
        jedisConFactory.afterPropertiesSet();
        return jedisConFactory;
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
        template.setEnableTransactionSupport(true);

        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new JdkSerializationRedisSerializer());
        template.setValueSerializer(new JdkSerializationRedisSerializer());
        template.setEnableTransactionSupport(true);
        template.afterPropertiesSet();
        return template;
    }
}

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
import org.springframework.data.redis.serializer.RedisSerializationContext;

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

//    /**
//     * Lettuce connection factory reactive redis connection factory.
//     *
//     * @return the reactive redis connection factory
//     */
//    @Bean
//    public ReactiveRedisConnectionFactory lettuceConnectionFactory() {
//        return new LettuceConnectionFactory(redisStandaloneConfiguration(), lettuceClientConfiguration());
//    }

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

    @Bean
    public ReactiveRedisTemplate reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {
        return new ReactiveRedisTemplate<>(factory, getSerializationContext());
    }

    protected RedisSerializationContext getSerializationContext() {
        return RedisSerializationContext.string();
    }

}

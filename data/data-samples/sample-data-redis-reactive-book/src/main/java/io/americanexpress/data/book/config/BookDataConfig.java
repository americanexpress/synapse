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
package io.americanexpress.data.book.config;

import io.americanexpress.data.book.entity.BookEntity;
import io.americanexpress.synapse.data.redis.config.BaseReactiveRedisDataConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * {@code BookDataConfig} is the configuration class to load all the properties for the book data module.
 */
@Configuration
@PropertySource("classpath:data-book-application.properties")
@ComponentScan(basePackages = BookDataConfig.PACKAGE_NAME)
@EnableRedisRepositories(basePackages = BookDataConfig.PACKAGE_NAME)
public class BookDataConfig extends BaseReactiveRedisDataConfig {

    /**
     * The Package name.
     */
    static final String PACKAGE_NAME = "io.americanexpress.data.book";

    /**
     * The {@link BookDataConfig} overloaded constructor.
     * @param environment the environment
     */
    public BookDataConfig(Environment environment) {
        super(environment);
    }

    /**
     * Overriding method to configure redis template for serialization/deserialization for key, value mapping of String and {@link BookEntity} type.
     *
     * @param factory the redis connection factory
     * @return the reactive redis template specifically for <Stirng, BookEntity>
     */
    @Override
    public ReactiveRedisTemplate<String, BookEntity> reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {
        return new ReactiveRedisTemplate<>(factory, getSerializationContext());
    }

    /**
     * Overriding method to provide configuration for serialization/deserialization of {@link BookEntity}.
     *
     * @return the redis serialization context
     */
    @Override
    protected RedisSerializationContext<String, BookEntity> getSerializationContext() {
        return  RedisSerializationContext
                .<String, BookEntity>newSerializationContext(new StringRedisSerializer())
                .key(new StringRedisSerializer())
                .value(new GenericToStringSerializer<>(BookEntity.class))
                .hashKey(new StringRedisSerializer())
                .hashValue(new GenericJackson2JsonRedisSerializer())
                .build();
    }
}
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

import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;

/**
 * {@code BaseRedisConfig} contains common configuration for redis configuration such as methods to retrieve port, host, etc.
 */
public abstract class BaseRedisConfig {

    /**
     * Used to retrieve properties from property files.
     */
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
     * Creates a RedisStandaloneConfiguration, this method can be overridden for further redis configurations.
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

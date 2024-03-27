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
package io.americanexpress.synapse.subscriber.kafka.config;

import io.americanexpress.synapse.framework.exception.ApplicationServerException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * {@code BaseKafkaPropertiesConfiguration} class is used host all the kafka properties.
 *
 * @param <C> class type of BaseKafkaConsumer
 * @param <S> class type of BaseKafkaSsl
 * @author Krishna Kuchikulla
 */
public abstract class BaseKafkaPropertiesConfiguration<C extends BaseKafkaPropertiesConfiguration.BaseKafkaConsumer,
        S extends BaseKafkaPropertiesConfiguration.BaseKafkaSsl> extends KafkaProperties {

    /**
     * BaseKafkaConsumerConfiguration.
     */
    private final C consumer;

    /**
     * BaseKafkaSslConfiguration.
     */
    private final S ssl;

    /**
     * Environment.
     */
    private final Environment environment;

    /**
     * BaseKafkaPropertiesConfiguration constructor.
     *
     * @param consumer    consumer
     * @param ssl         ssl
     * @param environment environment
     */
    protected BaseKafkaPropertiesConfiguration(C consumer, S ssl, Environment environment) {
        this.consumer = consumer;
        this.ssl = ssl;
        this.environment = environment;
    }

    /**
     * This method is used to get bootstrapServers.
     */
    @Override
    public List<String> getBootstrapServers() {
        return Collections.singletonList(environment.getRequiredProperty("kafka.bootstrap.servers"));
    }

    /**
     * This method is used to get kafka Ssl.
     */
    @Override
    public Ssl getSsl() {
        return this.ssl;
    }

    /**
     * This method is used to get kafka consumer.
     */
    @Override
    public Consumer getConsumer() {
        return this.consumer;
    }

    /**
     * This method is used to build a HashMap of consumer properties.
     */
    @Override
    public Map<String, Object> buildConsumerProperties() {
        return this.getConsumer().buildProperties();
    }


    /**
     * {@code BaseKafkaConsumer} class contains the properties related to a consumer configuration.
     */
    public abstract static class BaseKafkaConsumer extends Consumer {

        /**
         * Environment.
         */
        private final Environment environment;

        /**
         * BaseKafkaConsumer constructor.
         *
         * @param environment environment
         */
        protected BaseKafkaConsumer(Environment environment) {
            this.environment = environment;
        }

        /**
         * Bootstrap Server.
         */
        @Override
        public List<String> getBootstrapServers() {
            return Collections.singletonList(environment.getRequiredProperty("kafka.bootstrap.servers"));
        }

        /**
         * Auto-commit.
         */
        @Override
        public Boolean getEnableAutoCommit() {
            return false;
        }

        /**
         * This method is used to get de-serializer used for key of the message.
         */
        @Override
        public Class<?> getKeyDeserializer() {
            return StringDeserializer.class;
        }

        /**
         * This method is used to get de-serializer used for value of the message.
         */
        @Override
        public Class<?> getValueDeserializer() {
            return StringDeserializer.class;
        }

        /**
         * This method is used to get maximum number of records to be polled.
         */
        @Override
        public Integer getMaxPollRecords() {
            return Optional.ofNullable(environment.getProperty("kafka.subscriber.maximum.poll.records", Integer.class)).orElse(1);
        }
    }

    /**
     * {@code BaseKafkaSsl} class contains the properties related to a ssl configuration.
     */
    public abstract static class BaseKafkaSsl extends Ssl {

        /**
         * Environment.
         */
        private final Environment environment;

        /**
         * ResourceLoader.
         */
        private final ResourceLoader resourceLoader;

        /**
         * BaseKafkaSsl constructor.
         *
         * @param environment environment
         * @param resourceLoader resourceLoader
         */
        protected BaseKafkaSsl(Environment environment, ResourceLoader resourceLoader) {
            this.environment = environment;
            this.resourceLoader = resourceLoader;
        }

        /**
         * This method is used to get key password.
         */
        @Override
        public String getKeyPassword() {
            return environment.getRequiredProperty("kafka.key.password");
        }

        /**
         * This method is used to get keyStore password.
         */
        @Override
        public String getKeyStorePassword() {
            return environment.getRequiredProperty("kafka.keyStore.password");
        }

        /**
         * This method is used to get keyStore type.
         */
        @Override
        public String getKeyStoreType() {
            return environment.getRequiredProperty("kafka.keyStore.type");
        }

        /**
         * This method is used to get keyStore location.
         */
        @Override
        public Resource getKeyStoreLocation() {
            Resource resource = resourceLoader.getResource(environment.getRequiredProperty("kafka.keyStore.location"));
            if (!resource.exists()) {
                throw new ApplicationServerException(new FileNotFoundException(resource.getDescription() + " not found"));
            }
            return resource;
        }

        /**
         * This method is used to get trustStore location.
         */
        @Override
        public Resource getTrustStoreLocation() {
            Resource resource = resourceLoader.getResource(environment.getRequiredProperty("kafka.trustStore.location"));
            if (!resource.exists()) {
                throw new ApplicationServerException(new FileNotFoundException(resource.getDescription() + " not found"));
            }
            return resource;
        }

        /**
         * This method is used to get trustStore password.
         */
        @Override
        public String getTrustStorePassword() {
            return environment.getRequiredProperty("kafka.trustStore.password");
        }

        /**
         * This method is used to get trustStore type.
         */
        @Override
        public String getTrustStoreType() {
            return environment.getRequiredProperty("kafka.trustStore.type");
        }

        /**
         * This method is used to get ssl protocol.
         */
        @Override
        public String getProtocol() {
            return environment.getRequiredProperty("protocol");
        }
    }
}

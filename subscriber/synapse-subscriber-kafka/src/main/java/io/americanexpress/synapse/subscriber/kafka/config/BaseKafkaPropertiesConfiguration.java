package io.americanexpress.synapse.subscriber.kafka.config;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
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
 *
 * @author Krishna Kuchikulla
 */
@RequiredArgsConstructor
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
    @RequiredArgsConstructor
    public abstract static class BaseKafkaConsumer extends Consumer {

        /**
         * Environment.
         */
        private final Environment environment;

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
    @RequiredArgsConstructor
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
         * This method is used to get key password.
         */
        @Override
        public String getKeyPassword() {
            return environment.getRequiredProperty("kafka.key.identification");
        }

        /**
         * This method is used to get keyStore password.
         */
        @Override
        public String getKeyStorePassword() {
            return environment.getRequiredProperty("kafka.keyStore.identification");
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
         *
         * @throws FileNotFoundException if the keyStore is not found in a given location
         */
        @SneakyThrows
        @Override
        public Resource getKeyStoreLocation() {
            Resource resource = resourceLoader.getResource(environment.getRequiredProperty("kafka.keyStore.location"));
            if (!resource.exists()) {
                throw new FileNotFoundException(resource.getDescription() + " not found");
            }
            return resource;
        }

        /**
         * This method is used to get trustStore location.
         *
         * @throws FileNotFoundException if the trustStore is not found in a given location
         */
        @SneakyThrows
        @Override
        public Resource getTrustStoreLocation() {
            Resource resource = resourceLoader.getResource(environment.getRequiredProperty("kafka.trustStore.location"));
            if (!resource.exists()) {
                throw new FileNotFoundException(resource.getDescription() + " not found");
            }
            return resource;
        }

        /**
         * This method is used to get trustStore password.
         */
        @Override
        public String getTrustStorePassword() {
            return environment.getRequiredProperty("kafka.trustStore.identification");
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

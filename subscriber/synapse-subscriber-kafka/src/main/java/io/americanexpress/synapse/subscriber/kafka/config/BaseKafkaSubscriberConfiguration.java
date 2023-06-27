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

import io.americanexpress.synapse.subscriber.kafka.errorhandler.BaseKafkaSubscriberErrorHandler;
import io.americanexpress.synapse.subscriber.kafka.filter.BaseKafkaSubscriberMessageFilter;
import io.americanexpress.synapse.subscriber.kafka.interceptor.BaseKafkaSubscriberMetricInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.Optional;


/**
 * {@code BaseKafkaSubscriberConfiguration} class is used to host the configurations needed for creating a kafka subscriber.
 *
 * @param <C> class type of BaseKafkaPropertiesConfiguration
 * @param <E> class type of BaseKafkaSubscriberErrorHandler
 * @param <F> class type of BaseKafkaSubscriberMessageFilter
 * @param <I> class type of BaseKafkaSubscriberMetricInterceptor
 *
 * @author Krishna Kuchikulla
 */
@EnableKafka
public abstract class BaseKafkaSubscriberConfiguration<C extends BaseKafkaPropertiesConfiguration,
                                                    E extends BaseKafkaSubscriberErrorHandler,
                                                    F extends BaseKafkaSubscriberMessageFilter,
                                                    I extends BaseKafkaSubscriberMetricInterceptor> {

    /**
     * Partition count key
     */
    private static final String partitionCountKey = "kafka.partitions.count";

    /**
     * Record filter enabled key
     */
    private static final String recordFilterEnabledKey = "kafka.subscriber.filter.enabled";

    /**
     * Batch subscriber enabled key
     */
    private static final String batchSubscriberEnabledKey = "kafka.subscriber.batch.enabled";

    /**
     * KafkaPropertiesConfig.
     */
    private final C kafkaPropertiesConfiguration;

    /**
     * RecordInterceptor.
     */
    private final I recordInterceptor;

    /**
     * KafkaSubscriberErrorHandler.
     */
    private final E kafkaErrorHandler;

    /**
     * RecordFilteringStrategy.
     */
    private F recordFilteringStrategy;

    /**
     * Number of partitions.
     */
    private final Integer partitions;

    /**
     * Kafka subscriber record filter enabled Flag.
     */
    private final boolean recordFilteringEnabled;

    /**
     * Kafka batch subscriber enabled Flag.
     */
    private final boolean batchSubscriberEnabled;

    /**
     * This method is used to get record filtering enabled flag.
     */
    public boolean isRecordFilteringEnabled() {
        return this.recordFilteringEnabled;
    }

    /**
     * This method is used to get batch subscriber enabled flag.
     */
    public boolean isBatchSubscriberEnabled() {
        return this.batchSubscriberEnabled;
    }

    /**
     * BaseKafkaSubscriberConfiguration constructor.
     *
     * @param kafkaPropertiesConfiguration kafkaPropertiesConfiguration
     * @param kafkaErrorHandler kafkaErrorHandler
     * @param recordFilteringStrategy recordFilteringStrategy
     * @param environment environment
     * @param recordInterceptor recordInterceptor
     */
    protected BaseKafkaSubscriberConfiguration(C kafkaPropertiesConfiguration, E kafkaErrorHandler, F recordFilteringStrategy, Environment environment, I recordInterceptor) {
        this(kafkaPropertiesConfiguration, kafkaErrorHandler, environment, recordInterceptor);
        this.recordFilteringStrategy = recordFilteringStrategy;
    }

    /**
     * BaseKafkaSubscriberConfiguration constructor.
     *
     * @param kafkaPropertiesConfiguration kafkaPropertiesConfiguration
     * @param kafkaErrorHandler kafkaErrorHandler
     * @param environment environment
     * @param recordInterceptor recordInterceptor
     */
    protected BaseKafkaSubscriberConfiguration(C kafkaPropertiesConfiguration, E kafkaErrorHandler, Environment environment, I recordInterceptor) {
        this.kafkaPropertiesConfiguration = kafkaPropertiesConfiguration;
        this.kafkaErrorHandler = kafkaErrorHandler;
        this.recordInterceptor = recordInterceptor;
        this.partitions = Optional.ofNullable(environment.getProperty(partitionCountKey, Integer.class)).orElse(1);
        this.batchSubscriberEnabled = Optional.ofNullable(environment.getProperty(batchSubscriberEnabledKey, Boolean.class)).orElse(Boolean.FALSE);
        this.recordFilteringEnabled = Optional.ofNullable(environment.getProperty(recordFilterEnabledKey, Boolean.class)).orElse(Boolean.FALSE);
        this.recordFilteringStrategy = null;
    }

    /**
     * This method returns the consumerFactory created using kafkaPropertiesConfiguration.
     */
    private ConsumerFactory<String, Object> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(kafkaPropertiesConfiguration.buildConsumerProperties());
    }

    /**
     * This method returns a ConcurrentKafkaListenerContainerFactory bean.
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> baseKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConcurrency(partitions);
        factory.setConsumerFactory(consumerFactory());

        if (isRecordFilteringEnabled()) {
            factory.setAckDiscarded(true);
            factory.setRecordFilterStrategy(Optional.ofNullable(recordFilteringStrategy).orElseThrow(() -> new IllegalArgumentException("Please consider defining a bean of type: " + BaseKafkaSubscriberMessageFilter.class)));
        }
        if (!Optional.ofNullable(kafkaPropertiesConfiguration.getConsumer().getEnableAutoCommit()).orElse(Boolean.FALSE)) {
            factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        }
        if (isBatchSubscriberEnabled()) {
            factory.setBatchListener(this.batchSubscriberEnabled);
            factory.setBatchInterceptor(recordInterceptor);
        }

        factory.setCommonErrorHandler(Optional.ofNullable(kafkaErrorHandler).orElseThrow(() -> new IllegalArgumentException("Please consider defining a bean of type: " + BaseKafkaSubscriberErrorHandler.class)));
        factory.setRecordInterceptor(recordInterceptor);
        return factory;
    }
}

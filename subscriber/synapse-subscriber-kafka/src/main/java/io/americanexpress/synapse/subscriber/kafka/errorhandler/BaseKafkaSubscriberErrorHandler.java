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
package io.americanexpress.synapse.subscriber.kafka.errorhandler;

import io.americanexpress.synapse.subscriber.kafka.annotation.KafkaErrorHandler;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


/**
 * {@code BaseKafkaSubscriberErrorHandler} class is used to handle exceptions.
 *
 * @author Krishna Kuchikulla
 */
@KafkaErrorHandler
public class BaseKafkaSubscriberErrorHandler implements CommonErrorHandler {

    /**
     * Logger used for this class.
     */
    protected final XLogger log = XLoggerFactory.getXLogger(getClass());

    @Override
    public void handleRecord(Exception thrownException, ConsumerRecord<?, ?> record, Consumer<?, ?> consumer, MessageListenerContainer container) {
        this.handleException(thrownException, Collections.singletonList(record), consumer);
    }

    @Override
    public void handleRemaining(Exception thrownException, List<ConsumerRecord<?, ?>> records, Consumer<?, ?> consumer, MessageListenerContainer container) {
        this.handleException(thrownException, records, consumer);
    }

    @Override
    public void handleBatch(Exception thrownException, ConsumerRecords<?, ?> data, Consumer<?, ?> consumer, MessageListenerContainer container, Runnable invokeListener) {
        this.handleBatchException(thrownException, data, consumer);
    }

    /**
     * This method handles the exception thrown by the Kafka Subscriber.
     *
     * @param thrownException exception thrown by the Kafka Subscriber, eg: SerializationException
     * @param data            list of Kafka records
     * @param consumer        kafka consumer
     */
    public void handleBatchException(Exception thrownException, @Nullable ConsumerRecords<?, ?> data, Consumer<?, ?> consumer) {

        if (Optional.ofNullable(data).isPresent()) {
            data.iterator().forEachRemaining(kafkaRecord -> this.handleException(thrownException, consumer, kafkaRecord));
        }
    }

    /**
     * This method handles the exception thrown by the Kafka Subscribers.
     *
     * @param thrownException exception thrown by the Kafka Subscriber, eg: SerializationException
     * @param records         list of Kafka records
     * @param consumer        kafka consumer
     */
    public void handleException(Exception thrownException, @Nullable List<ConsumerRecord<?, ?>> records,
                                Consumer<?, ?> consumer) {

        if (!CollectionUtils.isEmpty(records)) {
            records.forEach(kafkaRecord -> this.handleException(thrownException, consumer, kafkaRecord));
        }
    }

    private void handleException(Exception thrownException, Consumer<?, ?> consumer, ConsumerRecord<?, ?> kafkaRecord) {
        int partition = kafkaRecord.partition();
        long offset = kafkaRecord.offset();
        String topic = kafkaRecord.topic();
        TopicPartition topicPartition = new TopicPartition(topic, partition);
        consumer.seek(topicPartition, offset++);
        consumer.commitAsync();
        log.info("MESSAGE_SKIPPED, TOPIC: {}, OFFSET: {}, CAUSE: {}, EXCEPTION: {}", topic, offset,
                thrownException.getCause(), thrownException.getLocalizedMessage());
    }

}

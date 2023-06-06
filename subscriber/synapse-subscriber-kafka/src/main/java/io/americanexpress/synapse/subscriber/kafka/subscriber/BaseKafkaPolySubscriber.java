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
package io.americanexpress.synapse.subscriber.kafka.subscriber;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

import java.util.concurrent.ExecutorService;


/**
 * {@code BaseKafkaPolySubscriber} class is used as the base Kafka Subscriber for batch.
 *
 * @param <K> class type of key for message that is consumed
 * @param <V> class type of message that is consumed
 *
 * @author Krishna Kuchikulla
 */
public abstract class BaseKafkaPolySubscriber<K, V> extends BaseKafkaSubscriber {

    protected BaseKafkaPolySubscriber(ExecutorService executorService) {
        super(executorService);
    }

    /**
     * This method is used to consume messages from kafka topic.
     *
     * @param consumerRecords list of kafka messages
     * @param acknowledgment acknowledgement
     */
    @KafkaListener(topics = "#{'${kafka.subscriber.topics}'.split(',')}",
            autoStartup = "${kafka.subscriber.auto.startup:true}",
            groupId = "${kafka.subscriber.group.id}",
            containerFactory = "baseKafkaListenerContainerFactory")
    private void consumePoly(ConsumerRecords<K, V> consumerRecords, Acknowledgment acknowledgment) {
        if (isAutoCommitEnabled()) {
            acknowledgment.acknowledge();
        }
        processPoly(consumerRecords, acknowledgment);
    }

    /**
     * This method is used to get the runnable task to process the consumed messages.
     *
     * @return task that needs to be executed for consumer messages.
     */
    protected abstract Runnable processPoly(ConsumerRecords<K, V> consumerRecords, Acknowledgment acknowledgment);
}

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
package io.americanexpress.synapse.subscriber.kafka.filter;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;


/**
 * {@code BaseKafkaSubscriberMessageFilter} class is used as the base message filtering class for Kafka Subscriber.
 *
 * @author Krishna Kuchikulla
 */
public abstract class BaseKafkaSubscriberMessageFilter<K, V> implements RecordFilterStrategy<K, V> {

    /**
     * Logger used for this class.
     */
    protected final XLogger log = XLoggerFactory.getXLogger(getClass());

    @Override
    public boolean filter(ConsumerRecord<K, V> consumerRecord) {
        boolean messageFiltered = this.filterMessage(consumerRecord);
        if (messageFiltered) {
            log.info("MESSAGE_DISCARDED, TOPIC: {}, PARTITION: {}, OFFSET: {}, KEY: {}",
                    consumerRecord.topic(), consumerRecord.partition(), consumerRecord.offset(), consumerRecord.key());
        }
        return messageFiltered;
    }

    /**
     * This method is used to filter messages prior to Kafka Subscriber.
     * <p>
     * Note: Override filterBatch() if you want to filter multiple messages at once.
     * Please set ackDiscarded:true to acknowledge filtered out messages
     *
     * @param consumerRecord Kafka message
     * @return true to discard.
     */
    protected abstract boolean filterMessage(ConsumerRecord<K, V> consumerRecord);

}

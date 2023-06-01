package io.americanexpress.synapse.subscriber.kafka.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;


/**
 * {@code BaseKafkaSubscriberMessageFilter} class is used as the base message filtering class for Kafka Subscriber.
 *
 * @author Krishna Kuchikulla
 */
@Slf4j
public abstract class BaseKafkaSubscriberMessageFilter<K, V> implements RecordFilterStrategy<K, V> {

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

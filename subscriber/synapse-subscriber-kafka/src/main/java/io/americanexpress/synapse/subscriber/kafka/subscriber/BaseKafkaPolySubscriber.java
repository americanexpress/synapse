package io.americanexpress.synapse.subscriber.kafka.subscriber;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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

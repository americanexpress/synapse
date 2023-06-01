package io.americanexpress.synapse.subscriber.kafka.subscriber;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

import java.util.concurrent.ExecutorService;

/**
 * {@code BaseKafkaMonoSubscriber} class is used as the base Kafka Subscriber.
 *
 * @param <K> class type of key for message that is consumed
 * @param <V> class type of message that is consumed
 *
 * @author Krishna Kuchikulla
 */
@Slf4j
public abstract class BaseKafkaMonoSubscriber<K, V> extends BaseKafkaSubscriber {

    protected BaseKafkaMonoSubscriber(ExecutorService executorService) {
        super(executorService);
    }

    /**
     * This method is used to consume messages from kafka topic.
     *
     * @param consumerRecord Kafka message
     * @param acknowledgment acknowledgement
     */
    @KafkaListener(topics = "#{'${kafka.subscriber.topics}'.split(',')}",
            autoStartup = "${kafka.subscriber.auto.startup:true}",
            groupId = "${kafka.subscriber.group.id}",
            containerFactory = "baseKafkaListenerContainerFactory")
    protected void consumeMono(ConsumerRecord<K, V> consumerRecord, Acknowledgment acknowledgment) {
        if (isAutoCommitEnabled()) {
            acknowledgment.acknowledge();
        }
        executorService.submit(processMono(consumerRecord, acknowledgment));
    }

    /**
     * This method is used to get the runnable task to process the consumed messages.
     *
     * @return task that needs to be executed for consumer messages.
     */
    protected abstract Runnable processMono(ConsumerRecord<K, V> consumerRecord, Acknowledgment acknowledgment);
}

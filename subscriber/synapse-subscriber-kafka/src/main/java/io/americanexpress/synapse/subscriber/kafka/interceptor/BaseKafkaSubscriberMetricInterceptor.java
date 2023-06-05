package io.americanexpress.synapse.subscriber.kafka.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.listener.BatchInterceptor;
import org.springframework.kafka.listener.RecordInterceptor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;


/**
 * {@code BaseKafkaSubscriberMetricInterceptor} class is used intercept the messages to log metrics.
 *
 * @author Krishna Kuchikulla
 */
@Component
@Slf4j
public class BaseKafkaSubscriberMetricInterceptor<K, V> implements RecordInterceptor<K, V>, BatchInterceptor<K, V> {

    /**
     * This method is used to intercept messages prior to Kafka Subscriber.
     * <p>
     * Note: if you want to add logic in interceptor, please override preHandle() method.
     *
     * @param consumerRecord Kafka message
     * @return kafka message
     */
    @Override
    public ConsumerRecord<K, V> intercept(ConsumerRecord<K, V> consumerRecord) {
        startMetricLog(consumerRecord);
        return preHandle(consumerRecord);
    }

    /**
     * This method is used to intercept messages after processing the message by Kafka Subscriber.
     * <p>
     * Note: if you want to add logic in interceptor, please override postHandle() method.
     *
     * @param consumerRecord Kafka message
     */
    @Override
    public void afterRecord(ConsumerRecord<K, V> consumerRecord, Consumer<K, V> consumer) {
        endMetricLog(consumerRecord);
        postHandle(consumerRecord, consumer);
    }

    /**
     * This method allows user to intercept messages prior to Kafka Subscriber.
     *
     * @param consumerRecord Kafka message
     * @return kafka message
     */
    protected ConsumerRecord<K,V> preHandle(ConsumerRecord<K, V> consumerRecord) {
        return consumerRecord;
    }

    /**
     * This method allows user to intercept messages after processing the message by Kafka Subscriber.
     *
     * @param consumerRecord Kafka message
     */
    protected void postHandle(ConsumerRecord<K,V> consumerRecord, Consumer<K,V> consumer) {}

    /**
     * This method is used to intercept messages prior to Kafka batch subscriber.
     * <p>
     * Note: if you want to add logic in interceptor, please override preHandleBatch() method.
     *
     * @param consumerRecords Kafka message
     * @param consumer kafka consumer
     * @return kafka messages
     */
    @Override
    public ConsumerRecords<K, V> intercept(ConsumerRecords<K, V> consumerRecords, Consumer<K, V> consumer) {
        consumerRecords.iterator().forEachRemaining(this::startMetricLog);
        return preHandleBatch(consumerRecords);
    }

    /**
     * This method allows is used to intercept messages after processing the message by Kafka batch subscriber.
     *
     * @param consumerRecords Kafka message
     * @param consumer kafka consumer
     */
    @Override
    public void success(ConsumerRecords<K, V> consumerRecords, Consumer<K, V> consumer) {
        consumerRecords.iterator().forEachRemaining(this::endMetricLog);
        postHandleBatch(consumerRecords, consumer);
    }

    /**
     * This method allows user to intercept messages before processing the message by Kafka batch subscriber.
     *
     * @param consumerRecords Kafka messages
     */
    protected ConsumerRecords<K,V> preHandleBatch(ConsumerRecords<K, V> consumerRecords) {
        return consumerRecords;
    }

    /**
     * This method allows user to intercept messages after processing the message by Kafka batch subscriber.
     *
     * @param consumerRecords Kafka messages
     * @param consumer kafka consumer
     */
    protected void postHandleBatch(ConsumerRecords<K,V> consumerRecords, Consumer<K,V> consumer) {}

    /**
     * This method is used to log metrics before processing the kafka message.
     *
     * @param consumerRecord kafka message
     */
    private void startMetricLog(ConsumerRecord<K, V> consumerRecord) {
        String startTime = String.valueOf(System.currentTimeMillis());
        consumerRecord.headers().add("startTime", startTime.getBytes());
        log.info("TOPIC: {}, PARTITION: {}, OFFSET: {}, KEY: {}",
                consumerRecord.topic(), consumerRecord.partition(), consumerRecord.offset(), consumerRecord.key());
    }

    /**
     * This method is used to log metrics after processing the kafka message.
     *
     * @param consumerRecord kafka message
     */
    private void endMetricLog(ConsumerRecord<K, V> consumerRecord) {
        long startTime = Long.parseLong(new String(consumerRecord.headers().lastHeader("startTime").value(), StandardCharsets.UTF_8));
        long executionTime = System.currentTimeMillis() - startTime;
        log.info("TOPIC: {}, PARTITION: {}, OFFSET: {}, KEY: {}, PROCESSING_TIME: {} ms.",
                consumerRecord.topic(), consumerRecord.partition(), consumerRecord.offset(), consumerRecord.key(), executionTime);
    }
}

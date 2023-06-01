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

    @Override
    public ConsumerRecord<K, V> intercept(ConsumerRecord<K, V> consumerRecord) {
        startMetricLog(consumerRecord);
        return preHandle(consumerRecord);
    }

    @Override
    public void afterRecord(ConsumerRecord<K, V> consumerRecord, Consumer<K, V> consumer) {
        endMetricLog(consumerRecord);
        postHandle(consumerRecord, consumer);
    }

    protected ConsumerRecord<K,V> preHandle(ConsumerRecord<K, V> consumerRecord) {
        return consumerRecord;
    }

    protected void postHandle(ConsumerRecord<K,V> consumerRecord, Consumer<K,V> consumer) {}


    @Override
    public ConsumerRecords<K, V> intercept(ConsumerRecords<K, V> consumerRecords, Consumer<K, V> consumer) {
        consumerRecords.iterator().forEachRemaining(this::startMetricLog);
        return preHandleBatch(consumerRecords);
    }

    @Override
    public void success(ConsumerRecords<K, V> consumerRecords, Consumer<K, V> consumer) {
        consumerRecords.iterator().forEachRemaining(this::endMetricLog);
        postHandleBatch(consumerRecords, consumer);
    }
    protected ConsumerRecords<K,V> preHandleBatch(ConsumerRecords<K, V> consumerRecords) {
        return consumerRecords;
    }

    protected void postHandleBatch(ConsumerRecords<K,V> consumerRecords, Consumer<K,V> consumer) {}

    private void startMetricLog(ConsumerRecord<K, V> consumerRecord) {
        String startTime = String.valueOf(System.currentTimeMillis());
        consumerRecord.headers().add("startTime", startTime.getBytes());
        log.info("TOPIC: {}, PARTITION: {}, OFFSET: {}, KEY: {}",
                consumerRecord.topic(), consumerRecord.partition(), consumerRecord.offset(), consumerRecord.key());
    }

    private void endMetricLog(ConsumerRecord<K, V> consumerRecord) {
        long startTime = Long.parseLong(new String(consumerRecord.headers().lastHeader("startTime").value(), StandardCharsets.UTF_8));
        long executionTime = System.currentTimeMillis() - startTime;
        log.info("TOPIC: {}, PARTITION: {}, OFFSET: {}, KEY: {}, PROCESSING_TIME: {} ms.",
                consumerRecord.topic(), consumerRecord.partition(), consumerRecord.offset(), consumerRecord.key(), executionTime);
    }
}

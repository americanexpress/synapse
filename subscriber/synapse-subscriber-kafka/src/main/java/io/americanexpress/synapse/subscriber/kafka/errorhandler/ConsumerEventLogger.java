package io.americanexpress.synapse.subscriber.kafka.errorhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.event.ConsumerFailedToStartEvent;
import org.springframework.kafka.event.ConsumerStartedEvent;
import org.springframework.kafka.event.ConsumerStoppedEvent;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * {@code ConsumerEventLogger} class is used to log consumer events.
 *
 * @author Krishna Kuchikulla
 */
@Component
@Slf4j
public class ConsumerEventLogger {

    /**
     * This method is used to log Kafka Listeners stopped events.
     *
     * @param event consumerStoppedEvent
     */
    @EventListener(ConsumerStoppedEvent.class)
    public void consumerStoppedEventHandler(ConsumerStoppedEvent event) {
        Set<String> topics = Arrays.stream((((KafkaMessageListenerContainer) event.getSource()).getContainerProperties()).getTopics()).collect(Collectors.toSet());
        log.info("LISTENER_STATUS: {}, TOPIC(S): {}, REASON: {}", "STOPPED", topics, event.getReason());
    }

    /**
     * This method is used to log Kafka Listeners started events.
     *
     * @param event consumerStartedEvent
     */
    @EventListener(ConsumerStartedEvent.class)
    public void consumerStartedEventHandler(ConsumerStartedEvent event) {
        Set<String> topics = Arrays.stream((((KafkaMessageListenerContainer) event.getSource()).getContainerProperties()).getTopics()).collect(Collectors.toSet());
        log.info("LISTENER_STATUS: {}, TOPIC(S): {}", "STARTED", topics);
    }

    /**
     * This method is used to log Kafka Listeners failed to start events.
     *
     * @param event consumerFailedToStartEvent
     */
    @EventListener(ConsumerFailedToStartEvent.class)
    public void consumerFailedToStartEventHandler(ConsumerFailedToStartEvent event) {
        Set<String> topics = Arrays.stream((((KafkaMessageListenerContainer) event.getSource()).getContainerProperties()).getTopics()).collect(Collectors.toSet());
        log.info("LISTENER_STATUS: {}, TOPIC(S): {}", "FAILED_TO_STARTED", topics);
    }

}

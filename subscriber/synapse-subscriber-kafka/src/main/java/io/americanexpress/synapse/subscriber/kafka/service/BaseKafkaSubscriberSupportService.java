package io.americanexpress.synapse.subscriber.kafka.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.Lifecycle;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * {@code BaseKafkaSubscriberSupportService} class is used interact with the KafkaListenerEndpointRegistry to start, stop and get status of subscribers.
 *
 * @author Krishna Kuchikulla
 */
@Service
@RequiredArgsConstructor
public class BaseKafkaSubscriberSupportService {

    /**
     * KafkaListenerEndpointRegistry
     */
    private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    /**
     * This method is used to start kafka subscribers.
     */
    public void startSubscribers() {
        kafkaListenerEndpointRegistry.getListenerContainers().forEach(Lifecycle::start);
    }

    /**
     * This method is used to get the status of kafka subscribers.
     */
    public Map<String, String> getSubscribersStatus() {
        HashMap<String, String> listenerStatus = new HashMap<>();
        kafkaListenerEndpointRegistry.getAllListenerContainers()
                .forEach(listenerContainer -> listenerStatus.put(listenerContainer.getListenerId(), String.valueOf(listenerContainer.isRunning())));
        return listenerStatus;
    }

    /**
     * This method is used to stop kafka subscribers.
     */
    public void stopSubscribers() {
        kafkaListenerEndpointRegistry.getListenerContainers().forEach(Lifecycle::stop);
    }

}

package io.americanexpress.synapse.subscriber.kafka.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.Lifecycle;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * {@code BaseKafkaSupportService} class is used interact with the KafkaListenerEndpointRegistry to start, stop and get status of listeners.
 *
 * @author Krishna Kuchikulla
 */
@Service
@RequiredArgsConstructor
public class BaseKafkaSupportService {

    private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    public void startListeners() {
        kafkaListenerEndpointRegistry.getListenerContainers().forEach(Lifecycle::start);
    }

    public Map<String, String> getListenersStatus() {
        HashMap<String, String> listenerStatus = new HashMap<>();
        kafkaListenerEndpointRegistry.getAllListenerContainers()
                .forEach(listenerContainer -> listenerStatus.put(listenerContainer.getListenerId(), String.valueOf(listenerContainer.isRunning())));
        return listenerStatus;
    }

    public void stopListeners() {
        kafkaListenerEndpointRegistry.getListenerContainers().forEach(Lifecycle::stop);
    }
}

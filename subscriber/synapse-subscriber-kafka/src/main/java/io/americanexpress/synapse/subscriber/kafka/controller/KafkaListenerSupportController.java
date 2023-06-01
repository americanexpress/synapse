package io.americanexpress.synapse.subscriber.kafka.controller;

import io.americanexpress.synapse.subscriber.kafka.service.BaseKafkaSupportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * {@code KafkaListenerSupportController} class is used interact with the KafkaSupportService to start, stop and get status of listeners.
 *
 * @author Krishna Kuchikulla
 */
@RestController()
@RequestMapping("/kafkaSupport")
@RequiredArgsConstructor
public class KafkaListenerSupportController {

    /**
     * KafkaSupportService
     */
    private final BaseKafkaSupportService kafkaSupportService;

    /**
     * This endpoint is used to start kafka listeners.
     */
    @GetMapping("/startListeners")
    public void startListeners() {
        kafkaSupportService.startListeners();
    }

    /**
     * This endpoint is used to get status of the kafka listeners.
     */
    @GetMapping("/getListenersStatus")
    public Map<String, String> getListenersStatus() {
        return kafkaSupportService.getListenersStatus();
    }

    /**
     * This endpoint is used to stop kafka listeners.
     */
    @GetMapping("/stopListeners")
    public void stopListeners() {
        kafkaSupportService.stopListeners();
    }
}

package io.americanexpress.synapse.subscriber.kafka.controller;

import io.americanexpress.synapse.subscriber.kafka.service.BaseKafkaSubscriberSupportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * {@code KafkaSubscriberSupportController} class is used interact with the KafkaSubscriberSupportService to start, stop and get status of listeners.
 *
 * @author Krishna Kuchikulla
 */
@RestController()
@RequestMapping("/kafkaSubscriberSupport")
@RequiredArgsConstructor
public class KafkaSubscriberSupportController {

    /**
     * BaseKafkaSubscriberSupportService
     */
    private final BaseKafkaSubscriberSupportService kafkaSubscriberSupportService;

    /**
     * This endpoint is used to start kafka subscribers.
     */
    @GetMapping("/startSubscribers")
    public void startSubscribers() {
        kafkaSubscriberSupportService.startSubscribers();
    }

    /**
     * This endpoint is used to get status of the kafka subscribers.
     */
    @GetMapping("/getSubscribersStatus")
    public Map<String, String> getSubscribersStatus() {
        return kafkaSubscriberSupportService.getSubscribersStatus();
    }

    /**
     * This endpoint is used to stop kafka subscribers.
     */
    @GetMapping("/stopSubscribers")
    public void stopSubscribers() {
        kafkaSubscriberSupportService.stopSubscribers();
    }

}

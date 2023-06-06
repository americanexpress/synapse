/*
 * Copyright 2020 American Express Travel Related Services Company, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.americanexpress.synapse.subscriber.kafka.controller;

import io.americanexpress.synapse.subscriber.kafka.service.BaseKafkaSubscriberSupportService;
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
public class KafkaSubscriberSupportController {

    /**
     * BaseKafkaSubscriberSupportService
     */
    private final BaseKafkaSubscriberSupportService kafkaSubscriberSupportService;


    /**
     * KafkaSubscriberSupportController constructor
     *
     * @param kafkaSubscriberSupportService kafkaSubscriberSupportService
     */
    public KafkaSubscriberSupportController(BaseKafkaSubscriberSupportService kafkaSubscriberSupportService) {
        this.kafkaSubscriberSupportService = kafkaSubscriberSupportService;
    }

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

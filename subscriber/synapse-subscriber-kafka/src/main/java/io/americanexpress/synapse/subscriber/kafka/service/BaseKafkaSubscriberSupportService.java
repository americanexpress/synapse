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
package io.americanexpress.synapse.subscriber.kafka.service;

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
public class BaseKafkaSubscriberSupportService {

    /**
     * KafkaListenerEndpointRegistry
     */
    private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    /**
     * BaseKafkaSubscriberSupportService constructor
     *
     * @param kafkaListenerEndpointRegistry kafkaListenerEndpointRegistry
     */
    public BaseKafkaSubscriberSupportService(KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry) {
        this.kafkaListenerEndpointRegistry = kafkaListenerEndpointRegistry;
    }

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

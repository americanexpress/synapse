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
package io.americanexpress.synapse.subscriber.kafka.errorhandler;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
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
public class ConsumerEventLogger {

    /**
     * Logger used for this class.
     */
    protected final XLogger log = XLoggerFactory.getXLogger(getClass());

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

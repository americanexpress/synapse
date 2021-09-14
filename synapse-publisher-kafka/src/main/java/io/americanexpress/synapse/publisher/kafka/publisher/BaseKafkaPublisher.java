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
package io.americanexpress.synapse.publisher.kafka.publisher;

import org.springframework.kafka.core.KafkaTemplate;

/**
 * <code>BaseKafkaPublisher</code> class contains different methods used to publish message to a kafka topic.
 *
 * @author Krishna Kuchikulla
 */
public abstract class BaseKafkaPublisher {

	/**
	 * KafkaTemplate.
	 */
	private final KafkaTemplate<String, String> kafkaTemplate;

	/**
	 * Constructor for BaseKafkaPublisher.
	 *
	 * @param kafkaTemplate kafka template
	 */
	protected BaseKafkaPublisher(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	/**
	 * This method publishes messages to kafka topic
	 *
	 * @param message message to be published to kafka topic
	 */
	public void publish(String message) {
		kafkaTemplate.send(kafkaTemplate.getDefaultTopic(), message);
	}

	/**
	 * This method publishes messages to kafka topic
	 *
	 * @param message   message to be published to kafka topic
	 * @param partition partition for the kafka topic where the message is published
	 */
	public void publish(String message, int partition) {
		kafkaTemplate.send(kafkaTemplate.getDefaultTopic(), partition, null, message);
	}

	/**
	 * This method publishes messages to kafka topic
	 *
	 * @param key   key for message to be published to kafka topic
	 * @param value message to be published to kafka topic
	 */
	public void publish(String key, String value) {
		kafkaTemplate.send(kafkaTemplate.getDefaultTopic(), key, value);
	}

	/**
	 * This method publishes messages to kafka topic
	 *
	 * @param key       key for message to be published to kafka topic
	 * @param value     message to be published to kafka topic
	 * @param partition partition for the kafka topic where the message is published
	 */
	public void publish(String key, String value, int partition) {
		kafkaTemplate.send(kafkaTemplate.getDefaultTopic(), partition, key, value);
	}
}

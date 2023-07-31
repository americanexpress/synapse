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
package io.americanexpress.synapse.subscriber.kafka.subscriber;

import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.ExecutorService;


/**
 * {@code BaseKafkaSubscriber} class is used as the base Kafka Subscriber.
 *
 * @author Krishna Kuchikulla
 */
public abstract class BaseKafkaSubscriber {

    /**
     * This method is used to consume messages from kafka topic.
     */
    protected final ExecutorService executorService;

    /**
     * Auto-Commit
     */
    @Value("${kafka.subscriber.enable.auto.commit:true}")
    private boolean autoCommitEnabled;

    /**
     * BaseKafkaSubscriber constructor
     *
     * @param executorService executorService
     */
    protected BaseKafkaSubscriber(ExecutorService executorService) {
        this.executorService = executorService;
    }

    /**
     * This method is used to get executor service.
     */
    public ExecutorService getExecutorService() {
        return executorService;
    }

    /**
     * This method is used to get autoCommitEnabled flog.
     */
    public boolean isAutoCommitEnabled() {
        return autoCommitEnabled;
    }
}

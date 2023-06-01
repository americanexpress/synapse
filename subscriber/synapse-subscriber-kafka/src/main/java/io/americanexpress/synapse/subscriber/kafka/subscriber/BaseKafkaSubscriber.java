package io.americanexpress.synapse.subscriber.kafka.subscriber;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.ExecutorService;


/**
 * {@code BaseKafkaSubscriber} class is used as the base Kafka Subscriber.
 *
 * @author Krishna Kuchikulla
 */
@Slf4j
@RequiredArgsConstructor
@Getter
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

}

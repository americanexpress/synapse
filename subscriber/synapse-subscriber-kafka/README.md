# Synapse-subscriber-kafka
This project is the go-to solution for any application which has to interact with the Kafka topics.

## Table of contents
- [Pre requisites](#pre-requisites)

## Pre requisites
In order to set up, you'll need the following
- JDK 17 or higher
- Gradle 7 or higher
- Any IDE of your choice

## How to use:
### Integrating with your application

1. To utilize this module, add the following dependency to the pom.xml file:
```
        <dependency>
            <groupId>com.americanexpress</groupId>
            <artifactId>synapse-subscriber-kafka</artifactId>
            <version>0.3.14-SNAPSHOT</version>
        </dependency>
```
Or add the following to the build.gradle file:
```
    implementation 'io.americanexpress.synapse:synapse-subscriber-kafka:+'
```


### Creating a Kafka Subscriber module

1. Create `SampleKafkaPropertiesConfiguration` configuration class and extend `KafkaPropertiesConfiguration` to capture the kafka properties.
    Annotate `SampleKafkaPropertiesConfiguration` configuration bean as `@Primary`
```
@Configuration
@Primary
public class SampleKafkaPropertiesConfiguration extends BaseKafkaPropertiesConfiguration<SampleKafkaPropertiesConfiguration.SampleKafkaConsumer,
        SampleKafkaPropertiesConfiguration.SampleKafkaSSL> {

    public SampleKafkaPropertiesConfiguration(SampleKafkaConsumer consumer, SampleKafkaSSL ssl, Environment environment) {
        super(consumer, ssl, environment);
    }

    @Configuration
    public static class SampleKafkaConsumer extends BaseKafkaConsumer {

        public SampleKafkaConsumer(Environment environment) {
            super(environment);
        }
    }

    @Configuration
    public static class SampleKafkaSSL extends BaseKafkaSsl {

        public SampleKafkaSSL(Environment environment, ResourceLoader resourceLoader) {
            super(environment, resourceLoader);
        }
    }

}
```

2. Create a `SampleKafkaErrorHandler` class and extend `ErrorHandler`.
    Annotate `SampleKafkaErrorHandler` with `@KafkaErrorHandler`.
```
@KafkaErrorHandler
public class SampleKafkaErrorHandler extends BaseKafkaSubscriberErrorHandler {
}
```

3. Create `SampleKafkaSubscriberConfiguration` configuration class and extend `KafkaSubscriberConfiguration` to create subscriber configurations.
```
@Configuration
public class SampleKafkaSubscriberConfiguration extends BaseKafkaSubscriberConfiguration {
    protected SampleKafkaSubscriberConfiguration(SampleKafkaPropertiesConfiguration kafkaPropertiesConfiguration, SampleKafkaErrorHandler kafkaErrorHandler, Environment environment, BaseKafkaSubscriberMetricInterceptor recordInterceptor) {
        super(kafkaPropertiesConfiguration, kafkaErrorHandler, environment, recordInterceptor);
    }

}
```

4. Create a `SampleKafkaSubscriber` class and extend `BaseKafkaMonoSubscriber<String, DesiredDeserializationObject>` or `BaseKafkaPolySubscriber<String, DesiredDeserializationObject>`
    Annotate `SampleKafkaSubscriber` class with `@KakfaSubscriber`
```
@KafkaSubscriber
@Slf4j
public class SampleKafkaMonoSubscriber extends BaseKafkaMonoSubscriber<String, String> {

    public SampleKafkaMonoSubscriber(ExecutorService executorService) {
        super(executorService);
    }

}
```

5. Override the method `processMono(ConsumerRecord<String, DesiredDeserializationObject> consumerRecord, Acknowledgement acknowledgement)` from the `BaseKafkaMonoSubscriber` to provide a runnable to process the message.
```
    @Override
    protected Runnable processMono(ConsumerRecord<String, String> consumerRecord, Acknowledgment acknowledgment) {
        return () -> log.info("Message: "+ consumerRecord);
    }
```

6. Add the following properties to connect to the Kafka topic.
```
# Properties required for synapse-subscriber-kafka.
key.identification=
keyStore.location=classpath:
keyStore.identification=
keyStore.type=JKS
trustStore.location=classpath:
trustStore.identification=
trustStore.type=JKS
protocol=SSL
kafka.bootstrap.servers=localhost:9092

# Properties required to control subscriber behaviour.
kafka.partitions.count=5
kafka.subscriber.topics=sample-synapse-topic
kafka.subscriber.auto.startup=true
kafka.subscriber.group.id=test-consumer-group
kafka.subscriber.maximum.poll.records=5
kafka.subscriber.enable.auto.commit=true
```

#### Batch kafka subscriber
- To run the kafka subscriber in batch mode, enable the following property.
```
    kafka.subscriber.batch.enabled=true
```
- Update `SampleKafkaSubscriber` to extend `BaseKafkaPolySubscriber` and override `processPoly(ConsumerRecords<String, String> consumerRecords, Acknowledgment acknowledgment)` method.
```
    @KafkaSubscriber
    @Slf4j
    public class SampleKafkaPolySubscriber extends BaseKafkaPolySubscriber<String, String> {

        public SampleKafkaPolySubscriber(ExecutorService executorService) {
            super(executorService);
        }

    @Override
    protected Runnable processPoly(ConsumerRecords<String, String> consumerRecords, Acknowledgment acknowledgment) {
        return () -> consumerRecords.iterator()
                .forEachRemaining(consumerRecord -> log.info("Message: "+ consumerRecord));
    }
}
```


#### Message filter
- To add filtering capability for kafka subscriber , enable the following property
```
    kafka.subscriber.filter.enabled=true
```

- Create `SampleKafkaMessageFilter` and extend `BaseKafkaSubscriberMessageFilter<String, DesiredDeserializationObject>`.
    Provide predicate to filter messages by overriding the `filterMessage()`method.
```
@Component
public class SampleKafkaSubscriberMessageFilter extends BaseKafkaSubscriberMessageFilter<String, String> {

    @Override
    protected boolean filterMessage(ConsumerRecord<String, String> consumerRecord) {
        // logic to filter out the message. 
        // return true to discard the message.
    }
}
```
- Note: To filter multiple messages at once, override `filterBatch(List<ConsumerRecord<K, V>> records)` method.

- Pass `SampleKafkaMessageFilter` in `SampleKafkaSubscriberConfiguration` constructor.
```
@Configuration
public class SampleKafkaSubscriberConfiguration extends BaseKafkaSubscriberConfiguration {
    protected SampleKafkaSubscriberConfiguration(SampleKafkaPropertiesConfiguration kafkaPropertiesConfiguration, SampleKafkaErrorHandler kafkaErrorHandler, SampleKafkaSubscriberMessageFilter recordFilteringStrategy, Environment environment, BaseKafkaSubscriberMetricInterceptor recordInterceptor) {
        super(kafkaPropertiesConfiguration, kafkaErrorHandler, recordFilteringStrategy, environment, recordInterceptor);
    }
}
```

#### Message interceptor

- To intercept a message, create `SampleKafkaSubscriberMetricInterceptor` and extend `BaseKafkaSubscriberMetricInterceptor`,
    override any of the following methods for desired functionality.
    `preHandle(ConsumerRecord<K, V> consumerRecord)` - to intercept message before subscriber.
    `postHandle(ConsumerRecord<K,V> consumerRecord, Consumer<K,V> consumer)` - to intercept message after subscriber.
    `preHandleBatch(ConsumerRecords<K, V> consumerRecords)` - to intercept messages before subscriber when batch subscriber is used.
    `postHandleBatch(ConsumerRecords<K,V> consumerRecords, Consumer<K,V> consumer)` - to intercept messages after subscriber when batch subscriber is used.

- Pass `SampleKafkaSubscriberMetricInterceptor` in `SampleKafkaSubscriberConfiguration` constructor. 
```
    @Configuration
    public class SampleKafkaSubscriberConfiguration extends BaseKafkaSubscriberConfiguration {
        protected SampleKafkaSubscriberConfiguration(SampleKafkaPropertiesConfiguration kafkaPropertiesConfiguration, SampleKafkaErrorHandler kafkaErrorHandler, SampleKafkaSubscriberMessageFilter recordFilteringStrategy, Environment environment, SampleKafkaSubscriberMetricInterceptor recordInterceptor) {
        super(kafkaPropertiesConfiguration, kafkaErrorHandler, recordFilteringStrategy, environment, recordInterceptor);
        }
    }
```

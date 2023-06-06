# Synapse-subscriber-kafka

## Description
- This project is the synapse kafka subscriber framework used for any application which has to subscribe to Kafka topics.
  It provides several out-of-the-box functionalities like:
  - Built-in Kafka subscriber which can connect to topic with minimal properties.
  - Built-in functionality to interact with kafka broker via API to start, stop and get subscriber status.
  - An open to extension base class to support interceptors
  - An open to extension base class to support filtering capability
  - An open to extension base class to support batch subscribers

## Table of contents
- [Pre requisites](#pre-requisites)
- [How to use](#how-to-use)
  - [Integrating with your application](#integrating-with-your-application)
  - [Creating Kafka Subscriber Module](#creating-kafka-subscriber-module)
  - [Batch Kafka Subscriber](#batch-kafka-subscriber)
  - [Custom Message Filter](#custom-message-filter)
  - [Custom Message Interceptor](#custom-message-interceptor)
  - [Custom Error Handler](#custom-error-handler)
  - [Kafka Subscriber Support APIs](#kafka-subscriber-support-apis)


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


### Creating Kafka Subscriber Module

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

2. Create `SampleKafkaSubscriberConfiguration` configuration class and extend `KafkaSubscriberConfiguration` to create subscriber configurations.
```
    @Configuration
    public class SampleKafkaSubscriberConfiguration extends BaseKafkaSubscriberConfiguration {
        protected SampleKafkaSubscriberConfiguration(SampleKafkaPropertiesConfiguration kafkaPropertiesConfiguration, BaseKafkaSubscriberErrorHandler kafkaErrorHandler, Environment environment, BaseKafkaSubscriberMetricInterceptor recordInterceptor) {
            super(kafkaPropertiesConfiguration, kafkaErrorHandler, environment, recordInterceptor);
        }
    }
```

3. Create a `SampleKafkaSubscriber` class and extend `BaseKafkaMonoSubscriber<String, DesiredDeserializationObject>` or `BaseKafkaPolySubscriber<String, DesiredDeserializationObject>`
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

4. Override the method `processMono(ConsumerRecord<String, DesiredDeserializationObject> consumerRecord, Acknowledgement acknowledgement)` from the `BaseKafkaMonoSubscriber` to provide a runnable to process the message.
```
    @Override
    protected Runnable processMono(ConsumerRecord<String, String> consumerRecord, Acknowledgment acknowledgment) {
        return () -> log.info("Message: "+ consumerRecord);
    }
```

5. Add the following properties to connect to the Kafka topic.
```
    # Properties required for synapse-subscriber-kafka.
    kafka.key.password=
    kafka.keyStore.location=classpath:
    kafka.keyStore.password=
    kafka.keyStore.type=JKS
    kafka.trustStore.location=classpath:
    kafka.trustStore.password=
    kafka.trustStore.type=JKS
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

#### Batch Kafka Subscriber

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


#### Custom Message Filter

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

#### Custom Message Interceptor

- To intercept a message, create `SampleKafkaSubscriberMetricInterceptor` and extend `BaseKafkaSubscriberMetricInterceptor`,
    override any of the following methods for desired functionality.
    - `preHandle(ConsumerRecord<K, V> consumerRecord)` - to intercept message before subscriber.
    - `postHandle(ConsumerRecord<K,V> consumerRecord, Consumer<K,V> consumer)` - to intercept message after subscriber.
    - `preHandleBatch(ConsumerRecords<K, V> consumerRecords)` - to intercept messages before subscriber when batch subscriber is used.
    - `postHandleBatch(ConsumerRecords<K,V> consumerRecords, Consumer<K,V> consumer)` - to intercept messages after subscriber when batch subscriber is used.

- Pass `SampleKafkaSubscriberMetricInterceptor` in `SampleKafkaSubscriberConfiguration` constructor. 
```
    @Configuration
    public class SampleKafkaSubscriberConfiguration extends BaseKafkaSubscriberConfiguration {
        protected SampleKafkaSubscriberConfiguration(SampleKafkaPropertiesConfiguration kafkaPropertiesConfiguration, SampleKafkaErrorHandler kafkaErrorHandler, SampleKafkaSubscriberMessageFilter recordFilteringStrategy, Environment environment, SampleKafkaSubscriberMetricInterceptor recordInterceptor) {
        super(kafkaPropertiesConfiguration, kafkaErrorHandler, recordFilteringStrategy, environment, recordInterceptor);
        }
    }
```

#### Custom Error Handler

- For a custom error handler, Create a `SampleKafkaSubscriberErrorHandler` class and extend `BaseKafkaSubscriberErrorHandler`.
  Annotate `SampleKafkaSubscriberErrorHandler` with `@KafkaErrorHandler`.
```
    @KafkaErrorHandler
    public class SampleKafkaSubscriberErrorHandler extends BaseKafkaSubscriberErrorHandler {
    }
```

- Pass `SampleKafkaSubscriberErrorHandler` in `SampleKafkaSubscriberConfiguration` constructor.
```
    @Configuration
    public class SampleKafkaSubscriberConfiguration extends BaseKafkaSubscriberConfiguration {
        protected SampleKafkaSubscriberConfiguration(SampleKafkaPropertiesConfiguration kafkaPropertiesConfiguration, SampleKafkaSubscriberErrorHandler kafkaErrorHandler, SampleKafkaSubscriberMessageFilter recordFilteringStrategy, Environment environment, SampleKafkaSubscriberMetricInterceptor recordInterceptor) {
        super(kafkaPropertiesConfiguration, kafkaErrorHandler, recordFilteringStrategy, environment, recordInterceptor);
        }
    }
```

#### Kafka Subscriber Support APIs

- The following APIs are provided by the framework to interact with kafka broker.
  - `GET` : `/kafkaSubscriberSupport/startSubscribers` - to start the subscribers.
  - `GET` : `/kafkaSubscriberSupport/stopSubscribers` - to stop the subscribers.
  - `GET` : `/kafkaSubscriberSupport/getSubscribersStatus` - to get the status of the subscribers.

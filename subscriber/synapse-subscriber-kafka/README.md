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
2. Annotate `SampleKafkaPropertiesConfiguration` configuration bean as `@Primary`
```
@Configuration
@Primary
public class SampleKafkaPropertiesConfiguration extends KafkaPropertiesConfiguration<SampleKafkaPropertiesConfiguration.SampleKafkaTemplate,
                                                                                     SampleKafkaPropertiesConfiguration.SampleKafkaConsumer,
                                                                                     SampleKafkaPropertiesConfiguration.SampleKafkaSSL> {

    public SampleKafkaPropertiesConfiguration(SampleKafkaTemplate template, SampleKafkaConsumer consumer, SampleKafkaSSL ssl, Environment environment) {
        super(template, consumer, ssl, environment);
    }

    @Configuration
    public static class SampleKafkaTemplate extends BaseKafkaTemplate {

        public SampleKafkaTemplate(Environment environment) {
            super(environment);
        }
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

3. Create a `SampleKafkaErrorHandler` class and extend `ErrorHandler`

4. Annotate `SampleKafkaErrorHandler` with `@KafkaErrorHandler`
```
@KafkaErrorHandler
public class SampleKafkaErrorHandler extends ErrorHandler {
}
```

5. Create `SampleKafkaSubscriberConfiguration` configuration class and extend `KafkaSubscriberConfiguration` to create subscriber configurations.
```
@Configuration
public class SampleKafkaSubscriberConfiguration extends KafkaSubscriberConfiguration {

    protected SampleKafkaSubscriberConfiguration(SampleKafkaPropertiesConfiguration sampleKafkaPropertiesConfiguration, SampleKafkaErrorHandler sampleKafkaErrorHandler, Environment environment, KafkaMetricInterceptor recordInterceptor) {
        super(sampleKafkaPropertiesConfiguration, sampleKafkaErrorHandler, environment, recordInterceptor);
    }

}
```

6. Create a `SampleKafkaSubscriber` class and extend `BaseKafkaMonoSubscriber<String, DesiredDeserializationObject>` or `BaseKafkaPolySubscriber<String, DesiredDeserializationObject>`

7. Annotate `SampleKafkaSubscriber` class with `@KakfaSubscriber`
```
@KafkaSubscriber
@Slf4j
public class SampleKafkaSubscriber extends BaseKafkaMonoSubscriber<String, String> {

    public SampleKafkaSubscriber(ExecutorService executorService) {
        super(executorService);
    }

    @Override
    protected Runnable processMessage(ConsumerRecord<String, String> consumerRecord) {
        return () -> log.info("Message: "+ consumerRecord);
    }
}
```

8. Override the method `processMessage(ConsumerRecord<String, DesiredDeserializationObject> consumerRecord)` from the `SampleKafkaSubscriber` instance to provide a runnable to process the message.
```
@Override
    protected Runnable processMessage(ConsumerRecord<String, String> consumerRecord) {
        return () -> log.info("Message: "+ consumerRecord);
    }
```

9. Add the following properties to connect to the Kafka topic
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
bootstrap.servers=

# If schema registry is enabled
kafka.schema.registry.enabled=true
basic.auth.credentials.source=USER_INFO
basic.auth.user=
basic.auth.identification=
schema.registry.url=

# Properties required for subscriber.
kafka.partitions.count=
kafka.subscriber.topics=
kafka.subscriber.auto.startup=
kafka.subscriber.group.id=
```

#### Batch kafka subscriber
- To run the kafka subscriber in batch mode, enable the following property and use `BaseKafkaSingularSubscriber` and `KafkaBatchSubscriberErrorHandler`
```
kafka.subscriber.batch.enabled=true
```

#### Message filter
- To add filtering capability for kafka subscriber , enable the following property
```
kafka.subscriber.filter.enabled=true
```

- Create `SampleKafkaMessageFilter` and extend `KafkaMessageFilter<String, DesiredDeserializationObject>`. Provide predicate to filter messages by overriding the ``method.
```
@Configuration
public class SampleKafkaMessageFilter extends KafkaMessageFilter<String, DesiredDeserializationObject> {
    @Override
    protected boolean filterMessage(ConsumerRecord<String, DesiredDeserializationObject> consumerRecord) {
        // logic to filter out the message. 
        // return true to discard the message.
    }
}
```

- Pass `SampleKafkaMessageFilter` in `SampleSubscriberConfiguration` constructor.
```
@Configuration
public class SampleKafkaMessageFilter extends KafkaSubscriberConfiguration {

    protected SampleKafkaMessageFilter(KafkaPropertiesConfiguration kafkaPropertiesConfiguration, SubscriberErrorHandler subscriberErrorHandler, SampleKafkaMessageFilter filter) {
        super(kafkaPropertiesConfiguration, subscriberErrorHandler, filter);
    }

}
```

package io.americanexpress.synapse.subscriber.kafka.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * {@code KafkaSubscriber} annotation is used as annotate a class as a kafka subscriber.
 * This annotation serves as a specialization of @KafkaListener, allowing for implementation classes
 * to be auto-detected through classpath scanning.
 *
 * @author Krishna Kuchikulla
 */
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface KafkaSubscriber {

    /**
     * The value may indicate a suggestion for a logical component name to be turned into a Spring bean in case of an auto-detected component.
     *
     * @return the suggested component name, if any (or empty String otherwise)
     */
    @AliasFor(annotation = Component.class)
    String value() default "";
}

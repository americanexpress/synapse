package io.americanexpress.function.greeting.reactive.config;

import io.americanexpress.synapse.function.reactive.config.BaseObservabilityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class GreetingConfig extends BaseObservabilityConfig {
    public GreetingConfig(Environment environment) {
        super(environment);
    }
}

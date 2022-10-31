package io.americanexpress.function.greeting.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class Config {

    @Bean
    public Validator springValidator() {
        return new LocalValidatorFactoryBean();
    }
}

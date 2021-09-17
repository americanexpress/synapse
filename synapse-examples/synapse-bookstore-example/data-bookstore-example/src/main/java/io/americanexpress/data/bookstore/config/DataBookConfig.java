package io.americanexpress.data.bookstore.config;

import io.americanexpress.synapse.framework.test.model.ProfileConstants;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@PropertySource("classpath:/data-sample-application-${SPRING_PROFILES_ACTIVE}.properties")
@EnableJpaRepositories(basePackages = DataBookConfig.PACKAGE_NAME)
@EnableAutoConfiguration
@EnableJpaAuditing
//This is to be sure it is only loaded when 'Test' Profiles are off.
// All the Controller slice tests have the 'Test' profile ON them. Please check BaseController test which has this annotation. @ActiveProfiles(ProfileConstants.TEST)
@Profile(ProfileConstants.NON_TEST)
public class DataBookConfig {

    /**
     * The Package name.
     */
    static final String PACKAGE_NAME = "io.americanexpress.data.bookstore";
}

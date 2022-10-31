package io.americanexpress.service.book.rest.config;

import io.americanexpress.data.oracle.book.config.DataBookConfig;
import io.americanexpress.synapse.service.rest.config.ServiceRestConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:service-book-application.properties")
@ComponentScan(basePackages = "io.americanexpress.service.book.rest")
@Import({ServiceRestConfig.class, DataBookConfig.class})
public class BookConfig {
}

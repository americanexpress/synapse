package io.americanexpress.data.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * DataBookApplication is the Spring Boot main class to start the data application.
 */
@SpringBootApplication
@EnableConfigurationProperties
public class DataBookApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(DataBookApplication.class, args);
    }

}


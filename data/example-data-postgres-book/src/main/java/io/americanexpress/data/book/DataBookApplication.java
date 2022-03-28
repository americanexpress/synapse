package io.americanexpress.data.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class DataBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataBookApplication.class, args);
    }

}

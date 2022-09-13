package io.americanexpress.synapse.data.oracle.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("spring.oracle.datasource")
@Data
public class OracleProperties {

    private String username;
    private String password;
    private String driverType;
    private String url;
    private int portNumber;
}

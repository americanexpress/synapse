package io.americanexpress.sample.client.weather.config;

import io.americanexpress.sample.client.weather.client.WeatherClient;
import io.americanexpress.sample.client.weather.handler.WeatherResponseErrorHandler;
import io.americanexpress.synapse.client.rest.config.BaseRestClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;

@Configuration
@ComponentScan("io.americanexpress.sample.client.weather")
@Import(BaseRestClientConfig.class)
@PropertySource("classpath:client-weather.properties")
public class WeatherClientConfig extends BaseRestClientConfig {

    private final WeatherClient weatherClient;

    private final WeatherResponseErrorHandler weatherResponseErrorHandler;

    public WeatherClientConfig(WeatherClient weatherClient, WeatherResponseErrorHandler weatherResponseErrorHandler) {
        this.weatherClient = weatherClient;
        this.weatherResponseErrorHandler = weatherResponseErrorHandler;
    }

    @Value("${client.url}")
    @Override
    protected void initialize(String destinationUrl) {
        initializeClient(destinationUrl, weatherClient, weatherResponseErrorHandler);
    }
}

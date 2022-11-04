package io.americanexpress.sample.weather.config;

import io.americanexpress.sample.client.weather.config.WeatherClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(WeatherClientConfig.class)
public class WeatherClientTestConfig {
}

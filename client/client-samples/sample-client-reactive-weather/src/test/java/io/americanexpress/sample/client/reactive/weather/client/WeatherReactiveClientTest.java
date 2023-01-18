package io.americanexpress.sample.client.reactive.weather.client;

import io.americanexpress.sample.client.reactive.weather.config.WeatherReactiveClientTestConfig;
import io.americanexpress.sample.client.reactive.weather.model.WeatherRequest;
import io.americanexpress.sample.client.reactive.weather.model.WeatherResponse;
import io.americanexpress.synapse.client.test.client.BaseReactiveRestClientUnitTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;

/**
 * {@code WeatherReactiveClientTest} tests the {@link WeatherReactiveClient}.
 */
@ContextConfiguration(classes = WeatherReactiveClientTestConfig.class)
public class WeatherReactiveClientTest extends BaseReactiveRestClientUnitTest<WeatherRequest, WeatherResponse, WeatherReactiveClient> {

    @Override
    public WeatherRequest mockDefaultClientRequest() {
        return null;
    }

    @Override
    public WeatherResponse mockDefaultClientResponse() {
        return new WeatherResponse();
    }

    @Override
    public HttpHeaders mockClientHeaders() {
        return new HttpHeaders();
    }

    @Override
    public String mockPathVariables() {
        return null;
    }
}

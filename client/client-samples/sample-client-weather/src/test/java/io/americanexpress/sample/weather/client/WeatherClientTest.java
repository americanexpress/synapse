package io.americanexpress.sample.weather.client;

import io.americanexpress.sample.client.weather.client.WeatherClient;
import io.americanexpress.sample.client.weather.model.WeatherRequest;
import io.americanexpress.sample.client.weather.model.WeatherResponse;
import io.americanexpress.sample.weather.config.WeatherClientTestConfig;
import io.americanexpress.synapse.client.test.client.BaseRestClientUnitTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;

/**
 * {@code WeatherClientTest} tests the {@link WeatherClient}
 */
@ContextConfiguration(classes = WeatherClientTestConfig.class)
public class WeatherClientTest extends BaseRestClientUnitTest<WeatherRequest, WeatherResponse, WeatherClient> {

    /**
     * To provide a mock client request.
     */
    @Override
    protected WeatherRequest mockDefaultClientRequest() {
        return null;
    }

    /**
     * To provide a mock client response.
     */
    @Override
    protected WeatherResponse mockDefaultClientResponse() {
        WeatherResponse weatherResponse = new WeatherResponse();
        return weatherResponse;
    }

    /**
     * To provide mocked client http headers.
     */
    @Override
    protected HttpHeaders getDefaultClientHeaders() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}

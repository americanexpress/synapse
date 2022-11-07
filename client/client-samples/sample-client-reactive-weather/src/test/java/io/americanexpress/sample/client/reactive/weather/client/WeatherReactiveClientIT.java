package io.americanexpress.sample.client.reactive.weather.client;

import io.americanexpress.sample.client.reactive.weather.config.WeatherReactiveClientTestConfig;
import io.americanexpress.sample.client.reactive.weather.model.WeatherResponse;
import io.americanexpress.synapse.framework.test.CommonAssertionMessages;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Mono;

/**
 * {@code WeatherReactiveClientIT} tests the {@link WeatherReactiveClient}.
 */
@ContextConfiguration(classes = WeatherReactiveClientTestConfig.class)
class WeatherReactiveClientIT {

    /**
     * Weather client
     */
    private final WeatherReactiveClient client;

    public WeatherReactiveClientIT(WeatherReactiveClient client) {
        this.client = client;
    }

    @Test
    void callMonoService_givenValidRequest_expectedSuccessResponse() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Mono<WeatherResponse> response = client.callMonoService(headers, null);
        Assertions.assertNotNull(response.block(), CommonAssertionMessages.RESPONSE_IS_NULL);
    }
}

package io.americanexpress.sample.client.reactive.weather.client;

import io.americanexpress.sample.client.reactive.weather.config.WeatherReactiveClientTestConfig;
import io.americanexpress.sample.client.reactive.weather.model.WeatherResponse;
import io.americanexpress.synapse.framework.test.CommonAssertionMessages;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;

/**
 * {@code WeatherReactiveClientIT} tests the {@link WeatherReactiveClient}.
 */
@ContextConfiguration(classes = WeatherReactiveClientTestConfig.class)
@ExtendWith(SpringExtension.class)
class WeatherReactiveClientIT {

    /**
     * Weather client
     */
    @Autowired
    private WeatherReactiveClient client;

    @Test
    void callMonoService_givenValidRequest_expectedSuccessResponse() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Mono<WeatherResponse> response = client.callMonoService(headers, null);
        Assertions.assertNotNull(response.block(), CommonAssertionMessages.RESPONSE_IS_NULL);
    }
}

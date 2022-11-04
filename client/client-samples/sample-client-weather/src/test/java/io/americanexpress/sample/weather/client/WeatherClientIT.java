package io.americanexpress.sample.weather.client;

import io.americanexpress.sample.client.weather.client.WeatherClient;
import io.americanexpress.sample.client.weather.model.WeatherRequest;
import io.americanexpress.sample.client.weather.model.WeatherResponse;
import io.americanexpress.sample.weather.config.WeatherClientTestConfig;
import io.americanexpress.synapse.client.test.client.BaseRestClientIT;
import io.americanexpress.synapse.framework.test.CommonAssertionMessages;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = WeatherClientTestConfig.class)
class WeatherClientIT extends BaseRestClientIT<WeatherRequest, WeatherResponse, WeatherClient> {

    @Autowired
    private WeatherClient client;

    @Test
    @Override
    protected void callMonoService_givenValidRequest_expectedSuccessResponse() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        WeatherResponse response = client.callMonoService(headers, null);
        Assertions.assertNotNull(response, CommonAssertionMessages.RESPONSE_IS_NULL);
    }
}

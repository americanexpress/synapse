package io.americanexpress.sample.client.weather.client;

import io.americanexpress.sample.client.weather.model.WeatherRequest;
import io.americanexpress.sample.client.weather.model.WeatherResponse;
import io.americanexpress.synapse.client.rest.client.BaseRestClient;
import io.americanexpress.synapse.client.rest.client.RestClient;
import org.springframework.http.HttpMethod;

@RestClient
public class WeatherClient extends BaseRestClient<WeatherRequest, WeatherResponse> {

    /**
     * Argument constructor creates a new instance of BaseRestClient with given values.
     *
     */
    protected WeatherClient() {
        super(HttpMethod.GET);
    }

}

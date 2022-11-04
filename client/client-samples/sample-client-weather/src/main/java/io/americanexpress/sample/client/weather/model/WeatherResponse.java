package io.americanexpress.sample.client.weather.model;

import io.americanexpress.synapse.client.rest.model.BaseClientResponse;

/**
 * The type Weather response.
 */
public class WeatherResponse implements BaseClientResponse {

    private String latitude;

    private String longitude;

    private String elevation;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }
}

/*
 * Copyright 2020 American Express Travel Related Services Company, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.americanexpress.sample.client.reactive.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.americanexpress.synapse.client.rest.model.BaseClientResponse;

/**
 * {@code WeatherResponse} is the response object for weather client.
 */
public class WeatherResponse implements BaseClientResponse {

    /**
     * The latitude
     */
    private String latitude;

    /**
     * The longitude
     */
    private String longitude;

    /**
     * The elevation
     */
    private String elevation;

    /**
     * The current weather
     */
    @JsonProperty("current_weather")
    private CurrentWeather currentWeather;

    /**
     * Gets latitude.
     *
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * Sets latitude.
     *
     * @param latitude the latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets longitude.
     *
     * @return the longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * Sets longitude.
     *
     * @param longitude the longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets elevation.
     *
     * @return the elevation
     */
    public String getElevation() {
        return elevation;
    }

    /**
     * Sets elevation.
     *
     * @param elevation the elevation
     */
    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    /**
     * Gets current weather.
     *
     * @return the current weather
     */
    public CurrentWeather getCurrentWeather() {
        return currentWeather;
    }

    /**
     * Sets current weather.
     *
     * @param currentWeather the current weather
     */
    public void setCurrentWeather(CurrentWeather currentWeather) {
        this.currentWeather = currentWeather;
    }
}

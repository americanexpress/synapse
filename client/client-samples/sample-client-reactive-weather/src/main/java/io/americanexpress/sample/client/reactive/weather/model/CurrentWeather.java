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

import java.time.LocalDateTime;

/**
 * {@code CurrentWeather} represents current weather.
 */
public class CurrentWeather {

    /**
     * The temperature
     */
    private double temperature;

    /**
     * The wind speed
     */
    private double windSpeed;

    /**
     * The wind direction
     */
    private double windDirection;

    /**
     * The weather code - WMO weather code https://open-meteo.com/en/docs
     */
    private int weatherCode;

    /**
     * Gets temperature.
     *
     * @return the temperature
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * Sets temperature.
     *
     * @param temperature the temperature
     */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    /**
     * Gets wind speed.
     *
     * @return the wind speed
     */
    public double getWindSpeed() {
        return windSpeed;
    }

    /**
     * Sets wind speed.
     *
     * @param windSpeed the wind speed
     */
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    /**
     * Gets wind direction.
     *
     * @return the wind direction
     */
    public double getWindDirection() {
        return windDirection;
    }

    /**
     * Sets wind direction.
     *
     * @param windDirection the wind direction
     */
    public void setWindDirection(double windDirection) {
        this.windDirection = windDirection;
    }

    /**
     * Gets weather code.
     *
     * @return the weather code
     */
    public int getWeatherCode() {
        return weatherCode;
    }

    /**
     * Sets weather code.
     *
     * @param weatherCode the weather code
     */
    public void setWeatherCode(int weatherCode) {
        this.weatherCode = weatherCode;
    }

    /**
     * Gets time.
     *
     * @return the time
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * Sets time.
     *
     * @param time the time
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    private LocalDateTime time;
}

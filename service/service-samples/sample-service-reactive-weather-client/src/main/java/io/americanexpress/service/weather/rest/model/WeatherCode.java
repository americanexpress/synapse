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
package io.americanexpress.service.weather.rest.model;

/**
 * The enum Weather code.
 */
public enum WeatherCode {

    /**
     * Clear sky weather code.
     */
    CLEAR_SKY(0),
    /**
     * Mainly clear weather code.
     */
    MAINLY_CLEAR(1),
    /**
     * Partly cloudy weather code.
     */
    PARTLY_CLOUDY(2),
    /**
     * Overcast weather code.
     */
    OVERCAST(3),
    /**
     * Fog weather code.
     */
    FOG(45),
    /**
     * Rime fog weather code.
     */
    RIME_FOG(48),
    /**
     * Light drizzle weather code.
     */
    LIGHT_DRIZZLE(51),
    /**
     * Moderate drizzle weather code.
     */
    MODERATE_DRIZZLE(53),
    /**
     * Dense drizzle weather code.
     */
    DENSE_DRIZZLE(55),
    /**
     * Light freezing drizzle weather code.
     */
    LIGHT_FREEZING_DRIZZLE(56),
    /**
     * Dense freezing drizzle weather code.
     */
    DENSE_FREEZING_DRIZZLE(57),
    /**
     * Slight rain weather code.
     */
    SLIGHT_RAIN(61),
    /**
     * Moderate rain weather code.
     */
    MODERATE_RAIN(63),
    /**
     * Heavy rain weather code.
     */
    HEAVY_RAIN(65),
    /**
     * Light freezing rain weather code.
     */
    LIGHT_FREEZING_RAIN(66),
    /**
     * Heavy freezing rain weather code.
     */
    HEAVY_FREEZING_RAIN(67),
    /**
     * Slight snow weather code.
     */
    SLIGHT_SNOW(71),
    /**
     * Moderate snow weather code.
     */
    MODERATE_SNOW(73),
    /**
     * Heavy snow weather code.
     */
    HEAVY_SNOW(75),
    /**
     * Slight rain shower weather code.
     */
    SLIGHT_RAIN_SHOWER(80),
    /**
     * Moderate rain shower weather code.
     */
    MODERATE_RAIN_SHOWER(81),
    /**
     * Violent rain shower weather code.
     */
    VIOLENT_RAIN_SHOWER(82),
    /**
     * Slight snow shower weather code.
     */
    SLIGHT_SNOW_SHOWER(85),
    /**
     * Heavy snow shower weather code.
     */
    HEAVY_SNOW_SHOWER(86),
    /**
     * Slight thunderstorm weather code.
     */
    SLIGHT_THUNDERSTORM(95),
    /**
     * Thunderstrom slight hail weather code.
     */
    THUNDERSTROM_SLIGHT_HAIL(96);

    private int value;

    WeatherCode(int value) {this.value = value;}

    /**
     * Gets weather code.
     *
     * @param value the value
     * @return the weather code
     */
    public static WeatherCode getWeatherCode(int value) {
        for(WeatherCode weatherCode: values()){
            if(weatherCode.value == value) {
                return weatherCode;
            }
        }
        return null;
    }

}

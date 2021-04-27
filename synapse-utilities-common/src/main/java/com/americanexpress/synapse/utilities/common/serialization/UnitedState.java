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
package com.americanexpress.synapse.utilities.common.serialization;

import java.util.HashMap;
import java.util.Map;

/**
 * UnitedState enum has the full set of United States and their abbreviations.
 *
 * @author Paolo Claudio
 */
public enum UnitedState {

    ALABAMA("ALABAMA", "AL"), ALASKA("ALASKA", "AK"), AMERICAN_SAMOA("AMERICAN SAMOA", "AS"), ARIZONA("ARIZONA", "AZ"), ARKANSAS(
            "ARKANSAS", "AR"), CALIFORNIA("CALIFORNIA", "CA"), COLORADO("COLORADO", "CO"), CONNECTICUT("CONNECTICUT", "CT"), DELAWARE(
            "DELAWARE", "DE"), DISTRICT_OF_COLUMBIA("DISTRICT OF COLUMBIA", "DC"), FEDERATED_STATES_OF_MICRONESIA(
            "FEDERATED STATES OF MICRONESIA", "FM"), FLORIDA("FLORIDA", "FL"), GEORGIA("GEORGIA", "GA"), GUAM("GUAM", "GU"), HAWAII(
            "HAWAII", "HI"), IDAHO("IDAHO", "ID"), ILLINOIS("ILLINOIS", "IL"), INDIANA("INDIANA", "IN"), IOWA("IOWA", "IA"), KANSAS(
            "KANSAS", "KS"), KENTUCKY("KENTUCKY", "KY"), LOUISIANA("LOUISIANA", "LA"), MAINE("MAINE", "ME"), MARYLAND("MARYLAND", "MD"), MARSHALL_ISLANDS(
            "MARSHALL ISLANDS", "MH"), MASSACHUSETTS("MASSACHUSETTS", "MA"), MICHIGAN("MICHIGAN", "MI"), MINNESOTA("MINNESOTA", "MN"), MISSISSIPPI(
            "MISSISSIPPI", "MS"), MISSOURI("MISSOURI", "MO"), MONTANA("MONTANA", "MT"), NEBRASKA("NEBRASKA", "NE"), NEVADA("NEVADA",
            "NV"), NEW_HAMPSHIRE("NEW HAMPSHIRE", "NH"), NEW_JERSEY("NEW JERSEY", "NJ"), NEW_MEXICO("NEW MEXICO", "NM"), NEW_YORK(
            "NEW YORK", "NY"), NORTH_CAROLINA("NORTH CAROLINA", "NC"), NORTH_DAKOTA("NORTH DAKOTA", "ND"), NORTHERN_MARIANA_ISLANDS(
            "NORTHERN MARIANA ISLANDS", "MP"), OHIO("OHIO", "OH"), OKLAHOMA("OKLAHOMA", "OK"), OREGON("OREGON", "OR"), PALAU("PALAU",
            "PW"), PENNSYLVANIA("PENNSYLVANIA", "PA"), PUERTO_RICO("PUERTO RICO", "PR"), RHODE_ISLAND("RHODE ISLAND", "RI"), SOUTH_CAROLINA(
            "SOUTH CAROLINA", "SC"), SOUTH_DAKOTA("SOUTH DAKOTA", "SD"), TENNESSEE("TENNESSEE", "TN"), TEXAS("TEXAS", "TX"), UTAH(
            "UTAH", "UT"), VERMONT("VERMONT", "VT"), VIRGIN_ISLANDS("VIRGIN ISLANDS", "VI"), VIRGINIA("VIRGINIA", "VA"), WASHINGTON(
            "WASHINGTON", "WA"), WEST_VIRGINIA("WEST VIRGINIA", "WV"), WISCONSIN("WISCONSIN", "WI"), WYOMING("WYOMING", "WY");

    /**
     * Map used during lookup.
     */
    private static final Map<String, String> UNITED_STATES = new HashMap<>();

    /**
     * Set the state name to abbreviation mapping.
     */
    static {
        for (UnitedState state : values()) {
            UNITED_STATES.put(state.getName(), state.getAbbreviation());
        }
    }

    /**
     * Fully qualified United State name e.g. Florida
     */
    private String name;

    /**
     * Abbreviation of the United State name e.g. FL
     */
    private String abbreviation;

    /**
     * Argument constructor creates a new instance of UnitedState with given values.
     *
     * @param name         fully qualified United State name e.g. Florida
     * @param abbreviation of the United State name e.g. FL
     */
    UnitedState(String name, String abbreviation) {
        setName(name);
        setAbbreviation(abbreviation);
    }

    /**
     * Get the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name.
     *
     * @param name the name to set
     */
    private void setName(String name) {
        this.name = name;
    }

    /**
     * Get the abbreviation.
     *
     * @return the abbreviation
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * Set the abbreviation.
     *
     * @param abbreviation the abbreviation to set
     */
    private void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    /**
     * Get the abbreviation for this United State.
     *
     * @param name fully qualified United State name e.g. Florida
     * @return the abbreviation for this United State name e.g. FL
     */
    public static String getAbbreviation(String name) {

        // Check to see if this name exists and if so, return the abbreviation
        String unitedStateName = name.toUpperCase().trim();
        if (UNITED_STATES.containsKey(unitedStateName)) {
            return UNITED_STATES.get(unitedStateName);
        }

        // Name not found; return the name AS IS
        return name;
    }
}

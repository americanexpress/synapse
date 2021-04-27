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

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitedStatesAbbreviationStringSerializerTest extends BaseTestStringSerializer {

    private static String[] unitedStateNames;
    private static String[] unitedStateAbbreviations;

    @BeforeEach
    @Override
    public void initializeModel() {
        super.initializeModel();
        setTestField("united_state");
    }

    @Override
    public void arrangeNullField() {
        model.setUnitedState(null);
    }

    @Override
    public void arrangeEmptyValue() {
        model.setUnitedState("");
    }

    @Override
    public void arrangeWhiteSpace() {
        model.setUnitedState(" ");
    }

    @BeforeAll
    public static void initialize() {
        unitedStateNames = new String[]{"Alabama", "Alaska", "American Samoa", "Arizona", "Arkansas", "California", "Colorado",
                "Connecticut", "Delaware", "District of Columbia", "Federated States of Micronesia", "Florida", "Georgia", "Guam",
                "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Marshall Islands",
                "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire",
                "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Northern Mariana Islands", "Ohio", "Oklahoma",
                "Oregon", "Palau", "Pennsylvania", "Puerto Rico", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas",
                "Utah", "Vermont", "Virgin Islands", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"};

        unitedStateAbbreviations = new String[]{"AL", "AK", "AS", "AZ", "AR", "CA", "CO", "CT", "DE", "DC", "FM", "FL", "GA", "GU",
                "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MH", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH",
                "NJ", "NM", "NY", "NC", "ND", "MP", "OH", "OK", "OR", "PW", "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VI",
                "VA", "WA", "WV", "WI", "WY"};
    }

    @Test
    @Override
    public void serialize_clean() throws Exception {
        model.setUnitedState("Florida");
        String expected = "{\"" + testField + "\":\"FL\"}";
        String actual = mapper.writeValueAsString(model);
        assertEquals(expected, actual, SERIALIZATION_FAILED);
    }

    @Test
    public void serialize_allUnitedStates() throws Exception {
        String expected;
        String actual;
        for (int i = 0; i < unitedStateNames.length; i++) {
            model.setUnitedState(unitedStateNames[i]);
            expected = "{\"" + testField + "\":\"" + unitedStateAbbreviations[i] + "\"}";
            actual = mapper.writeValueAsString(model);
            assertEquals(expected, actual, SERIALIZATION_FAILED);
        }
    }
}

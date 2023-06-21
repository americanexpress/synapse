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
package io.americanexpress.synapse.utilities.common.serialization;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * {@code UnitedStateTest} tests the {@link UnitedState}.
 */
class UnitedStateTest {

    @Test
    void unitedState_values() {
        UnitedState.values();
    }

    @Test
    void unitedState_valueOf() {
        UnitedState expected = UnitedState.FLORIDA;
        UnitedState actual = UnitedState.valueOf("FLORIDA");
        assertEquals(expected, actual, "Expected FLORIDA.");
    }

    @Test
    void getName_clean() {
        String expected = "FLORIDA";
        String actual = UnitedState.FLORIDA.getName();
        assertEquals(expected, actual, "Expected Florida.");
    }

    @Test
    void getAbbreviation_clean() {
        String expected = "FL";
        String actual = UnitedState.FLORIDA.getAbbreviation();
        assertEquals(expected, actual, "Expected FL.");
    }

    @Test
    void getAbbreviation_null() {
        assertThrows(NullPointerException.class, () -> UnitedState.getAbbreviation(null));
    }

    @Test
    void getAbbreviation_emptyString() {
        String expected = "";
        String actual = UnitedState.getAbbreviation("");
        assertEquals(expected, actual, "Expected empty string.");
    }

    @Test
    void getAbbreviation_whiteSpace() {
        String expected = " ";
        String actual = UnitedState.getAbbreviation(" ");
        assertEquals(expected, actual, "Expected empty string.");
    }

    @Test
    void getAbbreviation_ignoreCase() {
        String expected = "FL";
        String actual = UnitedState.getAbbreviation("fLOriDA");
        assertEquals(expected, actual, "Expected FL.");
    }

    @Test
    void getAbbreviation_pascalCase() {
        String expected = "FL";
        String actual = UnitedState.getAbbreviation("Florida");
        assertEquals(expected, actual, "Expected FL.");
    }

    @Test
    void getAbbreviation_ignoreCaseUntrimmed() {
        String expected = "FL";
        String actual = UnitedState.getAbbreviation(" fLOriDA ");
        assertEquals(expected, actual, "Expected FL.");
    }

    @Test
    void getAbbreviation_pascalCaseUntrimmed() {
        String expected = "FL";
        String actual = UnitedState.getAbbreviation(" Florida ");
        assertEquals(expected, actual, "Expected FL.");
    }

    @Test
    void getAbbreviation_unmapped() {
        String expected = "not found";
        String actual = UnitedState.getAbbreviation("not found");
        assertEquals(expected, actual, "Expected not found.");
    }

    @Test
    void getAbbreviation_unmappedUntrimmed() {
        String expected = " not found ";
        String actual = UnitedState.getAbbreviation(" not found ");
        assertEquals(expected, actual, "Expected not found.");
    }

    @Test
    void getAbbreviation_clean_string() {
        String expected = "FL";
        String actual = UnitedState.getAbbreviation("FLORIDA");
        assertEquals(expected, actual, "Expected FL.");
    }
}

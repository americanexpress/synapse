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
package io.americanexpress.synapse.utilities.common.miscellaneous;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@code StringCommonUtilsTest} tests the {@link StringCommonUtils}.
 */
class StringCommonUtilsTest {

    private static final String TEST_VALUE = "The  @@@@@@\\\\  .:;!Test----Value@%;";
    private static final String BLANK_TEST_VALUE = "";

    @Test
    void removeSpecialCharacters_givenDataContainingSpecialCharacters_expectedSpecialCharactersRemovalSuccess() {
        assertEquals("The    Test----Value", StringCommonUtils.removeSpecialCharacters(TEST_VALUE));
        assertEquals(BLANK_TEST_VALUE, StringCommonUtils.removeSpecialCharacters(BLANK_TEST_VALUE));
    }

    @Test
    void convertSpaceAndDashToUnderscore_givenDataContainingSpacesAndDashes_expectedSpaceAndDashesToUnderscoreSuccess() {
        assertEquals("The_@@@@@@\\\\_.:;!Test_Value@%;", StringCommonUtils.convertSpaceAndDashToUnderscore(TEST_VALUE));
        assertEquals(BLANK_TEST_VALUE, StringCommonUtils.convertSpaceAndDashToUnderscore(BLANK_TEST_VALUE));
    }

    @Test
    void enumerate_givenNonEnumerateString_expectedEnumerificationSuccess() {
        assertEquals("THE_TEST_VALUE", StringCommonUtils.enumerate(TEST_VALUE));
        assertEquals(BLANK_TEST_VALUE, StringCommonUtils.enumerate(BLANK_TEST_VALUE));
    }

    @Test
    void convertSnakeCaseToPascalCase_givenSnakeCaseString_expectPascalCase() {
        assertEquals("TheTestValue", StringCommonUtils.convertSnakeCaseToPascalCase("the_test_value"));
        assertEquals(BLANK_TEST_VALUE, StringCommonUtils.convertSnakeCaseToPascalCase(BLANK_TEST_VALUE));
    }

    @Test
    void convertSnakeCaseToPascalCase_givenStringWithConsecutiveUnderscores_expectPascalCase() {
        assertEquals("TheTestValue", StringCommonUtils.convertSnakeCaseToPascalCase("the__test_value"));
    }

    @Test
    void convertSnakeCaseToPascalCase_givenStringAllCaps_expectPascalCase() {
        assertEquals("TheTestValue", StringCommonUtils.convertSnakeCaseToPascalCase("THE_TEST_VALUE"));
    }

    @Test
    void checkForNullString_givenNullValueString_expectFalse(){
        assertFalse(StringCommonUtils.checkForNullString("null"));
    }

    @Test
    void checkForNullString_givenNullString_expectFalse(){
        assertFalse(StringCommonUtils.checkForNullString(null));
    }

    @Test
    void checkForNullString_givenValidString_expectTrue(){
        assertTrue(StringCommonUtils.checkForNullString("test"));
    }
}


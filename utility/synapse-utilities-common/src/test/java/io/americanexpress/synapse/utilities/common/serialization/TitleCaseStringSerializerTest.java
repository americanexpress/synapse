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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@code TitleCaseStringSerializerTest} tests the {@link TitleCaseStringSerializer}.
 */
class TitleCaseStringSerializerTest extends BaseTestStringSerializer {

    private static final String NAMES_NOT_FORMATTED_CORRECTLY = "Names are not formatted correctly.";

    TitleCaseStringSerializer titleCaseStringSerializer = new TitleCaseStringSerializer();

    @BeforeEach
    @Override
    public void initializeModel() {
        super.initializeModel();
        setTestField("fullName");
    }

    @Override
    public void arrangeNullField() {
        model.setFullName(null);
    }

    @Override
    public void arrangeEmptyValue() {
        model.setFullName("");
    }

    @Override
    public void arrangeWhiteSpace() {
        model.setFullName(" ");
    }

    @Test
    void serialize_allLowercase() {
        String text = "john x doe";
        String expected = "John X Doe";
        String actual = titleCaseStringSerializer.serialize(text);
        assertEquals(expected, actual, NAMES_NOT_FORMATTED_CORRECTLY);
    }

    @Test
    void serialize_whiteSpacesWithinString() {
        String text = "JOHN   X   DOE";
        String expected = "John X Doe";
        String actual = titleCaseStringSerializer.serialize(text);
        assertEquals(expected, actual, NAMES_NOT_FORMATTED_CORRECTLY);
    }

    @Test
    void serialize_untrimmed() {
        String text = "   JOHN X DOE   ";
        String expected = "John X Doe";
        String actual = titleCaseStringSerializer.serialize(text);
        assertEquals(expected, actual, NAMES_NOT_FORMATTED_CORRECTLY);
    }

    @Test
    void serialize_correctlyFormattedAlready() {
        String text = "John X Doe";
        String expected = "John X Doe";
        String actual = titleCaseStringSerializer.serialize(text);
        assertEquals(expected, actual, NAMES_NOT_FORMATTED_CORRECTLY);
    }

    @Test
    void serialize_empty() {
        String text = "";
        String expected = "";
        String actual = titleCaseStringSerializer.serialize(text);
        assertEquals(expected, actual, NAMES_NOT_FORMATTED_CORRECTLY);
    }

    @Test
    void serialize_null() {
        assertNull(titleCaseStringSerializer.serialize(null));
    }

    @Test
    void serialize_singleName() {
        String text = "a";
        String expected = "A";
        String actual = titleCaseStringSerializer.serialize(text);
        assertEquals(expected, actual, NAMES_NOT_FORMATTED_CORRECTLY);
    }

    @Test
    void serialize_twoNames() {
        String text = "a b";
        String expected = "A B";
        String actual = titleCaseStringSerializer.serialize(text);
        assertEquals(expected, actual, NAMES_NOT_FORMATTED_CORRECTLY);
    }

    @Test
    void serialize_singleLetterNames() {
        String text = "a b c d e f g";
        String expected = "A B C D E F G";
        String actual = titleCaseStringSerializer.serialize(text);
        assertEquals(expected, actual, NAMES_NOT_FORMATTED_CORRECTLY);
    }

    @Test
    void serialize_hyphenatedName() {
        String text = "anne-marie";
        String expected = "Anne-Marie";
        String actual = titleCaseStringSerializer.serialize(text);
        assertEquals(expected, actual, NAMES_NOT_FORMATTED_CORRECTLY);
    }

    @Test
    void serialize_hyphenatedNameWithSpacesWithin() {
        String text = "anne-marie    jones-smith";
        String expected = "Anne-Marie Jones-Smith";
        String actual = titleCaseStringSerializer.serialize(text);
        assertEquals(expected, actual, NAMES_NOT_FORMATTED_CORRECTLY);
    }

    @Test
    void serialize_hyphenatedNameUntrimmed() {
        String text = "   anne-marie    jones-smith   ";
        String expected = "Anne-Marie Jones-Smith";
        String actual = titleCaseStringSerializer.serialize(text);
        assertEquals(expected, actual, NAMES_NOT_FORMATTED_CORRECTLY);
    }

    @Test
    void serialize_nameWithApostrophe() {
        String text = "PATRICK O'REILLY";
        String expected = "Patrick O'reilly";
        String actual = titleCaseStringSerializer.serialize(text);
        assertEquals(expected, actual, NAMES_NOT_FORMATTED_CORRECTLY);
    }

    @Test
    void serialize_nameWithApostropheWithSpacesWithin() {
        String text = "PATRICK   O'REILLY";
        String expected = "Patrick O'reilly";
        String actual = titleCaseStringSerializer.serialize(text);
        assertEquals(expected, actual, NAMES_NOT_FORMATTED_CORRECTLY);
    }

    @Test
    void serialize_nameWithApostropheUntrimmed() {
        String text = "   PATRICK O'REILLY   ";
        String expected = "Patrick O'reilly";
        String actual = titleCaseStringSerializer.serialize(text);
        assertEquals(expected, actual, NAMES_NOT_FORMATTED_CORRECTLY);
    }

    @Test
    void serialize_nameWithHyphensApostrophesSpacesWithinUntrimmed() {
        String text = "   ANNE-MARIE   O'REILLY  ";
        String expected = "Anne-Marie O'reilly";
        String actual = titleCaseStringSerializer.serialize(text);
        assertEquals(expected, actual, NAMES_NOT_FORMATTED_CORRECTLY);
    }

    @Test
    void serialize_nameWithPeriods() {
        String text = "A.K. COWLING";
        String expected = "A.K. Cowling";
        String actual = titleCaseStringSerializer.serialize(text);
        assertEquals(expected, actual, NAMES_NOT_FORMATTED_CORRECTLY);
    }

    @Test
    void serialize_nameWithPeriodsWithSpacesWithin() {
        String text = "A.K.   COWLING";
        String expected = "A.K. Cowling";
        String actual = titleCaseStringSerializer.serialize(text);
        assertEquals(expected, actual, NAMES_NOT_FORMATTED_CORRECTLY);
    }

    @Test
    void serialize_nameWithPeriodsUntrimmed() {
        String text = "   A.K. COWLING   ";
        String expected = "A.K. Cowling";
        String actual = titleCaseStringSerializer.serialize(text);
        assertEquals(expected, actual, NAMES_NOT_FORMATTED_CORRECTLY);
    }

    @Test
    void serialize_nameWithHyphensApostrophesPeriodsSpacesWithinUntrimmed() {
        String text = "   ANNE-MARIE  J.K.  O'REILLY  ";
        String expected = "Anne-Marie J.K. O'reilly";
        String actual = titleCaseStringSerializer.serialize(text);
        assertEquals(expected, actual, NAMES_NOT_FORMATTED_CORRECTLY);
    }

    @Test
    @Override
    public void serialize_clean() {
        String text = "JOHN X DOE";
        String expected = "John X Doe";
        String actual = titleCaseStringSerializer.serialize(text);
        assertEquals(expected, actual, NAMES_NOT_FORMATTED_CORRECTLY);
    }

    @Test
    void serialize_clean_objectMapper() throws Exception {
        model.setFullName("JOE A BLOGGS");
        String expected = "{\"" + testField + "\":\"Joe A Bloggs\"}";
        String actual = mapper.writeValueAsString(model);
        assertEquals(expected, actual, SERIALIZATION_FAILED);
    }
}

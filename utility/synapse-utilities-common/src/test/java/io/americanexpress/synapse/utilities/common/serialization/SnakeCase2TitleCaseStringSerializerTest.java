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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SnakeCase2TitleCaseStringSerializerTest extends BaseTestStringSerializer {

    SnakeCase2TitleCaseStringSerializer snakeCase2TitleCaseStringSerializer = new SnakeCase2TitleCaseStringSerializer();

    @BeforeEach
    @Override
    public void initializeModel() {
        super.initializeModel();
        setTestField("full_name");
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
    public void serialize_allLowercase() {
        String text = "john _x_ _ doe";
        String expected = "John X Doe";
        String actual = snakeCase2TitleCaseStringSerializer.serialize(text);
        assertEquals(expected, actual, "Names are not formatted correctly.");
    }

    @Override
    public void serialize_clean() {
        String text = "JOHN_X_DOE";
        String expected = "John X Doe";
        String actual = snakeCase2TitleCaseStringSerializer.serialize(text);
        assertEquals(expected, actual, "Names are not formatted correctly.");
    }
}

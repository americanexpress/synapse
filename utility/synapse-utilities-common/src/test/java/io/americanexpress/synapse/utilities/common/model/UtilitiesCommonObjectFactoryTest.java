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
package io.americanexpress.synapse.utilities.common.model;

import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilitiesCommonObjectFactoryTest {

    @Test
    void create_givenValidJSONInput_expectedDeserializedGenerated() throws IOException {
        SampleDeserializedObject sampleDeserializedObject = UtilitiesCommonObjectFactory.DEFAULT_CLASSPATH_OBJECT_FACTORY.create("sample-serialized-object.json", SampleDeserializedObject.class);
        assertAll("Default object factory unit test failed deserializing.",
                () -> assertNotNull(sampleDeserializedObject),
                () -> assertEquals("Stephen Strange", sampleDeserializedObject.getSomeName())
        );
    }

}
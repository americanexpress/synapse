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
package io.americanexpress.synapse.utilities.common.io;

import io.americanexpress.synapse.framework.exception.ApplicationServerException;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * {@code PropertiesManagerTest} tests the {@link PropertiesManager}.
 */
class PropertiesManagerTest {

    private static final String PROPERTIES_ARE_NULL = "Properties are null.";

    private static final String NONEXISTENT_FILE = "nonexistent file";

    private static final String EMPTY_FILE = "sample-empty-file.json";

    private static final String FILE_INVALID_FORMAT = "sample-wrong-format.txt";

    private static final String SAMPLE_PROPERTIES_FILE = "sample-properties.properties";

    @Test
    void load_nonexistentFile() {
        PropertiesManager manager = new PropertiesManager();
        assertThrows(ApplicationServerException.class, () -> manager.load(NONEXISTENT_FILE));
    }

    @Test
    void load_emptyFile() throws Exception {
        PropertiesManager manager = new PropertiesManager();
        Properties actual = manager.load(EMPTY_FILE);
        assertNotNull(actual, PROPERTIES_ARE_NULL);
    }

    @Test
    void load_fileInvalidFormat() throws Exception {
        PropertiesManager manager = new PropertiesManager();
        Properties actual = manager.load(FILE_INVALID_FORMAT);
        assertNotNull(actual, PROPERTIES_ARE_NULL);
    }

    @Test
    void load_clean() throws Exception {
        PropertiesManager manager = new PropertiesManager();
        Properties actual = manager.load(SAMPLE_PROPERTIES_FILE);
        assertNotNull(actual, PROPERTIES_ARE_NULL);
    }
}

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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * {@code IOUtilsTest} tests the {@link IOUtils}.
 */
class IOUtilsTest {

    @Test
    void getStringsFromFile_nullFileName() {
        assertThrows(IllegalArgumentException.class, () -> IOUtils.getStringsFromFile(null));
    }

    @Test
    void getStringsFromFile_nonexistentFile() {
        assertThrows(ApplicationServerException.class, () -> IOUtils.getStringsFromFile("nonexistent"));
    }

    @Test
    void getStringsFromFile_clean() {
        List<String> actual = IOUtils.getStringsFromFile("sample-body.json");
        assertNotNull(actual, "List is null.");
    }
}

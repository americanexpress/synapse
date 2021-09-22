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

import io.americanexpress.synapse.utilities.common.config.UtilitiesCommonConfig;
import io.americanexpress.synapse.utilities.common.serialization.model.Phone;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UtilitiesCommonConfig.class})
public class ClasspathObjectFactoryTest {

    @Autowired
    private ClasspathObjectFactory classpathObjectFactory;

    @Test
    public void classpathObjectFactory_constructor() throws Exception {
        Constructor<ClasspathObjectFactory> constructor = ClasspathObjectFactory.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        constructor.newInstance();
        constructor.setAccessible(false);
    }

    @Test
    public void create_nullArguments() {
       assertThrows(IllegalArgumentException.class, () -> classpathObjectFactory.create(null, null));
    }

    @Test
    public void create_nullPath() {
        assertThrows(IllegalArgumentException.class, () -> classpathObjectFactory.create(null, Phone.class));
    }

    @Test
    public void create_nullClassType() {
        assertThrows(IllegalArgumentException.class, () -> classpathObjectFactory.create("sample-empty-file.json", null));
    }

    @Test
    public void create_fileNotFound() {
        assertThrows(FileNotFoundException.class, () -> classpathObjectFactory.create("nonexistentFile", Phone.class));
    }

    @Test
    public void create_emptyFile() {
        assertThrows(MismatchedInputException.class, () -> classpathObjectFactory.create("sample-empty-file.json", Phone.class));
    }

    @Test
    public void create_emptyJson() throws Exception{
        classpathObjectFactory.create("sample-empty-json.json", Phone.class);
    }

    @Test
    public void create_malformedJson() {
        assertThrows(JsonEOFException.class, () -> classpathObjectFactory.create("sample-malformed-json.json", Phone.class));
    }

    @Test
    public void create_wrongFileFormat() {
        assertThrows(JsonParseException.class, () -> classpathObjectFactory.create("sample-wrong-format.txt", Phone.class));
    }

    @Test
    public void create_clean() throws Exception {
        Phone actual = (Phone) classpathObjectFactory.create("sample-body.json", Phone.class);
        assertNotNull(actual, "Unmarshal of file failed.");
    }

    @Test
    public void create_clean_string() throws Exception {
        classpathObjectFactory.create("sample-body.json");
    }
}

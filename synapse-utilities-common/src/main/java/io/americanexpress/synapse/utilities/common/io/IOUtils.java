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
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * IOUtils is used for IO operations.
 */
public class IOUtils {

    /**
     * Read a file and save it content into a String.
     *
     * @param fileName any file name along the class path
     * @return the file content into a String value.
     */
    public static String readFileToAString(String fileName) throws Exception {
        File file = new File(ClassLoader.getSystemResource(fileName).toURI());
        return FileUtils.readFileToString(file, Charset.defaultCharset());

    }

    /**
     * Get strings from a file as a list.
     *
     * @param fileName any file name along the class path
     * @return the strings from a file as a list
     */
    public static List<String> getStringsFromFile(String fileName) {

        ClassPathResource resource = new ClassPathResource(fileName);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } catch (IOException exception) {
            //This one will be handled by the handlesApplicationException method first if, when cause != null, since cause is the exception that we pass here.
            throw new ApplicationServerException(exception);
        }
    }

}

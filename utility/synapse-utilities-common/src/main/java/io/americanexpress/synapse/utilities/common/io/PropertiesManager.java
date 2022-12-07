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
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * {@code PropertiesManager} class is used to manage properties whenever we need to point a lower environment to a higher environment.
 * This occurs whenever a backend API has an unstable E1 environment and we need to point our own E1 environment to the backend E2 environment.
 *
 * @author Paolo Claudio
 */
public class PropertiesManager {

    /**
     * Logger for the controller.
     */
    protected final XLogger logger = XLoggerFactory.getXLogger(this.getClass());

    /**
     * Load properties from a given file path.
     *
     * @param fileName any file name along the class path
     * @return the properties found from the given file path
     */
    public Properties load(String fileName) {
        ClassPathResource resource = new ClassPathResource(fileName);
        InputStream inputStream;
        try {
            inputStream = resource.getInputStream();
        } catch (IOException exception) {
            throw new ApplicationServerException(exception);
        }

        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new ApplicationServerException(e);
        }
        return properties;
    }
}

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

import io.americanexpress.synapse.utilities.common.config.UtilitiesCommonConfig;
import io.americanexpress.synapse.utilities.common.io.ClasspathObjectFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class UtilitiesCommonObjectFactory {

    private static final UtilitiesCommonConfig UTILITIES_COMMON_CONFIG = new UtilitiesCommonConfig();

    public static final ObjectMapper DEFAULT_OBJECT_MAPPER = defaultObjectMapper();

    public static final ObjectMapper CAMEL_CASE_OBJECT_MAPPER = camelCaseObjectMapper();

    public static final ObjectMapper INCLUDE_EMPTY_OBJECT_MAPPER = includeEmptyObjectMapper();

    public static final ObjectMapper XML_OBJECT_MAPPER = xmlObjectMapper();

    public static final ClasspathObjectFactory DEFAULT_CLASSPATH_OBJECT_FACTORY = defaultClasspathObjectFactory();

    private static ObjectMapper defaultObjectMapper() {
        return UTILITIES_COMMON_CONFIG.defaultObjectMapper();
    }

    private static ObjectMapper camelCaseObjectMapper() {
        return UTILITIES_COMMON_CONFIG.camelCaseObjectMapper();
    }

    public static ObjectMapper includeEmptyObjectMapper() {
        return UTILITIES_COMMON_CONFIG.includeEmptyObjectMapper();
    }

    private static ObjectMapper xmlObjectMapper() {
        return UTILITIES_COMMON_CONFIG.xmlObjectMapper();
    }

    private static ClasspathObjectFactory defaultClasspathObjectFactory() {
        return new ClasspathObjectFactory(DEFAULT_OBJECT_MAPPER);
    }

}

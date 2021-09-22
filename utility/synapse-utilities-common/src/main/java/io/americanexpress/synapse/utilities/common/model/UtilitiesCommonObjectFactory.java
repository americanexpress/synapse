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

    private static final ObjectMapper defaultObjectMapper() {
        return UTILITIES_COMMON_CONFIG.defaultObjectMapper();
    }

    private static final ObjectMapper camelCaseObjectMapper() {
        return UTILITIES_COMMON_CONFIG.camelCaseObjectMapper();
    }

    public static final ObjectMapper includeEmptyObjectMapper() {
        return UTILITIES_COMMON_CONFIG.includeEmptyObjectMapper();
    }

    private static final ObjectMapper xmlObjectMapper() {
        return UTILITIES_COMMON_CONFIG.xmlObjectMapper();
    }

    private static final ClasspathObjectFactory defaultClasspathObjectFactory() {
        return new ClasspathObjectFactory(DEFAULT_OBJECT_MAPPER);
    }

}

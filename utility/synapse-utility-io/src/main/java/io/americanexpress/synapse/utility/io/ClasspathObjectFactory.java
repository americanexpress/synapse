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
package io.americanexpress.synapse.utility.io;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * {@code ClasspathObjectFactory} produces objects given a classpath.
 *
 * @author Paolo Claudio
 */
@Component
public class ClasspathObjectFactory {

    /**
     * Object mapper used to deserialize the specified contents of a file into an object.
     */
    private ObjectMapper mapper;

    /**
     * Argument constructor creates a new instance of ClasspathObjectFactory with given values.
     *
     * @param mapper ObjectMapper
     */
    public ClasspathObjectFactory(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Create an object from the contents of a file. The object will be deserialized using the object mapper.
     *
     * @param filePath  on the classpath
     * @param classType of the object to be deserialized
     * @return the deserialized object from the file classpath
     * @throws IOException whenever an issue occurs while trying to create the object
     */
    public <T> T create(String filePath, Class<T> classType) throws IOException {
        ClassPathResource resource = new ClassPathResource(filePath);
        InputStream inputStream = resource.getInputStream();

        return mapper.readValue(inputStream, classType);
    }

    /**
     * Create a string from the contents of a file. The object will be deserialized using the object mapper.
     *
     * @param filePath of the classpath
     * @return the string contents of the file
     * @throws IOException        whenever an issue occurs while trying to read from the given filePath
     * @throws URISyntaxException whenever an issue occurs while trying to convert the given resource into its URI
     */
    public String create(String filePath) throws IOException, URISyntaxException {
        Path path = Paths.get(getClass().getClassLoader().getResource(filePath).toURI());
        Stream<String> stream = Files.lines(path);
        String text = stream.collect(Collectors.joining());
        stream.close();
        return text;
    }


    /**
     * Used to convert xml file to java object.
     *
     * @param filePath  path of the xml file
     * @param classType is defines the java object type to populate the xml data
     * @return java object
     * @throws IOException exception
     */
    public Object createFromXml(String filePath, Class<?> classType) throws IOException {
        ClassPathResource resource = new ClassPathResource(filePath);
        InputStream inputStream = resource.getInputStream();
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.registerModule(new JaxbAnnotationModule());
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(inputStream, classType);

    }
}

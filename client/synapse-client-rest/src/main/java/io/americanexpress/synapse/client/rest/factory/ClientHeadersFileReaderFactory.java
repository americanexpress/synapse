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
package io.americanexpress.synapse.client.rest.factory;

import io.americanexpress.synapse.client.rest.model.ClientHeaderKey;
import io.americanexpress.synapse.client.rest.model.ClientHeaders;
import io.americanexpress.synapse.client.rest.model.ClientRouting;
import io.americanexpress.synapse.client.rest.model.ClientTrace;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * {@code ClientHeadersFileReaderFactory} class produces {@link ClientHeaders} objects from a properties file on the class path.
 *
 * @author Paolo Claudio
 */
public class ClientHeadersFileReaderFactory {

    /**
     * Default constructor creates a new instance of ClientHeadersFileReaderFactory with default values.
     */
    private ClientHeadersFileReaderFactory() {

        // Private constructor is used for a class containing only static members since this class is not meant to be instantiated
    }

    /**
     * Create a ClientHeaders object given the class file path.
     *
     * @param filePath location of client headers properties
     * @return object containing the client headers from the class file path
     * @throws IOException whenever an issue occurs while trying to create the client headers
     */
    public static ClientHeaders create(String filePath) throws IOException {

        // Get the properties
        ClassPathResource resource = new ClassPathResource(filePath);
        InputStream inputStream = resource.getInputStream();
        Properties properties = new Properties();
        properties.load(inputStream);

        // Set the client trace
        ClientTrace trace = new ClientTrace();
        trace.setCorrelationId(properties.getProperty(ClientHeaderKey.CORRELATION_IDENTIFIER_KEY.getValue()));

        // Set the client routing
        ClientRouting routing = new ClientRouting();

        // Create the client headers
        ClientHeaders headers = new ClientHeaders();
        headers.setTrace(trace);
        headers.setRouting(routing);
        return headers;
    }
}

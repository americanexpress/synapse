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
package io.americanexpress.synapse.utilities.common.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * {@code PostalCodeStringSerializer} class is used to convert a zip code from xxxxxxxxx to xxxxx-xxxx format during serialization.
 *
 * @author Paolo Claudio
 */
public class PostalCodeStringSerializer extends StringSerializer {

    /**
     * Length of a well-formatted zip code.
     */
    private static final int ZIP_CODE_LENGTH = 9;

    /**
     * Index of the beginning of the delivery route index within a zip code.
     * The zip code consists of the first 5 digits as the delivery area
     * and the last 4 digits is the delivery route.
     */
    private static final int ZIP_CODE_DELIVERY_ROUTE_INDEX = 5;

    /**
     * Convert a zip code from xxxxxxxxx to xxxxx-xxxx format.
     *
     * @param text      to be converted from xxxxxxxxx to xxxxx-xxxx format
     * @param generator JSON generator
     * @param provider  serializer provider
     * @throws IOException whenever there's an issue performing the serialization
     */
    @Override
    public void serialize(String text, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeObject(serialize(text));
    }

    /**
     * Convert a zip code from xxxxxxxxx to xxxxx-xxxx format.
     *
     * @param text to be converted from xxxxxxxxx to xxxxx-xxxx format
     * @return the formatted ssn.
     */
    @Override
    public String serialize(String text) {
        String zipCode = text.trim();
        if (StringUtils.isNotBlank(zipCode)) {
            // Check to see if this zip code is of the correct length and is all digits
            if (zipCode.length() == ZIP_CODE_LENGTH && zipCode.matches("\\d{" + ZIP_CODE_LENGTH + "}")) {

                // Format the zip code into its parts xxxxx-xxxx
                String deliveryArea = zipCode.substring(0, ZIP_CODE_DELIVERY_ROUTE_INDEX);
                String deliveryRoute = zipCode.substring(ZIP_CODE_DELIVERY_ROUTE_INDEX);
                zipCode = deliveryArea + "-" + deliveryRoute;
            }
        }
        return zipCode;
    }
}

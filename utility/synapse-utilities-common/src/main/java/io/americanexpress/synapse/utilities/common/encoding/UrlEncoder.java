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
package io.americanexpress.synapse.utilities.common.encoding;

import org.apache.commons.lang3.StringUtils;

/**
 * {@code UrlEncoder} class encodes a string into its corresponding encoded value. All characters are encoded except
 * for the reserved characters '-', '_', '.' and '~'.
 *
 * @author Paolo Claudio
 */
public class UrlEncoder implements Encoder {

    /**
     * Default constructor creates a new instance of UrlEncoder with default values
     */
    public UrlEncoder() {

        // No fields to set
    }

    /**
     * Use URL encoding to encode the string. All characters in the string will be encoded into its corresponding encoded
     * values except for the reserved characters '-', '_', '.' and '~'.
     *
     * @param data to be encoded
     * @return the URL encoded string
     */
    @Override
    public String encode(String data) {
        StringBuilder builder = new StringBuilder();
        if (StringUtils.isNotBlank(data)) {
            char c;
            String hexValue;
            String encoded;
            for (int i = 0; i < data.length(); i++) {
                c = data.charAt(i);

                // Don't encode URL reserved characters
                if (Character.isLetter(c) || Character.isDigit(c) || c == '-' || c == '_' || c == '.' || c == '~') {
                    builder.append(c);
                }

                // Encode the remaining characters
                else {
                    hexValue = Integer.toHexString(c);
                    encoded = ("%" + hexValue).toUpperCase();
                    builder.append(encoded);
                }
            }
        }
        return builder.toString();
    }

}

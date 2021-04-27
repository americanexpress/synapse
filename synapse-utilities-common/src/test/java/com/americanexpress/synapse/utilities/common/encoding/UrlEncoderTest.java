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
package com.americanexpress.synapse.utilities.common.encoding;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UrlEncoderTest {

    private static final String ENCODING_FAILED = "Encoding failed.";

    @Test
    public void urlEncoder_clean() {
        new UrlEncoder();
    }

    @Test
    public void encode_empty() {
        String expected = "";
        Encoder encoder = new UrlEncoder();
        String actual = encoder.encode("");
        assertEquals(expected, actual, ENCODING_FAILED);
    }

    @Test
    public void encode_alphaNumeric() {
        String expected = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Encoder encoder = new UrlEncoder();
        String actual = encoder.encode("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
        assertEquals(expected, actual, ENCODING_FAILED);
    }

    @Test
    public void encode_reservedCharacters() {
        String expected = "-_.~";
        Encoder encoder = new UrlEncoder();
        String actual = encoder.encode("-_.~");
        assertEquals(expected, actual, ENCODING_FAILED);
    }

    @Test
    public void encode_allOtherCharacters() {
        Encoder encoder = new UrlEncoder();
        StringBuilder builder = new StringBuilder();
        for (char c = 0; c <= 127; c++) {
            builder.append(c);
        }
        String expected = "%0%1%2%3%4%5%6%7%8%9%A%B%C%D%E%F%10%11%12%13%14%15%16%17%18%19%1A%1B%1C%1D%1E%1F%20%21%22%23%24%25%26%27%28%29%2A%2B%2C-.%2F0123456789%3A%3B%3C%3D%3E%3F%40ABCDEFGHIJKLMNOPQRSTUVWXYZ%5B%5C%5D%5E_%60abcdefghijklmnopqrstuvwxyz%7B%7C%7D~%7F";
        String actual = encoder.encode(builder.toString());
        assertEquals(expected, actual, ENCODING_FAILED);
    }
}

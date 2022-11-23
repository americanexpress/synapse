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
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * {@code AllCharactersMaskBigDecimalSerializer} class limits to 2 digits after decimal and  masks the all characters.
 * e.g. if the text is 1234.000000 then it first converts to 1234.00 and then text will be serialized as *********
 *
 */
public class AllCharactersMaskBigDecimalSerializer extends JsonSerializer<BigDecimal> {

    @Override
    public void serialize(BigDecimal value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeString(serialize(serialize(value)));
    }

    public String serialize(BigDecimal value) {
        CurrencySerializer currencySerializer = new CurrencySerializer();
        return currencySerializer.serialize(value);
    }

    public String serialize(String value) {
        return AllCharactersMaskStringSerializer.serialize(value, 0);
    }
}


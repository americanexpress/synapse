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
package io.americanexpress.synapse.data.couchbase.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * The type Sync gateway data converter.
 *
 * @param <T> the type parameter
 */
@Component
public class SyncGatewayDataConverter<T> implements DataConverter<T> {

    private final ObjectMapper objectMapper;

    /**
     * Instantiates a new Sync gateway data converter.
     *
     * @param objectMapper the object mapper
     */
    public SyncGatewayDataConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public T apply(HashMap hashMap, Class<T> clazz) {
        LinkedHashMap data = (LinkedHashMap) hashMap.get("data");
        data.put("_id", hashMap.get("id"));
        data.put("_rev", ((LinkedHashMap) data.get("_sync")).get("rev"));
        return objectMapper.convertValue(data, clazz);
    }

}

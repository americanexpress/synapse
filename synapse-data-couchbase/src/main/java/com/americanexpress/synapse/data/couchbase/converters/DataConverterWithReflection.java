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
package com.americanexpress.synapse.data.couchbase.converters;

import com.couchbase.client.java.repository.annotation.Id;

import java.util.Arrays;
import java.util.HashMap;

/**
 * The interface Data converter with reflection.
 *
 * @param <T> the type parameter
 */
public interface DataConverterWithReflection<T> extends DataConverter<T> {
    /**
     * Sets id.
     *
     * @param obj     the obj
     * @param hashMap the hash map
     * @param clazz   the clazz
     */
    default void setId(T obj, HashMap hashMap, Class<T> clazz) {
        Arrays.stream(clazz.getDeclaredFields()).forEach(field -> {
            if(field.getAnnotation(Id.class) != null) {
                try {
                    field.setAccessible(true);
                    field.set(obj, hashMap.get("id"));
                } catch(IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}

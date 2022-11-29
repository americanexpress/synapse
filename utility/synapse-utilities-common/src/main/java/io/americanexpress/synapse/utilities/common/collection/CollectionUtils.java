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
package io.americanexpress.synapse.utilities.common.collection;

import java.util.Collection;

/**
 * {@code CollectionUtils} contains methods for collections.
 */
public class CollectionUtils {

    private CollectionUtils() {
        // Private constructor to prevent instantiation of utility class.
    }

    /**
     * Adds all elements from source collection to target collection
     *
     * @param target collection to all all elements to
     * @param source collection to add elements from
     * @param <T>    collections elements type
     * @return if operation was successful
     * @throws NullPointerException if target collection is null
     */
    public static <T> boolean addAll(Collection<T> target, Collection<T> source) {
        if (target == null) {
            throw new NullPointerException("The collection must not be null");
        }
        return source != null && target.addAll(source);
    }
}

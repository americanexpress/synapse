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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * {@code CollectionUtilsTest} tests the {@link CollectionUtils}
 */
class CollectionUtilsTest {

    @Test
    void addAll_givenCollectionWithItems_expectedCollectionItemsAdded() {
        Collection<String> collection = new ArrayList<>();
        collection.add("hello");
        collection.add("world");
        Collection<String> newCollection = new ArrayList<>();

        boolean success = CollectionUtils.addAll(newCollection, collection);
        Assertions.assertTrue(success);
        Assertions.assertEquals(collection.size(), newCollection.size());
    }

    @Test
    void addAll_givenNullTargetCollection_expectedNullPointerException() {
        Collection<String> collection = new ArrayList<>();

        Assertions.assertThrows(NullPointerException.class, () -> CollectionUtils.addAll(null, collection));
    }

}

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

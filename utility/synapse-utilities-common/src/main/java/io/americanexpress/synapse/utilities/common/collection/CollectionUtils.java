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

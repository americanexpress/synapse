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
package io.americanexpress.synapse.data.redis.repository;

import io.americanexpress.synapse.data.redis.entity.BaseEntity;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * {@code BaseRedisHashReactiveRepository} contains custom base crud operations for operating on Redis hashes.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public abstract class BaseRedisHashReactiveRepository<K, V extends BaseEntity> {

    /**
     * The Reactive redis operations.
     */
    protected final ReactiveRedisOperations<K, V> reactiveRedisOperations;

    /**
     * The Reactive hash operations.
     */
    protected final ReactiveHashOperations reactiveHashOperations;

    /**
     * Instantiates a new Base redis hash reactive repository.
     *
     * @param reactiveRedisOperations the reactive redis operations
     */
    protected BaseRedisHashReactiveRepository(ReactiveRedisOperations<K, V> reactiveRedisOperations) {
        this.reactiveRedisOperations = reactiveRedisOperations;
        this.reactiveHashOperations = reactiveRedisOperations.<K,V>opsForHash();
    }

    /**
     * Abstract method for implementing repositories to override, to provide the key value for the redis hash.
     *
     * @return the key
     */
    public abstract String getKey();

    /**
     * Exists by id mono.
     *
     * @param id the id
     * @return the mono
     */
    public Mono<Boolean> existsById(K id) {
        return reactiveHashOperations.hasKey(getKey(), id);
    }

    /**
     * Find by id.
     *
     * @param id the id
     * @return the mono
     */
    public Mono<V> findById(K id) {
        return reactiveHashOperations.get(getKey(), id);
    }

    /**
     * Find by id.
     *
     * @return the flux
     */
    public Flux<V> findAll() {
        return reactiveHashOperations.values(getKey());
    }


    /**
     * Save mono.
     *
     * @param value the value
     * @return the mono
     */
    public Mono<V> save(V value) {
        return reactiveHashOperations.put(getKey(), (K) value.getIdentifier(), value)
                .log()
                .map(p -> value);
    }

    /**
     * Delete all mono.
     *
     * @return the mono
     */
    public Mono<Void> deleteAll() {
        return reactiveHashOperations.delete(getKey()).then();
    }

    /**
     * Delete mono.
     *
     * @param value the value
     * @return the mono
     */
    public Mono<Void> delete(V value) {
        return reactiveHashOperations.remove(getKey(), value.getIdentifier()).then();
    }

    /**
     * Delete by id mono.
     *
     * @param id the id
     * @return the mono
     */
    public Mono<Void> deleteById(String id) {
        return reactiveHashOperations.remove(getKey(), id).then();
    }

    /**
     * Count mono.
     *
     * @return the mono
     */
    public Mono<Long> count() {
        return reactiveHashOperations.values(getKey()).count();
    }
}

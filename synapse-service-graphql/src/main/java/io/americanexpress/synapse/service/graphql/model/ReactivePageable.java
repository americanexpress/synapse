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
package io.americanexpress.synapse.service.graphql.model;

import java.util.concurrent.CompletableFuture;

import graphql.relay.Connection;

/**
 * {@code ReactivePageable} interface specifies the prototypes
 * for models that can be used for reactive pagination.
 * @author Paolo Claudio
 *
 * @param <T> type of element
 */
public interface ReactivePageable<T extends UniversallyUniqueIdentifiable> {

	/**
	 * Prototype to get the {@code first} number of paginated elements
	 * {@code after} this opaque cursor.
	 * @param first number of elements
	 * @param after the opaque cursor
	 * @return the paginated elements
	 */
	CompletableFuture<Connection<T>> getPaginatedElements(int first, String after);
}

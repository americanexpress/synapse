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

import java.util.List;
import java.util.concurrent.CompletableFuture;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * {@code ReactiveResponseCreator} class creates reactive responses.
 * @author Paolo Claudio
 *
 */
public final class ReactiveResponseCreator {

	/**
	 * Default constructor creates a new instance of ReactiveResponseCreator with default values.
	 */
	private ReactiveResponseCreator() {
		
		// A class containing only static methods is a utility class that requires a private default constructor
	}
	
	/**
	 * Create the reactive response.
	 * @param element to be set in the response
	 * @return the reactive response
	 */
	public static <T> CompletableFuture<T> create(T element) {
		return Mono.fromSupplier(() -> element).toFuture();
	}
	
	/**
	 * Create the reactive response.
	 * @param elements to be set in the response
	 * @return the reactive response
	 */
	public static <T> CompletableFuture<List<T>> create(List<T> elements) {
		return Flux.fromIterable(elements)
			.collectList()
			.toFuture();
	}
}

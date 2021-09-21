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
package io.americanexpress.synapse.service.graphql.pagination;

import java.util.List;
import java.util.stream.Collectors;

import graphql.relay.Connection;
import graphql.relay.ConnectionCursor;
import graphql.relay.DefaultConnection;
import graphql.relay.DefaultEdge;
import graphql.relay.DefaultPageInfo;
import graphql.relay.Edge;
import graphql.relay.PageInfo;
import io.americanexpress.synapse.service.graphql.model.UniversallyUniqueIdentifiable;

/**
 * {@code ConnectionUtil} class provides utilities for {@link Connection}.
 * @author Paolo Claudio
 *
 */
public final class ConnectionUtil {

	/**
	 * Default constructor creates a new instance of ConnectionUtil with default values.
	 */
	private ConnectionUtil() {
		
		// A class containing only static methods is a utility class that requires a private default constructor
	}
	
	/**
	 * Create the {@link Connection}.
	 * @param <T> type of {@link UniversallyUniqueIdentifiable} element
	 * @param elements used to create the edges of the connection
	 * @param first number of elements
	 * @param after the opaque cursor
	 * @return the {@link Connection}
	 */
	public static <T extends UniversallyUniqueIdentifiable> Connection<T> create(List<T> elements, long first, String after) {
		
		// Get the limit of this stream which is either the number of elements
		// specified by "first"; otherwise the full elements size
		long limit = first > 0 ? first : elements.size();
		
		// Create the edges for the connection
		List<Edge<T>> edges = elements.stream()
			.limit(limit)
			.map(element -> new DefaultEdge<>(element, ConnectionCursorUtil.from(element.getId())))
			.collect(Collectors.toList());
		
		// Create the page information for the connection
		ConnectionCursor startCursor = ConnectionCursorUtil.getStartCursor(edges);
		ConnectionCursor endCursor = ConnectionCursorUtil.getEndCursor(edges);
		boolean hasPreviousPage = after != null;
		boolean hasNextPage = edges.size() >= first;
		PageInfo pageInfo = new DefaultPageInfo(startCursor, endCursor, hasPreviousPage, hasNextPage);
		
		return new DefaultConnection<>(edges, pageInfo);
	}
}

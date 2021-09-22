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
import java.util.UUID;

import graphql.relay.ConnectionCursor;
import graphql.relay.DefaultConnectionCursor;
import graphql.relay.Edge;

/**
 * {@code ConnectionCursorUtil} class provides utilities for {@link ConnectionCursor}.
 * @author Paolo Claudio
 *
 */
public final class ConnectionCursorUtil {

	/**
	 * Default constructor creates a new instance of ConnectionCursorUtil with default values.
	 */
	private ConnectionCursorUtil() {
		
		// A class containing only static methods is a utility class that requires a private default constructor
	}
	
	/**
	 * Get the {@link ConnectionCursor} from this cursor.
	 * @param cursor the cursor
	 * @return the {@link ConnectionCursor}
	 */
	public static final ConnectionCursor from(UUID uuid) {
		return new DefaultConnectionCursor(UUIDUtil.toString(uuid));
	}
	
	/**
	 * Get the start cursor.
	 * @param edges the edges
	 * @return the start cursor
	 */
	public static final <T> ConnectionCursor getStartCursor(List<Edge<T>> edges) {
		return edges.isEmpty() ? null : edges.get(0).getCursor();
	}
	
	/**
	 * Get the end cursor.
	 * @param edges the edges
	 * @return the end cursor
	 */
	public static final <T> ConnectionCursor getEndCursor(List<Edge<T>> edges) {
		return edges.isEmpty() ? null : edges.get(edges.size() - 1).getCursor();
	}
}

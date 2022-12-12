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

import java.util.Base64;
import java.util.UUID;

/**
 * {@code UUIDUtil} class provides utilities for universally unique identifiers (UUID).
 * @author Paolo Claudio
 *
 */
public final class UUIDUtil {

	/**
	 * Default constructor creates a new instance of UUIDUtil with default values.
	 */
	private UUIDUtil() {
		
		// A class containing only static methods is a utility class that requires a private default constructor
	}
	
	/**
	 * Convert a {@link UUID} to a Base64 encoded {@link String}.
	 * @param uuid to be converted
	 * @return a Base64 encoded {@link String}
	 */
	public static String toString(UUID uuid) {
		return Base64.getEncoder().encodeToString(uuid.toString().getBytes());
	}
	
	/**
	 * Convert a {@link String} to a Base64 decoded {@link UUID}.
	 * @param text to be converted
	 * @return a Base64 decoded {@link UUID}
	 */
	public static UUID toUUID(String text) {
		return UUID.fromString(new String(Base64.getDecoder().decode(text)));
	}
}

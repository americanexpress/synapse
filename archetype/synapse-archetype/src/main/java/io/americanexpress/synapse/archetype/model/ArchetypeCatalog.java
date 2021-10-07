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
package io.americanexpress.synapse.archetype.model;

import java.util.List;

/**
 * {@code ArchetypeCatalog} class represents the root &lt;archetype-catalog/&gt; element
 * in the {@code archetype-catalog.xml} file.
 * @author Paolo Claudio
 *
 */
public class ArchetypeCatalog {

	/**
	 * All of the archetypes in the catalog.
	 */
	private List<Archetype> archetypes;

	/**
	 * Get the archetypes.
	 * @return the archetypes
	 */
	public List<Archetype> getArchetypes() {
		return archetypes;
	}

	/**
	 * Set the archetypes.
	 * @param archetypes the archetypes to set
	 */
	public void setArchetypes(List<Archetype> archetypes) {
		this.archetypes = archetypes;
	}
}

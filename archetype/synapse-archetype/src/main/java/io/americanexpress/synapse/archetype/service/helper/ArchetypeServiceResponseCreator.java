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
package io.americanexpress.synapse.archetype.service.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.americanexpress.synapse.archetype.model.ArchetypeCatalog;
import io.americanexpress.synapse.archetype.model.ArchetypeServiceResponse;

/**
 * {@code ArchetypeServiceResponseCreator} class creates the {@link ArchetypeServiceResponse}.
 * @author Paolo Claudio
 *
 */
@Component
public class ArchetypeServiceResponseCreator {

	/**
	 * Archetype catalog containing all of the archetypes.
	 */
	private final ArchetypeCatalog archetypeCatalog;
	
	/**
	 * Argument constructor creates a new instance of ArchetypeServiceResponseCreator with given values.
	 * @param archetypeCatalog archetype catalog containing all of the archetypes
	 */
	@Autowired
	public ArchetypeServiceResponseCreator(ArchetypeCatalog archetypeCatalog) {
		this.archetypeCatalog = archetypeCatalog;
	}
	
	/**
	 * Create the {@link ArchetypeServiceResponse}.
	 * @return the {@link ArchetypeServiceResponse}
	 */
	public ArchetypeServiceResponse create() {
		ArchetypeServiceResponse archetypeServiceResponse = new ArchetypeServiceResponse();
		archetypeServiceResponse.setArchetypes(archetypeCatalog.getArchetypes());
		return archetypeServiceResponse;
	}
}

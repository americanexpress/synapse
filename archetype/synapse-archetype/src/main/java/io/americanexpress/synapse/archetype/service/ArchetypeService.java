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
package io.americanexpress.synapse.archetype.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.americanexpress.synapse.archetype.model.ArchetypeServiceRequest;
import io.americanexpress.synapse.archetype.model.ArchetypeServiceResponse;
import io.americanexpress.synapse.archetype.service.helper.ArchetypeServiceResponseCreator;
import io.americanexpress.synapse.service.rest.service.BaseReadMonoService;

/**
 * {@code ArchetypeService} class returns the available archetypes found
 * from the {@code archetype-catalog.xml} file specified in {@code surgeon-service-application.properties}.
 * @author Paolo Claudio
 *
 */
@Service
public class ArchetypeService extends BaseReadMonoService<ArchetypeServiceRequest, ArchetypeServiceResponse>{

	/**
	 * Used to create the {@link ArchetypeServiceResponse}.
	 */
	private final ArchetypeServiceResponseCreator archetypeServiceResponseCreator;
	
	/**
	 * Argument constructor creates a new instance of ArchetypeService with given values.
	 * @param archetypeServiceResponseCreator used to create the {@link ArchetypeServiceResponse}
	 */
	@Autowired
	public ArchetypeService(ArchetypeServiceResponseCreator archetypeServiceResponseCreator) {
		this.archetypeServiceResponseCreator = archetypeServiceResponseCreator;
	}
	
	/**
	 * Read the {@link ArchetypeServiceResponse}.
	 * @param archetypeServiceRequest the service request
	 * @return the {@link ArchetypeServiceResponse}
	 */
	@Override
	protected ArchetypeServiceResponse executeRead(ArchetypeServiceRequest archetypeServiceRequest) {
		return archetypeServiceResponseCreator.create();
	}
}

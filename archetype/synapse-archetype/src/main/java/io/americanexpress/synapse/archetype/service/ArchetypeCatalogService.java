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

import io.americanexpress.synapse.archetype.model.ArchetypeCatalogServiceRequest;
import io.americanexpress.synapse.archetype.model.ArchetypeCatalogServiceResponse;
import io.americanexpress.synapse.archetype.service.helper.ArchetypeCatalogServiceResponseCreator;
import io.americanexpress.synapse.service.rest.service.BaseReadMonoService;

/**
 * {@code ArchetypeCatalogService} class returns the available archetypes found
 * from the {@code archetype-catalog.xml} file specified in {@code surgeon-service-application.properties}.
 * @author Paolo Claudio
 *
 */
@Service
public class ArchetypeCatalogService extends BaseReadMonoService<ArchetypeCatalogServiceRequest, ArchetypeCatalogServiceResponse> {

	/**
	 * Used to create the {@link ArchetypeCatalogServiceResponse}.
	 */
	private final ArchetypeCatalogServiceResponseCreator archetypeCatalogServiceResponseCreator;
	
	/**
	 * Argument constructor archetypeCatalogServiceResponseCreator a new instance of ArchetypeCatalogService with given values.
	 * @param archetypeServiceResponseCreator used to create the {@link ArchetypeCatalogServiceResponse}
	 */
	@Autowired
	public ArchetypeCatalogService(ArchetypeCatalogServiceResponseCreator archetypeCatalogServiceResponseCreator) {
		this.archetypeCatalogServiceResponseCreator = archetypeCatalogServiceResponseCreator;
	}
	
	/**
	 * Read the {@link ArchetypeCatalogServiceResponse}.
	 * @param archetypeCatalogServiceRequest the service request
	 * @return the {@link ArchetypeCatalogServiceResponse}
	 */
	@Override
	protected ArchetypeCatalogServiceResponse executeRead(ArchetypeCatalogServiceRequest archetypeCatalogServiceRequest) {
		return archetypeCatalogServiceResponseCreator.create();
	}
}

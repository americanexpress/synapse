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

import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.americanexpress.synapse.archetype.model.SurgeonServiceRequest;
import io.americanexpress.synapse.archetype.model.SurgeonServiceResponse;
import io.americanexpress.synapse.archetype.service.helper.InvocationRequestCreator;
import io.americanexpress.synapse.archetype.service.helper.MavenInvoker;
import io.americanexpress.synapse.archetype.service.helper.SurgeonServiceResponseCreator;
import io.americanexpress.synapse.service.rest.service.BaseCreateService;

/**
 * {@code SurgeonService} class creates modules by taking the service request
 * and stitching the inputs together using {@code maven-invoker}.
 * @author Paolo Claudio
 *
 */
@Service
public class SurgeonService extends BaseCreateService<SurgeonServiceRequest, SurgeonServiceResponse> {

	/**
	 * Used to create the {@link InvocationRequest}.
	 */
	private final InvocationRequestCreator mavenInvocationRequestCreator;
	
	/**
	 * Used to call the {@code mvn-invoker}.
	 */
	private final MavenInvoker mavenInvoker;
	
	/**
	 * Used to create the {@link SurgeonServiceResponse}.
	 */
	private final SurgeonServiceResponseCreator surgeonServiceResponseCreator;
	
	/**
	 * Argument constructor creates a new instance of SurgeonService with given values.
	 * @param mavenInvocationRequestCreator used to create the {@link InvocationRequest}
	 * @param mavenInvoker used to call the {@code mvn-invoker}
	 * @param surgeonServiceResponseCreator used to create the {@link SurgeonServiceResponse}
	 */
	@Autowired
	public SurgeonService(InvocationRequestCreator mavenInvocationRequestCreator, MavenInvoker mavenInvoker, SurgeonServiceResponseCreator surgeonServiceResponseCreator) {
		this.mavenInvocationRequestCreator = mavenInvocationRequestCreator;
		this.mavenInvoker = mavenInvoker;
		this.surgeonServiceResponseCreator = surgeonServiceResponseCreator;
	}
	
	/**
	 * Create the module by performing surgery, stitching together the inputs from the service request.
	 * @param surgeonServiceRequest the service request
	 * @return the {@link SurgeonServiceResponse}
	 */
	@Override
	protected SurgeonServiceResponse executeCreate(SurgeonServiceRequest surgeonServiceRequest) {
		InvocationRequest invocationRequest = mavenInvocationRequestCreator.create(surgeonServiceRequest);
		InvocationResult invocationResult = mavenInvoker.invoke(surgeonServiceRequest.getOutputDirectoryName(), invocationRequest);
		return surgeonServiceResponseCreator.create(invocationResult);
	}
}

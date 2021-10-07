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

import java.util.List;
import java.util.Properties;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.americanexpress.synapse.archetype.model.SurgeonServiceRequest;
import io.americanexpress.synapse.utilities.common.serialization.TitleCaseStringSerializer;

/**
 * {@code InvocationRequestCreator} class creates the {@link InvocationRequest}
 * for the {@link MavenInvoker}.
 * @author Paolo Claudio
 *
 */
@Component
public class InvocationRequestCreator {

	/**
	 * Used to convert strings into title case.
	 */
	private final TitleCaseStringSerializer titleCaseStringSerializer;
	
	/**
	 * Argument constructor creates a new instance of InvocationRequestCreator with given values.
	 * @param titleCaseStringSerializer used to convert strings into title case
	 */
	@Autowired
	public InvocationRequestCreator(TitleCaseStringSerializer titleCaseStringSerializer) {
		this.titleCaseStringSerializer = titleCaseStringSerializer;
	}
	
	/**
	 * Create the {@link InvocationRequest}.
	 * @param surgeonServiceRequest containing the request fields
	 * @return the {@link InvocationRequest}
	 */
	public InvocationRequest create(SurgeonServiceRequest surgeonServiceRequest) {
		
		Properties properties = new Properties();
		
		// Select the archetype from the archetype catalog
		properties.put("archetypeCatalog", "local");
		properties.put("archetypeGroupId", "io.americanexpress.synapse");
		properties.put("archetypeArtifactId", surgeonServiceRequest.getArchetypeArtifactId());
		properties.put("archetypeVersion", "0.1.0-SNAPSHOT");
		
		// Format the inputs
		String groupId = surgeonServiceRequest.getGroupId().toLowerCase();
		String artifactId = surgeonServiceRequest.getArtifactId().toLowerCase();
		String packageName = surgeonServiceRequest.getPackageName().toLowerCase();
		String apiName = titleCaseStringSerializer.serialize(surgeonServiceRequest.getApiName());
		String className = apiName.replaceAll("\\s", "");
		
		// Set the properties from the archetype
		properties.put("groupId", groupId);
		properties.put("artifactId", artifactId);
		properties.put("version", "0.0.1-SNAPSHOT");
		properties.put("package", packageName);
		properties.put("javaVersion", surgeonServiceRequest.getJavaVersion());
		properties.put("author", surgeonServiceRequest.getAuthor());
		properties.put("apiName", apiName);
		properties.put("url", surgeonServiceRequest.getUrl());
		properties.put("className", className);
		
		// Create the Maven invocation request
		InvocationRequest invocationRequest = new DefaultInvocationRequest();
		invocationRequest.setBatchMode(true);
		invocationRequest.setGoals(List.of("archetype:generate"));
		invocationRequest.setProperties(properties);
		return invocationRequest;
	}
}

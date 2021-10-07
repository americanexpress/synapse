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

import javax.validation.constraints.NotBlank;

import io.americanexpress.synapse.archetype.service.SurgeonService;
import io.americanexpress.synapse.service.rest.model.BaseServiceRequest;

/**
 * {@code SurgeonServiceRequest} class represents the service request to the {@link SurgeonService}.
 * @author Paolo Claudio
 *
 */
public class SurgeonServiceRequest extends BaseServiceRequest {

	/**
	 * Output directory where the generated modules will be created.
	 */
	@NotBlank
	private String outputDirectoryName;
	
	/**
	 * Selected archetype to be created.
	 */
	@NotBlank
	private String archetypeArtifactId;
	
	/**
	 * Group ID of the module to be created e.g. com.example.
	 */
	@NotBlank
	private String groupId;
	
	/**
	 * Artifact ID of the module to be created e.g. example-service-book-rest.
	 */
	@NotBlank
	private String artifactId;
	
	/**
	 * Name of the package of the module to be created e.g. com.example.book.
	 */
	@NotBlank
	private String packageName;
	
	/**
	 * Java version of the module to be created e.g. 11.
	 */
	@NotBlank
	private String javaVersion;
	
	/**
	 * Author of the module to be created e.g. John Doe.
	 */
	@NotBlank
	private String author;
	
	/**
	 * API name of the module to be created e.g. Example Bookstore.
	 */
	@NotBlank
	private String apiName;
	
	/**
	 * Endpoint url of the module to be created e.g. http://localhost:8080.
	 */
	@NotBlank
	private String url;

	/**
	 * Get the outputDirectoryName.
	 * @return the outputDirectoryName
	 */
	public String getOutputDirectoryName() {
		return outputDirectoryName;
	}

	/**
	 * Set the outputDirectoryName.
	 * @param outputDirectoryName the outputDirectoryName to set
	 */
	public void setOutputDirectoryName(String outputDirectoryName) {
		this.outputDirectoryName = outputDirectoryName;
	}

	/**
	 * Get the archetypeArtifactId.
	 * @return the archetypeArtifactId
	 */
	public String getArchetypeArtifactId() {
		return archetypeArtifactId;
	}

	/**
	 * Set the archetypeArtifactId.
	 * @param archetypeArtifactId the archetypeArtifactId to set
	 */
	public void setArchetypeArtifactId(String archetypeArtifactId) {
		this.archetypeArtifactId = archetypeArtifactId;
	}

	/**
	 * Get the groupId.
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * Set the groupId.
	 * @param groupId the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * Get the artifactId.
	 * @return the artifactId
	 */
	public String getArtifactId() {
		return artifactId;
	}

	/**
	 * Set the artifactId.
	 * @param artifactId the artifactId to set
	 */
	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	/**
	 * Get the packageName.
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * Set the packageName.
	 * @param packageName the packageName to set
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * Get the javaVersion.
	 * @return the javaVersion
	 */
	public String getJavaVersion() {
		return javaVersion;
	}

	/**
	 * Set the javaVersion.
	 * @param javaVersion the javaVersion to set
	 */
	public void setJavaVersion(String javaVersion) {
		this.javaVersion = javaVersion;
	}

	/**
	 * Get the author.
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Set the author.
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Get the apiName.
	 * @return the apiName
	 */
	public String getApiName() {
		return apiName;
	}

	/**
	 * Set the apiName.
	 * @param apiName the apiName to set
	 */
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	/**
	 * Get the url.
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Set the url.
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}

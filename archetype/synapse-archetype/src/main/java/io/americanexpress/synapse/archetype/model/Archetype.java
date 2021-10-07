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

/**
 * {@code Archetype} class represents each &lt;archetype/&gt; entry in the {@code archetype-catalog.xml} file.
 * @author Paolo Claudio
 *
 */
public class Archetype {

	/**
	 * Group ID e.g. io.americanexpress.synapse.
	 */
	private String groupId;
	
	/**
	 * Artifact ID e.g. synapse-archetype-client-rest.
	 */
	private String artifactId;
	
	/**
	 * Version e.g. 1.0.0.
	 */
	private String version;
	
	/**
	 * Description of the archetype.
	 */
	private String description;

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
	 * Get the version.
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Set the version.
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * Get the description.
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the description.
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}

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
package io.americanexpress.synapse.client.graphql.model;

import java.util.Map;

import io.americanexpress.synapse.client.rest.model.BaseClientRequest;

/**
 * {@code GraphQLClientRequest} class represents the client request
 * to a GraphQL API.
 * @author Paolo Claudio
 *
 */
public class GraphQLClientRequest implements BaseClientRequest {

	/**
	 * The selected operation name of the query.
	 */
	private String operationName;
	
	/**
	 * Containing all of the queries and mutations.
	 */
	private String query;
	
	/**
	 * Input variables to be placed in the selected query.
	 */
	private Map<String, Object> variables;

	/**
	 * Get the operationName.
	 * @return the operationName
	 */
	public String getOperationName() {
		return operationName;
	}

	/**
	 * Set the operationName.
	 * @param operationName the operationName to set
	 */
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	/**
	 * Get the query.
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * Set the query.
	 * @param query the query to set
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * Get the variables.
	 * @return the variables
	 */
	public Map<String, Object> getVariables() {
		return variables;
	}

	/**
	 * Set the variables.
	 * @param variables the variables to set
	 */
	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}
}

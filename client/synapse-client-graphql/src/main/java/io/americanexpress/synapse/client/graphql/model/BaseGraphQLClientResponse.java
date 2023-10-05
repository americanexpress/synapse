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

import java.util.List;

import graphql.GraphQLError;
import io.americanexpress.synapse.client.rest.model.BaseClientResponse;

/**
 * {@code BaseGraphQLClientResponse} class specifies the prototypes
 * for the client responses from a GraphQL API.
 * @author Paolo Claudio
 *
 */
public abstract class BaseGraphQLClientResponse<T extends BaseGraphQLData> implements BaseClientResponse {

	/**
	 * Data returned from the GraphQL API.
	 */
	private T data;
	
	/**
	 * Errors returned from the GraphQL API.
	 */
	private List<BaseGraphQLError> errors;

	/**
	 * Get the data.
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * Set the data.
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * Get the errors.
	 * @return the errors
	 */
	public List<BaseGraphQLError> getErrors() {
		return errors;
	}

	/**
	 * Set the errors.
	 * @param errors the errors to set
	 */
	public void setErrors(List<BaseGraphQLError> errors) {
		this.errors = errors;
	}
}

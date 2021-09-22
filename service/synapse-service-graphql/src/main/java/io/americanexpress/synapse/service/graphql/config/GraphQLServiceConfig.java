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
package io.americanexpress.synapse.service.graphql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;

/**
 * {@code GraphQLServiceConfig} class sets common configurations for the GraphQL service.
 *
 * @author Paolo Claudio
 */
@Configuration
public class GraphQLServiceConfig {

	/**
	 * Get the date scalar.
	 * @return the date scalar
	 */
	@Bean
	public GraphQLScalarType date() {
		return ExtendedScalars.Date;
	}
	
	/**
	 * Get the date time scalar.
	 * @return the date time scalar
	 */
	@Bean
	public GraphQLScalarType dateTime() {
		return ExtendedScalars.DateTime;
	}
	
	/**
	 * Get the time scalar.
	 * @return the time scalar
	 */
	@Bean
	public GraphQLScalarType time() {
		return ExtendedScalars.Time;
	}
	
	/**
	 * Get the positive int scalar.
	 * @return the positive int scalar
	 */
	@Bean
	public GraphQLScalarType positiveInt() {
		return ExtendedScalars.PositiveInt;
	}
	
	/**
	 * Get the negative int scalar.
	 * @return the negative int scalar
	 */
	@Bean
	public GraphQLScalarType negativeInt() {
		return ExtendedScalars.NegativeInt;
	}
	
	/**
	 * Get the non-positive int scalar.
	 * @return the non-positive int scalar
	 */
	@Bean
	public GraphQLScalarType nonPositiveInt() {
		return ExtendedScalars.NonPositiveInt;
	}
	
	/**
	 * Get the non-negative int scalar.
	 * @return the non-negative int scalar
	 */
	@Bean
	public GraphQLScalarType nonNegativeInt() {
		return ExtendedScalars.NonNegativeInt;
	}
	
	/**
	 * Get the positive float scalar.
	 * @return the positive float scalar
	 */
	@Bean
	public GraphQLScalarType positiveFloat() {
		return ExtendedScalars.PositiveFloat;
	}
	
	/**
	 * Get the negative float scalar.
	 * @return the negative float scalar
	 */
	@Bean
	public GraphQLScalarType negativeFloat() {
		return ExtendedScalars.NegativeFloat;
	}
	
	/**
	 * Get the non-positive float scalar.
	 * @return the non-positive float scalar
	 */
	@Bean
	public GraphQLScalarType nonPositiveFloat() {
		return ExtendedScalars.NonPositiveFloat;
	}
	
	/**
	 * Get the non-negative float scalar.
	 * @return the non-negative float scalar
	 */
	@Bean
	public GraphQLScalarType nonNegativeFloat() {
		return ExtendedScalars.NonNegativeFloat;
	}
}

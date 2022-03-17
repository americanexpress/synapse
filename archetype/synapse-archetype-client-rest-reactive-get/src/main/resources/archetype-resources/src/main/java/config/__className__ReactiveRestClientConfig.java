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
package ${package}.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import io.americanexpress.synapse.client.rest.config.BaseReactiveRestClientConfig;

import ${package}.client.${className}GetReactiveRestClient;

/**
 * {@code ${className}ReactiveRestClientConfig} class sets the configurations
 * for the {@link ${className}GetReactiveRestClient}.
 * @author ${author}
 */
@Import(BaseReactiveRestClientConfig.class)
@PropertySource("classpath:client-application.properties")
@ComponentScan("${package}")
@Configuration
public class ${className}ReactiveRestClientConfig extends BaseReactiveRestClientConfig {

	/**
	 * Used to connect to the ${apiName} REST API.
	 */
	private final ${className}GetReactiveRestClient getReactiveRestClient;
	
	/**
	 * Argument constructor creates a new instance of ${className}ReactiveRestClientConfig with given values.
	 * @param getReactiveRestClient used to connect to the ${apiName} REST API
	 */
	@Autowired
	public ${className}ReactiveRestClientConfig(${className}GetReactiveRestClient getReactiveRestClient) {
		this.getReactiveRestClient = getReactiveRestClient;
	}
	
	/**
	 * Initialize this REST client.
	 */
	@Value("${client.url}")
	@Override
	protected void initialize(String destinationUrl) {
		initializeClient(destinationUrl, getReactiveRestClient);
	}
}

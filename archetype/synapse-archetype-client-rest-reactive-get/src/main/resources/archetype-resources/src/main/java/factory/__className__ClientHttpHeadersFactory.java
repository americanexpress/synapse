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
package ${package}.factory;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import io.americanexpress.synapse.client.rest.factory.BaseClientHttpHeadersFactory;
import io.americanexpress.synapse.client.rest.model.ClientHeaders;

import ${package}.model.${className}ClientRequest;

/**
 * {@code ${className}ClientHttpHeadersFactory} class creates the the client HTTP headers
 * for the ${apiName} REST API.
 * @author ${author}
 */
@Component
public class ${className}ClientHttpHeadersFactory extends BaseClientHttpHeadersFactory<${className}ClientRequest> {

	/**
	 * Create the client HTTP headers.
	 * 
	 * @param clientHeaders containing the headers of the request to this API
	 * @param clientRequest containing the body of the request to this API
	 * @param url of the API
	 * @return the client HTTP headers
	 */
	@Override
	public HttpHeaders create(ClientHeaders clientHeaders, ${className}ClientRequest clientRequest, String url) {
		// TODO: please add any HTTP headers required by the back end API
		HttpHeaders httpHeaders = new HttpHeaders();
		return httpHeaders;
	}
}

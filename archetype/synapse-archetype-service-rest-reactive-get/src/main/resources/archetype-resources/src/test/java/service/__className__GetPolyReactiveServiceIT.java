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
package ${package}.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;

import io.americanexpress.synapse.framework.test.CommonAssertionMessages;

import ${package}.model.${className}ServiceResponse;

/**
 * {@code ${className}GetPolyReactiveServiceIT} class performs integration tests for the ${apiName} application
 * Be sure that the ${apiName} REST API is running
 * prior to running this integration test.
 * @author ${author}
 */
@ExtendWith(SpringExtension.class)
class ${className}GetPolyReactiveServiceIT {

    @Test
    void read_givenValidIdentifier_expectValidResponse() {
        ${className}GetPolyReactiveService restService = new ${className}GetPolyReactiveService();
        HttpHeaders httpHeaders = new HttpHeaders();
        Flux<${className}ServiceResponse> serviceResponse = restService.executeRead(httpHeaders);
        assertNotNull(serviceResponse, CommonAssertionMessages.VALUE_NULL);
    }
}

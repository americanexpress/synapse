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

import io.americanexpress.synapse.framework.test.CommonAssertionMessages;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import ${package}.config.${className}CreateServiceTestConfig;
import ${package}.model.${className}CreateServiceRequest;
import ${package}.model.${className}CreateServiceResponse;

/**
 * {@code ${className}CreateServiceIT} class performs integration tests
 * for the {@link ${className}CreateService}.
 * <p>
 * @author ${author}
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=${className}CreateServiceTestConfig.class)
class ${className}CreateServiceIT {

    @Autowired
    private ${className}CreateService restService;

    @Test
    void create_givenValidRequest_expectedValidResponse() throws Exception {
        ${className}CreateServiceRequest serviceRequest = new ${className}CreateServiceRequest();
        ${className}CreateServiceResponse serviceResponse = restService.create(serviceRequest);
        assertNotNull(serviceResponse, CommonAssertionMessages.VALUE_NULL);
    }
}

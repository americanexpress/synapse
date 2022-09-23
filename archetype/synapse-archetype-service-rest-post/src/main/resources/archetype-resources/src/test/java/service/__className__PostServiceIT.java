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

import ${package}.config.${className}ServiceTestConfig;
import ${package}.model.${className}ServiceRequest;
import ${package}.model.${className}ServiceResponse;

/**
 * {@code ${className}PostServiceIT} class performs integration tests
 * for the {@link ${className}PostService}.
 * <p>
 * @author ${author}
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=${className}ServiceTestConfig.class)
class ${className}PostServiceIT {

    @Autowired
    private ${className}PostService restService;

    @Test
    void callPostService_givenValidRequest_expectedValidResponse() throws Exception {
        ${className}ServiceRequest serviceRequest = new ${className}ServiceRequest();
        ${className}ServiceResponse serviceResponse = restService.create(serviceRequest);
        assertNotNull(serviceResponse, CommonAssertionMessages.VALUE_NULL);
    }
}

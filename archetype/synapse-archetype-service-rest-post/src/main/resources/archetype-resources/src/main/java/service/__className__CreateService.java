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

import ${package}.model.${className}CreateServiceRequest;
import ${package}.model.${className}CreateServiceResponse;
import io.americanexpress.synapse.service.rest.service.BaseCreateService;
import org.springframework.stereotype.Service;

/**
 * {@code ${className}CreateService} class creates an entity
 * based on the request given.
 * @author ${author}
 */
@Service
public class ${className}CreateService extends BaseCreateService<${className}CreateServiceRequest, ${className}CreateServiceResponse> {

    /**
     * Add a resource.
     *
     * @param request body received from the controller
     * @return response body to the controller
     */
    @Override
    protected ${className}CreateServiceResponse executeCreate(${className}CreateServiceRequest request) {
        return new ${className}CreateServiceResponse();
    }
}

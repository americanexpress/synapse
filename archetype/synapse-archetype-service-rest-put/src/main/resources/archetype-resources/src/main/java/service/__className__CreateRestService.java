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

import org.springframework.stereotype.Service;

import io.americanexpress.synapse.service.rest.service.BaseCreateService;

import ${package}.model.${className}ServiceResponse;
import ${package}.model.${className}ServiceRequest;

/**
 * {@code ${className}CreateRestService class is the service
 * used to connect to the ${apiName} REST API.
 * @author ${author}
 *
 */
@Service
public class ${className}CreateRestService extends BaseCreateService<${className}ServiceRequest, ${className}ServiceResponse> {

    @Override
    protected ${className}ServiceResponse executeCreate(${className}ServiceRequest serviceRequest) {
        return new ${className}ServiceResponse();
    }
}
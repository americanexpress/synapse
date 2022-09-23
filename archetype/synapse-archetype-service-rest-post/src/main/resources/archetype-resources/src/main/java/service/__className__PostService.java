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

import ${package}.model.${className}ServiceRequest;
import ${package}.model.${className}ServiceResponse;
import io.americanexpress.synapse.service.rest.service.BaseCreateService;
import org.springframework.stereotype.Service;

/**
 * {@code ${className}PostService} class creates an entity
 * based on the request given.
 * @author ${author}
 */
@Service
public class ${className}PostService extends BaseCreateService<${className}ServiceRequest, ${className}ServiceResponse> {

    @Override
    protected ${className}ServiceResponse executeCreate(${className}ServiceRequest request) {
        return new ${className}ServiceResponse();
    }
}

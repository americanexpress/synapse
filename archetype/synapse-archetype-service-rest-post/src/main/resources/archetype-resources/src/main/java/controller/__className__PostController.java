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
package ${package}.controller;

import ${package}.config.${className}ServiceConfig;
import ${package}.model.${className}ServiceRequest;
import ${package}.model.${className}ServiceResponse;
import ${package}.service.${className}PostService;
import io.americanexpress.synapse.service.rest.controller.BaseCreateController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@code ${className}PostController} class creates an entity
 * based on the request given.
 * @author ${author}
 */
@RestController
@RequestMapping(${className}ServiceConfig.BASE_URL)
public class ${className}PostController extends BaseCreateController<${className}ServiceRequest, ${className}ServiceResponse, ${className}PostService> {

}

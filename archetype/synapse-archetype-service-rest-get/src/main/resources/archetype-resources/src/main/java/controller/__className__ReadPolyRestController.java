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

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import io.americanexpress.synapse.service.rest.controller.BaseReadPolyController;

import ${package}.service.${className}ReadPolyRestService;
import ${package}.model.${className}ServiceResponse;
import ${package}.model.${className}PolyServiceRequest;
import ${package}.config.${className}RestServiceEndpoint;

/**
 * {@code ${className}ReadPolyRestController} class is the controller
 * used to connect to the ${apiName} REST API.
 * @author ${author}
 *
 */
@RestController
@RequestMapping(${className}RestServiceEndpoint.SERVICE_ENDPOINT)
public class ${className}ReadPolyRestController extends BaseReadPolyController<${className}PolyServiceRequest, ${className}ServiceResponse, ${className}ReadPolyRestService> {

}

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

import ${package}.config.${className}CreateServiceConfig;
import ${package}.model.${className}CreateServiceRequest;
import ${package}.model.${className}CreateServiceResponse;
import ${package}.service.${className}CreateService;
import io.americanexpress.synapse.service.rest.controller.BaseCreateController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@code ${className}CreateController} class creates an entity
 * based on the request given.
 * @author ${author}
 */
@RestController
@RequestMapping(${className}CreateServiceConfig.BASE_URL)
public class ${className}CreateController extends BaseCreateController<${className}CreateServiceRequest, ${className}CreateServiceResponse, ${className}CreateService> {

}

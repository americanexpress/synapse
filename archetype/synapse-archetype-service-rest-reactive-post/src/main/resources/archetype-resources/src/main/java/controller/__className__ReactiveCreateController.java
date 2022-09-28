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

import io.americanexpress.synapse.service.rest.controller.reactive.BaseReactiveCreateController;

import ${package}.config.${className}ReactiveCreateServiceConfig;
import ${package}.model.${className}ReactiveCreateServiceRequest;
import ${package}.model.${className}ReactiveCreateServiceResponse;
import ${package}.service.${className}ReactiveCreateService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@code ${className}ReactiveCreateController} class creates an entity
 * based on the request given.
 * @author ${author}
 */
@RestController
@RequestMapping(${className}ReactiveCreateServiceConfig.BASE_URL)
public class ${className}ReactiveCreateController extends BaseReactiveCreateController<${className}ReactiveCreateServiceRequest,
        ${className}ReactiveCreateServiceResponse, ${className}ReactiveCreateService> {

}

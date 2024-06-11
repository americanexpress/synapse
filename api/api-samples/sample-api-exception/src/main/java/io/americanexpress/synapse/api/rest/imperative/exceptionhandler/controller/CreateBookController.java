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
package io.americanexpress.synapse.api.rest.imperative.exceptionhandler.controller;

import io.americanexpress.service.sample.imperativebook.model.CreateBookServiceRequest;
import io.americanexpress.service.sample.imperativebook.model.CreateBookServiceResponse;
import io.americanexpress.service.sample.imperativebook.service.CreateBookService;
import io.americanexpress.synapse.api.rest.imperative.controller.BaseCreateImperativeRestController;
import io.americanexpress.synapse.framework.exception.ApplicationRequestTimeoutException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@code CreateBookController} maps the endpoint for creating a new book object.
 */
@RequestMapping("/api/exception-handler")
@RestController
public class CreateBookController extends BaseCreateImperativeRestController<CreateBookServiceRequest, CreateBookServiceResponse, CreateBookService> {

    public ResponseEntity<CreateBookServiceResponse>
        create(@RequestHeader HttpHeaders headers, @RequestBody CreateBookServiceRequest createBookServiceRequest) {
        throw new ApplicationRequestTimeoutException("Test Message");
    }
}

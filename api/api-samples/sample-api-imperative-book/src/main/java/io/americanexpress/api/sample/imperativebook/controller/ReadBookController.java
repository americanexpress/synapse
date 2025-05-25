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
package io.americanexpress.api.sample.imperativebook.controller;

import io.americanexpress.service.sample.imperativebook.model.ReadBookServiceRequest;
import io.americanexpress.service.sample.imperativebook.model.ReadBookServiceResponse;
import io.americanexpress.service.sample.imperativebook.service.ReadBookService;
import io.americanexpress.synapse.api.rest.imperative.controller.BaseReadMonoImperativeRestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static io.americanexpress.api.sample.imperativebook.config.BookEndpoint.BOOKS_ENDPOINT;

/**
 * Controller for handling HTTP GET requests to read book details. Extends the base class to utilize common read
 * functionality.
 *
 * @author Aziz Ali
 */
@RequestMapping(BOOKS_ENDPOINT)
@RestController
public class ReadBookController extends BaseReadMonoImperativeRestController<
        ReadBookServiceRequest,
        ReadBookServiceResponse,
        ReadBookService> {
}

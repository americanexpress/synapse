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
package io.americanexpress.service.book.rest.controller;

import io.americanexpress.service.book.rest.config.BookEndpoints;
import io.americanexpress.service.book.rest.service.DeleteBookReactiveService;
import io.americanexpress.synapse.service.rest.controller.reactive.BaseDeleteReactiveController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@code DeleteBookReactiveController} is the controller class for deleting a book in the Cassandra Book database.
 */
@RestController
@RequestMapping(BookEndpoints.BOOK_ENDPOINT)
public class DeleteBookReactiveController extends BaseDeleteReactiveController<DeleteBookReactiveService> {
}

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
package io.americanexpress.function.greeting.rest.router;

import io.americanexpress.service.book.rest.config.BookConfig;
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.service.book.rest.model.ReadPolyBookRequest;
import io.americanexpress.service.book.rest.service.ReadPolyBookService;
import io.americanexpress.synapse.service.rest.controller.BaseReadPolyController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ReadPolyBookController retrieves a collection of books and the results can be filtered by some given parameters.
 */
@RestController
@RequestMapping(BookConfig.BOOK_ENDPOINT)
public class ReadPolyBookController extends BaseReadPolyController<ReadPolyBookRequest, ReadBookResponse, ReadPolyBookService> {

}
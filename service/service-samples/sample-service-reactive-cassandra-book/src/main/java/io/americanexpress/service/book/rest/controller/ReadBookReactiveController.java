package io.americanexpress.service.book.rest.controller;

import io.americanexpress.service.book.rest.config.BookEndpoints;
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.service.book.rest.service.ReadBookReactiveService;
import io.americanexpress.synapse.service.rest.controller.reactive.BaseGetMonoReactiveController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@code ReadBookReactiveController} is the controller class for retrieving a book from the Cassandra Book database.
 */
@RestController
@RequestMapping(BookEndpoints.BOOK_ENDPOINT)
public class ReadBookReactiveController extends BaseGetMonoReactiveController<ReadBookResponse, ReadBookReactiveService> {
}

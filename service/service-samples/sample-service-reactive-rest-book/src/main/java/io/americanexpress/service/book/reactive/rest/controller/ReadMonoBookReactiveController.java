package io.americanexpress.service.book.reactive.rest.controller;

import io.americanexpress.service.book.reactive.rest.config.BookConfig;
import io.americanexpress.service.book.reactive.rest.model.ReadBookRequest;
import io.americanexpress.service.book.reactive.rest.model.ReadBookResponse;
import io.americanexpress.service.book.reactive.rest.service.ReadBookReactiveService;
import io.americanexpress.synapse.service.reactive.rest.controller.BaseReadMonoReactiveController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@code ReadMonoBookReactiveController} retrieves ReadBookResponses by ReadPolyBookRequest
 */
@RestController
@RequestMapping(BookConfig.BOOK_ENDPOINT)
public class ReadMonoBookReactiveController extends BaseReadMonoReactiveController<ReadBookRequest, ReadBookResponse, ReadBookReactiveService> {
}

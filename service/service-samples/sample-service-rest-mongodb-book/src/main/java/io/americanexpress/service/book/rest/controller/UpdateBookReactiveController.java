package io.americanexpress.service.book.rest.controller;

import io.americanexpress.service.book.rest.config.BookEndpoints;
import io.americanexpress.service.book.rest.model.UpdateBookRequest;
import io.americanexpress.service.book.rest.service.UpdateBookReactiveService;
import io.americanexpress.synapse.service.rest.controller.reactive.BaseUpdateReactiveController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@code UpdateBookReactiveController} updates a book given an UpdateBookRequest.
 */
@RestController
@RequestMapping(BookEndpoints.BOOK_ENDPOINT)
public class UpdateBookReactiveController extends BaseUpdateReactiveController<UpdateBookRequest, UpdateBookReactiveService> {
}

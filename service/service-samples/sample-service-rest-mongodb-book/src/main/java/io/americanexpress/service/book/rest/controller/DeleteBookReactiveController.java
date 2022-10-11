package io.americanexpress.service.book.rest.controller;

import io.americanexpress.service.book.rest.config.BookEndpoints;
import io.americanexpress.service.book.rest.service.DeleteBookReactiveService;
import io.americanexpress.synapse.service.rest.controller.reactive.BaseDeleteReactiveController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@code DeleteBookReactiveController} deletes a book given the title of the book.
 */
@RestController
@RequestMapping(BookEndpoints.BOOK_ENDPOINT)
public class DeleteBookReactiveController extends BaseDeleteReactiveController<DeleteBookReactiveService> {
}

package io.americanexpress.service.book.rest.controller;

import io.americanexpress.service.book.rest.config.BookConfig;
import io.americanexpress.service.book.rest.service.ReactiveDeleteBookService;
import io.americanexpress.synapse.service.rest.controller.reactive.BaseDeleteReactiveController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@code ReactiveDeleteBookController} deletes a book given the title of the book.
 */
@RestController
@RequestMapping(BookConfig.BOOK_ENDPOINT)
public class ReactiveDeleteBookController extends BaseDeleteReactiveController<ReactiveDeleteBookService> {
}

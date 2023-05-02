package io.americanexpress.service.book.rest.controller;

import io.americanexpress.service.book.rest.config.BookEndpoints;
import io.americanexpress.service.book.rest.model.CreateBookRequest;
import io.americanexpress.service.book.rest.model.CreateBookResponse;
import io.americanexpress.service.book.rest.service.CreateBookReactiveService;
import io.americanexpress.synapse.service.reactive.rest.controller.BaseCreateReactiveController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@code CreateBookReactiveController}
 */
@RestController
@RequestMapping(BookEndpoints.BOOK_ENDPOINT)
public class CreateBookReactiveController extends BaseCreateReactiveController<CreateBookRequest, CreateBookResponse, CreateBookReactiveService> {
}

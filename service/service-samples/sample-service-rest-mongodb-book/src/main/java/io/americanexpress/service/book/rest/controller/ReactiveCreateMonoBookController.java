package io.americanexpress.service.book.rest.controller;

import io.americanexpress.service.book.rest.config.BookConfig;
import io.americanexpress.service.book.rest.model.CreateBookRequest;
import io.americanexpress.service.book.rest.model.CreateBookResponse;
import io.americanexpress.service.book.rest.service.ReactiveCreateMonoBookService;
import io.americanexpress.synapse.service.rest.controller.reactive.BaseReactiveCreateController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BookConfig.BOOK_ENDPOINT)
public class ReactiveCreateMonoBookController extends BaseReactiveCreateController<CreateBookRequest, CreateBookResponse, ReactiveCreateMonoBookService> {
}

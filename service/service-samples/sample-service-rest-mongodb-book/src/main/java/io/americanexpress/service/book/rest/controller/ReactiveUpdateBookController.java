package io.americanexpress.service.book.rest.controller;

import io.americanexpress.service.book.rest.config.BookConfig;
import io.americanexpress.service.book.rest.model.UpdateBookRequest;
import io.americanexpress.service.book.rest.service.ReactiveUpdateBookService;
import io.americanexpress.synapse.service.rest.controller.reactive.v2.BaseReactiveUpdateController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BookConfig.BOOK_ENDPOINT)
public class ReactiveUpdateBookController extends BaseReactiveUpdateController<UpdateBookRequest, ReactiveUpdateBookService> {
}

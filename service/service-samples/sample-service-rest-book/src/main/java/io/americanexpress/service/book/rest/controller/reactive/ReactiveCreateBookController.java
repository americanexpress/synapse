package io.americanexpress.service.book.rest.controller.reactive;

import io.americanexpress.service.book.rest.config.BookConfig;
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.service.book.rest.model.ReadPolyBookRequest;
import io.americanexpress.service.book.rest.service.reactive.ReactiveCreateBookService;
import io.americanexpress.synapse.service.rest.controller.reactive.BaseReactiveCreateController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BookConfig.BOOK_REACTIVE_ENDPOINT)
public class ReactiveCreateBookController extends BaseReactiveCreateController<ReadPolyBookRequest, ReadBookResponse, ReactiveCreateBookService> {

}

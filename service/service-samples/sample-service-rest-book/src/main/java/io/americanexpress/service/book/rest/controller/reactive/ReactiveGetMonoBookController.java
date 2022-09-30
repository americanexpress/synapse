package io.americanexpress.service.book.rest.controller.reactive;

import io.americanexpress.service.book.rest.config.BookConfig;
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.service.book.rest.service.reactive.ReactiveGetMonoBookService;
import io.americanexpress.synapse.service.rest.controller.reactive.BaseReactiveGetMonoController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BookConfig.BOOK_REACTIVE_ENDPOINT)
public class ReactiveGetMonoBookController extends BaseReactiveGetMonoController<ReadBookResponse, ReactiveGetMonoBookService> {
}

package io.americanexpress.service.book.rest.controller.reactive;

import io.americanexpress.service.book.rest.config.BookConfig;
import io.americanexpress.service.book.rest.model.ReadBookResponses;
import io.americanexpress.service.book.rest.service.reactive.ReactiveGetPolyBookService;
import io.americanexpress.synapse.service.rest.controller.reactive.BaseReactiveGetPolyController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BookConfig.BOOK_REACTIVE_ENDPOINT)
public class ReactiveGetPolyBookController extends BaseReactiveGetPolyController<ReadBookResponses, ReactiveGetPolyBookService> {
}

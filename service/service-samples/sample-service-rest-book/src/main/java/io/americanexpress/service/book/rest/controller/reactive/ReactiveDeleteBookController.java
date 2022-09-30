package io.americanexpress.service.book.rest.controller.reactive;

import io.americanexpress.service.book.rest.config.BookConfig;
import io.americanexpress.service.book.rest.service.reactive.ReactiveDeleteBookService;
import io.americanexpress.synapse.service.rest.controller.reactive.BaseReactiveDeleteMonoController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BookConfig.BOOK_REACTIVE_ENDPOINT)
public class ReactiveDeleteBookController extends BaseReactiveDeleteMonoController<ReactiveDeleteBookService> {
}

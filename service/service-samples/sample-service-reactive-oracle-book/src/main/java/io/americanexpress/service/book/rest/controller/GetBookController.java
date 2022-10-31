package io.americanexpress.service.book.rest.controller;

import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.service.book.rest.service.GetBookService;
import io.americanexpress.synapse.service.rest.controller.reactive.BaseGetMonoReactiveController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static io.americanexpress.service.book.rest.utils.Constants.BOOK_ENDPOINT;

@RestController
@RequestMapping(BOOK_ENDPOINT)
public class GetBookController extends BaseGetMonoReactiveController<ReadBookResponse, GetBookService> {
}

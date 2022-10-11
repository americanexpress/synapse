package io.americanexpress.service.book.rest.controller;

import io.americanexpress.service.book.rest.config.BookEndpoints;
import io.americanexpress.service.book.rest.model.ReadBookRequest;
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.service.book.rest.service.ReadBookService;
import io.americanexpress.synapse.service.rest.controller.BaseReadMonoController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BookEndpoints.BOOK_ENDPOINT)
public class ReadBookController extends BaseReadMonoController<ReadBookRequest, ReadBookResponse, ReadBookService> {
}

package io.americanexpress.service.book.rest.controller;

import io.americanexpress.service.book.rest.config.BookEndpoints;
import io.americanexpress.service.book.rest.service.DeleteBookService;
import io.americanexpress.synapse.service.rest.controller.BaseDeleteController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BookEndpoints.BOOK_ENDPOINT)
public class DeleteBookController extends BaseDeleteController<DeleteBookService> {
}

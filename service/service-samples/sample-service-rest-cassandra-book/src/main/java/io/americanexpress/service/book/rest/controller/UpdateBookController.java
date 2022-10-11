package io.americanexpress.service.book.rest.controller;

import io.americanexpress.service.book.rest.config.BookEndpoints;
import io.americanexpress.service.book.rest.model.UpdateBookRequest;
import io.americanexpress.service.book.rest.service.UpdateBookService;
import io.americanexpress.synapse.service.rest.controller.BaseUpdateController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BookEndpoints.BOOK_ENDPOINT)
public class UpdateBookController extends BaseUpdateController<UpdateBookRequest, UpdateBookService> {
}

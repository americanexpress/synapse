package io.americanexpress.service.book.rest.controller;

import io.americanexpress.service.book.rest.service.DeleteBookService;
import io.americanexpress.synapse.service.rest.controller.BaseDeleteController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static io.americanexpress.service.book.rest.utils.Constants.BOOK_ENDPOINT;

@RestController
@RequestMapping(BOOK_ENDPOINT)
public class DeleteBookController extends BaseDeleteController<DeleteBookService> {
}

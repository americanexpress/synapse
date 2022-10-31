package io.americanexpress.service.book.rest.controller;

import io.americanexpress.service.book.rest.model.ReadBookRequest;
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.service.book.rest.service.ReadBookService;
import io.americanexpress.synapse.service.rest.controller.BaseReadMonoController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static io.americanexpress.service.book.rest.utils.Constants.BOOK_ENDPOINT;


/**
 * {@code ReadBookController} is the controller class for reading a book in the Cassandra Book database.
 */
@RestController
@RequestMapping(BOOK_ENDPOINT)
public class ReadBookController extends BaseReadMonoController<ReadBookRequest, ReadBookResponse, ReadBookService> {
}

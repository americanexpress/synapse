package io.americanexpress.function.book.rest.router;

import io.americanexpress.service.book.rest.config.BookConfig;
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.service.book.rest.service.ReadMonoBookService;
import io.americanexpress.synapse.service.rest.controller.BaseGetMonoController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ReadMonoBookController retrieves a Book Response given its unique identifier.
 */
@RestController
@RequestMapping(BookConfig.BOOK_ENDPOINT)
public class ReadMonoBookController extends BaseGetMonoController<ReadBookResponse, ReadMonoBookService> {

}



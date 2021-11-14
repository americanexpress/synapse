package io.americanexpress.service.book.rest.controller;

import io.americanexpress.service.book.rest.config.BookConfig;
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.service.book.rest.service.GetBookService;
import io.americanexpress.synapse.service.rest.controller.BaseGetMonoController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Get Book Service", description = "Provides book information based on identifier")
@RestController
@RequestMapping(BookConfig.BOOK_ENDPOINT)
public class GetBookController extends BaseGetMonoController<ReadBookResponse, GetBookService> {

}
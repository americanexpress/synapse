package io.americanexpress.service.book.controller;

import io.americanexpress.service.book.config.BookConfig;
import io.americanexpress.service.book.model.ReadBookResponse;
import io.americanexpress.service.book.service.GetBookService;
import io.americanexpress.synapse.service.rest.controller.BaseGetMonoController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BookConfig.BOOK_ENDPOINT)
@Api("Get Book Service")
public class GetBookController extends BaseGetMonoController<ReadBookResponse, GetBookService> {

}
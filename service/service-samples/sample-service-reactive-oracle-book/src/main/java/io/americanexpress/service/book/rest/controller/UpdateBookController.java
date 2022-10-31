package io.americanexpress.service.book.rest.controller;

import io.americanexpress.service.book.rest.model.UpdateBookRequest;
import io.americanexpress.service.book.rest.service.UpdateBookService;
import io.americanexpress.synapse.service.rest.controller.reactive.BaseUpdateReactiveController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static io.americanexpress.service.book.rest.utils.Constants.BOOK_ENDPOINT;

@RestController
@RequestMapping(BOOK_ENDPOINT)
public class UpdateBookController extends BaseUpdateReactiveController<UpdateBookRequest, UpdateBookService> {
}

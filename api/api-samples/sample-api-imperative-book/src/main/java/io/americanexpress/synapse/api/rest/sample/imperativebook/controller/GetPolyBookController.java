package io.americanexpress.synapse.api.rest.sample.imperativebook.controller;

import io.americanexpress.service.sample.imperativebook.model.GetPolyBookServiceRequest;
import io.americanexpress.service.sample.imperativebook.model.GetPolyBookServiceResponse;
import io.americanexpress.service.sample.imperativebook.service.GetPolyBookService;
import io.americanexpress.synapse.api.rest.imperative.controller.BaseGetPolyImperativeRestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static io.americanexpress.synapse.api.rest.sample.imperativebook.config.BookEndpoint.BOOK_ENDPOINT;

/**
 * {@code GetPolyBookController} gets multiple book resources by a path variable.
 *
 * @author Tanvir Islam
 */
@RestController
@RequestMapping(BOOK_ENDPOINT)
public class GetPolyBookController extends BaseGetPolyImperativeRestController<GetPolyBookServiceRequest, GetPolyBookServiceResponse, GetPolyBookService> {
}

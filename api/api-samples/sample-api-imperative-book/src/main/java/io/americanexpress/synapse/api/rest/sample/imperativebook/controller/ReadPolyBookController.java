package io.americanexpress.synapse.api.rest.sample.imperativebook.controller;

import io.americanexpress.service.sample.imperativebook.model.ReadPolyBookServiceRequest;
import io.americanexpress.service.sample.imperativebook.model.ReadPolyBookServiceResponse;
import io.americanexpress.service.sample.imperativebook.service.ReadPolyBookService;
import io.americanexpress.synapse.api.rest.imperative.controller.BaseReadPolyImperativeRestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static io.americanexpress.synapse.api.rest.sample.imperativebook.config.BookEndpoint.BOOK_ENDPOINT;

/**
 * {@code ReadPolyBookController} reads multiple book resources with specific criteria
 *
 * @author Tanvir Islam
 */
@RestController
@RequestMapping(BOOK_ENDPOINT)
public class ReadPolyBookController extends BaseReadPolyImperativeRestController<ReadPolyBookServiceRequest, ReadPolyBookServiceResponse, ReadPolyBookService> {
}

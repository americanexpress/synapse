package io.americanexpress.api.sample.imperativebook.controller;

import io.americanexpress.service.sample.imperativebook.model.CreateBookServiceRequest;
import io.americanexpress.service.sample.imperativebook.model.CreateBookServiceResponse;
import io.americanexpress.service.sample.imperativebook.service.CreateBookService;
import io.americanexpress.synapse.api.rest.imperative.controller.BaseCreateImperativeRestController;

/**
 * The type CreateBookController.
 *
 * @see BaseCreateImperativeRestController
 * @author Francois Gutt
 */
public class CreateBookController extends BaseCreateImperativeRestController<CreateBookServiceRequest, CreateBookServiceResponse, CreateBookService> {

}

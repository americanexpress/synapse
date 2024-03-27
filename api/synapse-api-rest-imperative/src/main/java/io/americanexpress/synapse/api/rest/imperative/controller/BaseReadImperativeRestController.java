package io.americanexpress.synapse.api.rest.imperative.controller;

import io.americanexpress.synapse.service.imperative.model.BaseServiceRequest;
import io.americanexpress.synapse.service.imperative.model.BaseServiceResponse;
import io.americanexpress.synapse.service.imperative.service.BaseCreateImperativeService;

public class BaseReadImperativeRestController<
        I extends BaseServiceRequest,
        O extends BaseServiceResponse,
        S extends BaseCreateImperativeService<I, O>
        > extends BaseController<S> {


    
}

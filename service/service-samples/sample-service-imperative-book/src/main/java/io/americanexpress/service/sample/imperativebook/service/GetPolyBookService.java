package io.americanexpress.service.sample.imperativebook.service;

import io.americanexpress.service.sample.imperativebook.model.GetPolyBookServiceRequest;
import io.americanexpress.service.sample.imperativebook.model.GetPolyBookServiceResponse;
import io.americanexpress.service.sample.imperativebook.service.helper.GetBookServiceResponseCreator;
import io.americanexpress.synapse.service.imperative.model.PageResponse;
import io.americanexpress.synapse.service.imperative.service.BaseService;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * {@code GetPolyBookService} gets multiple book resources.
 *
 * @author Tanvir Islam
 */
@Component
public class GetPolyBookService extends BaseService<GetPolyBookServiceRequest, PageResponse<GetPolyBookServiceResponse>> {

    /**
     * Creates the book response.
     */
    private final GetBookServiceResponseCreator getBookServiceResponseCreator;

    /**
     * Constructor creates a new instance of ReadBookService with the given values.
     *
     * @param getBookServiceResponseCreator the base book service response creator
     */
    public GetPolyBookService(GetBookServiceResponseCreator getBookServiceResponseCreator) {
        this.getBookServiceResponseCreator = getBookServiceResponseCreator;
    }

    /**
     * Gets multiple book resources.
     *
     * @param request the request object.
     * @return a list of book resources.
     */
    @Override
    protected PageResponse<GetPolyBookServiceResponse> doExecute(GetPolyBookServiceRequest request) {
        var response = getBookServiceResponseCreator.create();
        return new PageResponse<>(List.of(response, response));
    }
}

package io.americanexpress.service.sample.imperativebook.service;

import io.americanexpress.service.sample.imperativebook.model.ReadPolyBookServiceRequest;
import io.americanexpress.service.sample.imperativebook.model.ReadPolyBookServiceResponse;
import io.americanexpress.service.sample.imperativebook.service.helper.ReadBookServiceResponseCreator;
import io.americanexpress.synapse.service.imperative.model.PageResponse;
import io.americanexpress.synapse.service.imperative.service.BaseService;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * {@code ReadPolyBookService} reads multiple book resources with specified criteria.
 *
 * @author Tanvir Islam
 */
@Component
public class ReadPolyBookService extends BaseService<ReadPolyBookServiceRequest, PageResponse<ReadPolyBookServiceResponse>> {

    /**
     * Creates the book response.
     */
    private final ReadBookServiceResponseCreator readBookServiceResponseCreator;

    /**
     * Constructor creates a new instance of ReadBookService with the given values.
     *
     * @param readBookServiceResponseCreator the base book service response creator
     */
    public ReadPolyBookService(ReadBookServiceResponseCreator readBookServiceResponseCreator) {
        this.readBookServiceResponseCreator = readBookServiceResponseCreator;
    }

    /**
     * Reads multiple book resources with specified criteria.
     *
     * @param request the request object.
     * @return a list of book resources.
     */
    @Override
    protected PageResponse<ReadPolyBookServiceResponse> doExecute(ReadPolyBookServiceRequest request) {
        var response = readBookServiceResponseCreator.create(request);
        var otherResponse = readBookServiceResponseCreator.create(request);
        otherResponse.setCost(35);
        return new PageResponse<>(List.of(response, otherResponse), request.getPageInformation());
    }
}

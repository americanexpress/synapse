package io.americanexpress.service.sample.imperativebook.service.helper;

import io.americanexpress.service.sample.imperativebook.model.GetPolyBookServiceResponse;
import org.springframework.stereotype.Component;

/**
 * {@code GetBookServiceResponseCreator} creates the response for getting multiple books.
 *
 * @author Tanvir Islam
 */
@Component
public class GetBookServiceResponseCreator {

    /**
     * Creates a new {@link GetPolyBookServiceResponse} object.
     *
     * @return a new {@link GetPolyBookServiceResponse} object.
     */
    public GetPolyBookServiceResponse create() {
        var getPolyBookServiceResponse = new GetPolyBookServiceResponse();
        getPolyBookServiceResponse.setTitle("Harry Potter");
        getPolyBookServiceResponse.setAuthor("J.K. Rowling");
        getPolyBookServiceResponse.setCost(15);
        getPolyBookServiceResponse.setYear(2005);
        getPolyBookServiceResponse.setPublisher("Publishing Co.");
        return getPolyBookServiceResponse;
    }
}

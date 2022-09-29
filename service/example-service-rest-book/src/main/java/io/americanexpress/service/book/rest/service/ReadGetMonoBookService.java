package io.americanexpress.service.book.rest.service;

import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.synapse.service.rest.service.BaseGetPolyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ReadGetMonoBookService extends BaseGetPolyService<ReadBookResponse> {
    @Override
    protected Page<ReadBookResponse> executeRead() {
        return new PageImpl<>(populateReadBookResponse());
    }

    private List<ReadBookResponse> populateReadBookResponse() {
        ReadBookResponse response = new ReadBookResponse();
        response.setIdentifier("1");
        response.setAuthor("author1");
        response.setTitle("title1");

        ReadBookResponse response2 = new ReadBookResponse();
        response.setIdentifier("2");
        response.setAuthor("author2");
        response.setTitle("title2");

        ReadBookResponse response3 = new ReadBookResponse();
        response.setIdentifier("3");
        response.setAuthor("author3");
        response.setTitle("title3");

        return Arrays.asList(response, response2, response3);
    }
}

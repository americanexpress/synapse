package io.americanexpress.service.book.rest.service.reactive;

import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.service.book.rest.model.ReadBookResponses;
import io.americanexpress.synapse.service.rest.service.BaseGetPolyReactiveService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

@Service
public class ReactiveGetPolyBookService extends BaseGetPolyReactiveService<ReadBookResponses> {

    @Override
    protected Flux<ReadBookResponses> executeRead() {
        logger.entry();
        logger.debug("emulating get read...");
        return Flux.just(new ReadBookResponses(this.populateReadBookList()));
    }

    private List<ReadBookResponse> populateReadBookList() {
        return Arrays.asList(
                new ReadBookResponse("1", "title1", "author1"),
                new ReadBookResponse("2", "title2", "author2"),
                new ReadBookResponse("3", "title3", "author3"));
    }
}

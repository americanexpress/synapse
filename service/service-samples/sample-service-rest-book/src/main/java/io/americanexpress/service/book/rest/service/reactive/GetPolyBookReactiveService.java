/*
 * Copyright 2020 American Express Travel Related Services Company, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.americanexpress.service.book.rest.service.reactive;

import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.service.book.rest.model.ReadBookResponses;
import io.americanexpress.synapse.service.rest.service.reactive.BaseGetPolyReactiveService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

/**
 * {@code GetPolyBookReactiveService} service layer for retrieving multiple book resources
 */
@Service
public class GetPolyBookReactiveService extends BaseGetPolyReactiveService<ReadBookResponses> {

    /**
     * Overriding executeRead
     * @return
     */
    @Override
    protected Flux<ReadBookResponses> executeRead() {
        logger.entry();
        logger.debug("emulating get read...");
        return Flux.just(new ReadBookResponses(this.populateReadBookList()));
    }

    /**
     * Emulates retrieving multiple booking resource.
     * @return
     */
    private List<ReadBookResponse> populateReadBookList() {
        return Arrays.asList(
                new ReadBookResponse("1", "title1", "author1"),
                new ReadBookResponse("2", "title2", "author2"),
                new ReadBookResponse("3", "title3", "author3"));
    }
}

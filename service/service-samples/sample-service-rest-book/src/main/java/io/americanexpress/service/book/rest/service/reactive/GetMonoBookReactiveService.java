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
import io.americanexpress.synapse.service.rest.service.reactive.BaseGetMonoReactiveService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * {@code GetMonoBookReactiveService} service layer for retrieving a book resource
 */
@Service
public class GetMonoBookReactiveService extends BaseGetMonoReactiveService<ReadBookResponse> {

    /**
     * Overriding executeRead
     * @param request
     * @return
     */
    @Override
    protected Mono<ReadBookResponse> executeRead(HttpHeaders headers, String request) {
        logger.entry(request);
        logger.debug("emulating get mono read...");
        return Mono.just(populateSingleBookResponse(request));
    }

    /**
     * Emulates retrieving BookResponse object
     * @param identifier an identifier
     * @return a read book response
     */
    private ReadBookResponse populateSingleBookResponse(String identifier) {
        ReadBookResponse response = new ReadBookResponse();
        response.setId(identifier);
        response.setTitle("title");
        response.setAuthor("author");
        return response;
    }
}

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
package io.americanexpress.service.book.reactive.rest.service;

import io.americanexpress.service.book.reactive.rest.model.ReadBookRequest;
import io.americanexpress.service.book.reactive.rest.model.ReadBookResponse;
import io.americanexpress.synapse.service.reactive.rest.service.BaseReadMonoReactiveService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * {@code ReadMonoBookReactiveService} service layer for retrieving a book resources
 */
@Service
public class ReadBookReactiveService extends BaseReadMonoReactiveService<ReadBookRequest, ReadBookResponse> {

    /**
     * Overriding executeRead.
     * @param request a read mono book service request
     * @return a mono read book response
     */
    @Override
    protected Mono<ReadBookResponse> executeRead(HttpHeaders headers, ReadBookRequest request) {
        logger.entry(request);
        logger.debug("emulating post read...");
        return Mono.just(new ReadBookResponse("1", "title", "title"));
    }
}

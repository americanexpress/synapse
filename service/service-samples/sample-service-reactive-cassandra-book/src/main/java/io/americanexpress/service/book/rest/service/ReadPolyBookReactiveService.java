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
package io.americanexpress.service.book.rest.service;

import io.americanexpress.data.book.entity.BookEntity;
import io.americanexpress.data.book.repository.BookRepository;
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.service.book.rest.model.ReadBookPaginatedRequest;
import io.americanexpress.service.book.rest.service.helper.ReadBookResponseCreator;
import io.americanexpress.synapse.service.rest.model.PageInformation;
import io.americanexpress.synapse.service.rest.service.reactive.BaseReadPolyReactiveService;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * {@code ReadPolyBookReactiveService} is the service class for retrieving books from the Cassandra Book database.
 */
@Service
public class ReadPolyBookReactiveService extends BaseReadPolyReactiveService<ReadBookPaginatedRequest, ReadBookResponse> {

    private final BookRepository bookRepository;

    private Map<PageInformation, ByteBuffer> pageInformationPagingStateMap = new HashMap<>();


    /**
     * Instantiates a new Read poly book reactive service.
     *
     * @param bookRepository the book repository
     */
    public ReadPolyBookReactiveService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    protected Flux<ReadBookResponse> executeRead(HttpHeaders headers, ReadBookPaginatedRequest request) {
        Flux<ReadBookResponse> readBookResponseFlux;
        if(request.getPageInformation() != null) {
            Pageable pageable = PageRequest.of(0, request.getPageInformation().getSize());
            Pageable cassandraPageable = CassandraPageRequest.of(pageable, getPageState(request.getPageInformation()));
            Mono<Slice<BookEntity>> bookEntityList = bookRepository.findAllBy(cassandraPageable);
            bookEntityList.map(Slice::nextOrLastPageable).subscribe(nextPageable -> addPageState(request.getPageInformation(), (CassandraPageRequest) nextPageable));
            return bookEntityList.flatMapMany(Flux::fromIterable).map(ReadBookResponseCreator::create);
        }else {
            readBookResponseFlux = bookRepository.findAll()
                    .map(ReadBookResponseCreator::create)
                    .switchIfEmpty(Flux.empty());
        }
       return readBookResponseFlux;
    }
    private ByteBuffer getPageState(PageInformation pageInformation) {
        return pageInformationPagingStateMap.get(pageInformation);
    }

    private void addPageState(PageInformation pageInformation, CassandraPageRequest nextOrLastPageable) {
        System.out.println("AddPageState");
        if(nextOrLastPageable.getPagingState() != null ) {
            pageInformation.setPage(pageInformation.getPage() + 1);
            pageInformationPagingStateMap.put(pageInformation, nextOrLastPageable.getPagingState());
        }
    }
}


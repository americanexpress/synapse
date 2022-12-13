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
import io.americanexpress.service.book.rest.model.ReadBookPaginatedRequest;
import io.americanexpress.service.book.rest.model.ReadBookPaginatedResponse;
import io.americanexpress.service.book.rest.service.helper.ReadBookResponseCreator;
import io.americanexpress.synapse.service.rest.service.BaseReadPolyService;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@code ReadPolyBookService} is the service class for retrieving books from the Cassandra Book database.
 */
@Service
public class ReadPolyBookService extends BaseReadPolyService<ReadBookPaginatedRequest, ReadBookPaginatedResponse> {

    private final BookRepository bookRepository;

    /**
     * Instantiates a new Read poly book service.
     *
     * @param bookRepository the book repository
     */
    public ReadPolyBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    protected Page<ReadBookPaginatedResponse> executeRead(HttpHeaders headers, ReadBookPaginatedRequest request) {
        ReadBookPaginatedResponse readBookPaginatedResponse;
        if(request.getPageInformation() != null) {
            Pageable cassandraPageable = createPageable(request);
            Slice<BookEntity> bookEntitySlice = bookRepository.findAll(cassandraPageable);
            CassandraPageRequest nextPageRequest = (CassandraPageRequest) bookEntitySlice.nextOrLastPageable();
            readBookPaginatedResponse = createReadBookPaginatedResponse(bookEntitySlice.getContent(), nextPageRequest);
        } else {
            readBookPaginatedResponse = createReadBookPaginatedResponse(bookRepository.findAll(), null);
        }
        return new PageImpl<>(List.of(readBookPaginatedResponse));
    }

    private Pageable createPageable(ReadBookPaginatedRequest request) {
        Pageable pageable = PageRequest.of(0, request.getPageInformation().getSize());
        return CassandraPageRequest.of(pageable, request.getPageState() == null ? null : request.getPageState());
    }

    private ReadBookPaginatedResponse createReadBookPaginatedResponse(List<BookEntity> bookEntities, CassandraPageRequest nextPage) {
        ReadBookPaginatedResponse readBookPaginatedResponse = new ReadBookPaginatedResponse();
        readBookPaginatedResponse.setReadBookResponses(bookEntities.stream().map(ReadBookResponseCreator::create).toList());
        if(nextPage != null) {
            readBookPaginatedResponse.setNextPageState(nextPage.getPagingState());
        }
        return  readBookPaginatedResponse;
    }

}

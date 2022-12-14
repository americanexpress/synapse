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
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.service.book.rest.service.helper.ReadBookResponseCreator;
import io.americanexpress.synapse.service.rest.model.PageInformation;
import io.americanexpress.synapse.service.rest.service.BaseReadPolyService;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@code ReadPolyBookService} is the service class for retrieving books from the Cassandra Book database.
 */
@Service
public class ReadPolyBookService extends BaseReadPolyService<ReadBookPaginatedRequest, ReadBookResponse> {

    private final BookRepository bookRepository;

    private Map<PageInformation, ByteBuffer> pageInformationPagingStateMap = new HashMap<>();

    /**
     * Instantiates a new Read poly book service.
     *
     * @param bookRepository the book repository
     */
    public ReadPolyBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    protected Page<ReadBookResponse> executeRead(HttpHeaders headers, ReadBookPaginatedRequest request) {
        List<ReadBookResponse> readBookResponses;
        if(request.getPageInformation() != null) {
            Pageable pageable = PageRequest.of(0, request.getPageInformation().getSize());
            Pageable cassandraPageable = CassandraPageRequest.of(pageable, getPageState(request.getPageInformation()));

            Slice<BookEntity> bookEntitySlice = bookRepository.findAll(cassandraPageable);
            addPageState(request.getPageInformation(), (CassandraPageRequest) bookEntitySlice.nextOrLastPageable());

            readBookResponses = bookEntitySlice.getContent().stream().map(ReadBookResponseCreator::create).toList();
        }else {
            readBookResponses = bookRepository.findAll().stream().map(ReadBookResponseCreator::create).toList();
        }
        return new PageImpl<>(readBookResponses);
    }

    private ByteBuffer getPageState(PageInformation pageInformation) {
        return pageInformationPagingStateMap.get(pageInformation);
    }

    private void addPageState(PageInformation pageInformation, CassandraPageRequest nextOrLastPageable) {
        if(nextOrLastPageable.getPagingState() != null && pageInformationPagingStateMap.containsKey(pageInformation)) {
            pageInformation.setPage(pageInformation.getPage() + 1);
            pageInformationPagingStateMap.put(pageInformation, nextOrLastPageable.getPagingState());
        }
    }
}

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

import io.americanexpress.data.book.repository.BookRepository;
import io.americanexpress.service.book.rest.model.ReadBookPaginatedRequest;
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.service.book.rest.service.helper.ReadBookResponseCreator;
import io.americanexpress.synapse.service.rest.service.BaseReadPolyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@code ReadPolyBookService} is the service class for retrieving books from the Cassandra Book database.
 */
@Service
public class ReadPolyBookService extends BaseReadPolyService<ReadBookPaginatedRequest, ReadBookResponse> {

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
    protected Page<ReadBookResponse> executeRead(HttpHeaders headers, ReadBookPaginatedRequest request) {
        // Without using pagination in database
        Page<ReadBookResponse> readBookResponses;
        List<ReadBookResponse> bookResponseList = bookRepository.findAll().stream().map(ReadBookResponseCreator::create).toList();
        if(request.getPageInformation() != null) {
            Pageable pageable = PageRequest.of(request.getPageInformation().getPage(), request.getPageInformation().getSize());
            int start = (int) pageable.getOffset();
            int end = (Math.min((start + pageable.getPageSize()), bookResponseList.size()));
            readBookResponses = new PageImpl<>(bookResponseList.subList(start, end), pageable, bookResponseList.size());
        }else {
            readBookResponses = new PageImpl<>(bookResponseList);
        }
        return readBookResponses;
    }

}

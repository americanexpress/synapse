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

import io.americanexpress.data.book.dao.BookRepository;
import io.americanexpress.data.book.entity.BookEntity;
import io.americanexpress.service.book.rest.model.ReadBookRequest;
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.synapse.service.rest.service.BaseReadPolyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code ReactiveReadPolyBookService} retrieves books from the database given request.
 */
@Service
public class ReactiveReadPolyBookService extends BaseReadPolyService<ReadBookRequest, ReadBookResponse> {

    private final BookRepository bookRepository;

    @Autowired
    public ReactiveReadPolyBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    protected Page<ReadBookResponse> executeRead(ReadBookRequest request) {
        Flux<BookEntity> bookEntityFlux = bookRepository.findByTitle(request.getTitle());
        List<BookEntity> bookEntityList = bookEntityFlux.collectList().block();
        List<ReadBookResponse> bookResponses = new ArrayList<>();
        if(bookEntityList!= null && !bookEntityList.isEmpty()) {
            bookEntityList.forEach(bookEntity -> {
                ReadBookResponse response = new ReadBookResponse();
                response.setAuthor(bookEntity.getAuthor());
                response.setTitle(bookEntity.getTitle());
                bookResponses.add(response);
            });
        }
        return new PageImpl<>(bookResponses);
    }
}

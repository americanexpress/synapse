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
package io.americanexpress.service.book.rest.service

import io.americanexpress.data.book.entity.BookEntity
import io.americanexpress.data.book.repository.BookRepository
import io.americanexpress.service.book.rest.model.CreateBookRequest
import io.americanexpress.service.book.rest.model.CreateBookResponse
import io.americanexpress.synapse.service.rest.service.reactive.BaseCreateReactiveService
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CreateBookService(private val bookRepository: BookRepository): BaseCreateReactiveService<CreateBookRequest, CreateBookResponse>() {

    @RegisterReflectionForBinding(CreateBookRequest::class, CreateBookResponse::class)
    override fun executeCreate(headers: HttpHeaders?, request: CreateBookRequest?): Mono<CreateBookResponse> {
        val bookEntity = BookEntity()
        if (request != null) {
            bookEntity.title = request.title
            bookEntity.author = request.author
        }

        return bookRepository.save(bookEntity).map { book -> CreateBookResponse(book.identifier) }
            .switchIfEmpty(Mono.empty())

    }
}

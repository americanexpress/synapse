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
package io.americanexpress.synapse.kotlin.book.service

import io.americanexpress.synapse.kotlin.book.model.BookRequest
import io.americanexpress.synapse.kotlin.book.model.BookResponse
import io.americanexpress.synapse.service.rest.service.BaseCreateService
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import java.util.*

/**
 * {@code CreateBookService} Create book sample service in Kotlin using Synapse.
 * Extends {@link BaseCreateService} and takes {@link BookRequest} and {@link BookResponse} as an input.
 */
@Service
open class CreateBookService : BaseCreateService<BookRequest, BookResponse>() {

    /**
     * Sample executeCreate function from Synapse {@link BaseCreateService}
     * {@param headers} http headers from client.
     * {@param request} request object from client.
     */
    override fun executeCreate(headers: HttpHeaders?, request: BookRequest?): BookResponse {
        val actualRequest = request ?: BookRequest(UUID.randomUUID().toString(), "Synapse", "Gabriel")
        return BookResponse(title = actualRequest.title, author = actualRequest.author)
    }
}

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
import io.americanexpress.synapse.service.rest.service.BaseReadMonoService
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import java.util.*

/**
 * {@code ReadBookService} Read book sample service in Kotlin using Synapse.
 * Extends {@link BaseReadMonoService} and takes {@link BookRequest} and {@link BookResponse} as an input.
 */
@Service
open class ReadBookService : BaseReadMonoService<BookRequest, BookResponse>() {

    /**
     * Sample executeRead function from Synapse {@link BaseReadMonoService}
     * {@param headers} http headers from client.
     * {@param request} request object from client.
     */
    override fun executeRead(headers: HttpHeaders?, request: BookRequest?): BookResponse {
        val actualBookRequest = request ?: BookRequest(UUID.randomUUID().toString(), "Synapse", "Gabriel")
        return BookResponse(title = actualBookRequest.title, author = actualBookRequest.author)
    }
}

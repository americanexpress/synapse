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
import io.americanexpress.synapse.service.rest.service.BaseUpdateService
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service

/**
 * {@code UpdateBookService} Update book sample service in Kotlin using Synapse.
 * Extends {@link BaseUpdateService} and takes {@link BookRequest} as an input.
 */
@Service
open class UpdateBookService : BaseUpdateService<BookRequest>()  {

    /**
     * Sample executeUpdate function from Synapse {@link BaseUpdateService}
     * {@param headers} http headers from client.
     * {@param request} request object from client.
     */
    override fun executeUpdate(headers: HttpHeaders?, request: BookRequest?) {
        println("update")
    }
}

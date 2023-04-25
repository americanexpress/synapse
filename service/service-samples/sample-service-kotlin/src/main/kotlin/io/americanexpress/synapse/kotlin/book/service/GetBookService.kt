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

import io.americanexpress.synapse.kotlin.book.model.BookResponse
import io.americanexpress.synapse.service.rest.service.BaseGetMonoService
import org.apache.commons.lang3.StringUtils
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import java.util.*

/**
 * {@code GetBookService} Get book sample service in Kotlin using Synapse.
 * Extends {@link BaseGetMonoService} takes {@link BookResponse} as an input.
 */
@Service
open class GetBookService : BaseGetMonoService<BookResponse>() {

    /**
     * Sample executeRead function from Synapse {@link BaseGetMonoService}
     * {@param headers} http headers from client.
     * {@param title} title string from client.
     */
    override fun executeRead(headers: HttpHeaders?, title: String?): BookResponse {
        val actualTitle = title ?: StringUtils.EMPTY
        return BookResponse(title = actualTitle, author = "Gabriel")
    }
}

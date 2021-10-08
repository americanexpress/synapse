package io.americanexpress.service.rest.light.book.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ReadBookRequest (
    @JsonProperty("title")
    val title: String,

    @JsonProperty("author")
    val author: String
)
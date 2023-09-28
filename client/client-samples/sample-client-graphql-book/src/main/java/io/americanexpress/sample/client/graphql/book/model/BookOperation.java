package io.americanexpress.sample.client.graphql.book.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.americanexpress.synapse.client.graphql.model.BaseGraphQLData;

public class BookOperation implements BaseGraphQLData {

    @JsonProperty("getBookReactively")
    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}

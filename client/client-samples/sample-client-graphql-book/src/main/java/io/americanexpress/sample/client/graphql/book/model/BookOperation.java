package io.americanexpress.sample.client.graphql.book.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.americanexpress.synapse.client.graphql.model.BaseGraphQLData;

/**
 * The type Book operation.
 */
public class BookOperation implements BaseGraphQLData {

    @JsonProperty("getBookReactively")
    private Book book;

    /**
     * Gets book.
     *
     * @return the book
     */
    public Book getBook() {
        return book;
    }

    /**
     * Sets book.
     *
     * @param book the book
     */
    public void setBook(Book book) {
        this.book = book;
    }
}

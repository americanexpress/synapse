package io.americanexpress.service.sample.imperativebook.model;

import io.americanexpress.synapse.service.imperative.model.BasePaginatedServiceRequest;

/**
 * {@code ReadPolyBookServiceRequest} class is the request model for {@link io.americanexpress.service.sample.imperativebook.service.ReadPolyBookService}
 *
 * @author Tanvir Islam
 */
public class ReadPolyBookServiceRequest extends BasePaginatedServiceRequest {

    /**
     * Author of the book.
     */
    private String author;

    /**
     * Get the author of the book.
     *
     * @return the author of the book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Set the author of the book.
     *
     * @param author the author of the book
     */
    public void setAuthor(String author) {
        this.author = author;
    }
}

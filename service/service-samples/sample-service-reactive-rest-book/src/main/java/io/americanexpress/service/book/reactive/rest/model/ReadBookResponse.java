package io.americanexpress.service.book.reactive.rest.model;

import io.americanexpress.synapse.service.reactive.rest.model.BaseServiceResponse;

import java.util.Objects;

/**
 * ReadBookResponse is the response used on the mono and poly read controllers.
 */
public class ReadBookResponse extends BaseServiceResponse {

    private String title;
    private String author;

    /**
     * Instantiates a new Read book response.
     */
    public ReadBookResponse() {
    }

    /**
     * Instantiates a new Read book response.
     *
     * @param title  the title
     * @param author the author
     */
    public ReadBookResponse(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public ReadBookResponse(String id, String title, String author) {
        this.title = title;
        this.author = author;
        super.setId(id);
    }


    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets author.
     *
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets author.
     *
     * @param author the author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReadBookResponse that = (ReadBookResponse) o;
        return Objects.equals(title, that.title) && Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }
}

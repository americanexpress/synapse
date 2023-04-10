package io.americanexpress.service.book.reactive.rest.model;

import io.americanexpress.synapse.service.reactive.rest.model.BaseServiceRequest;

import java.util.Objects;

/**
 * ReadBookRequest is the model used on the request of the Read Book Controller.
 */
public class ReadBookRequest implements BaseServiceRequest {

    private String identifier;
    private String title;
    private String author;

    /**
     * Instantiates a new Read book request.
     */
    public ReadBookRequest() {
    }

    /**
     * Instantiates a new Read book request.
     *
     * @param title  the title
     * @param author the author
     */
    public ReadBookRequest(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public ReadBookRequest(String identifier, String title, String author) {
        this.identifier = identifier;
        this.title = title;
        this.author = author;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
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
        ReadBookRequest that = (ReadBookRequest) o;
        return identifier == that.identifier && Objects.equals(title, that.title) && Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, title, author);
    }

    @Override
    public String toString() {
        return "ReadPolyBookRequest{" +
                "identifier=" + identifier +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

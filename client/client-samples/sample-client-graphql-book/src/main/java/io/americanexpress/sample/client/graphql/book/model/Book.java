package io.americanexpress.sample.client.graphql.book.model;

import java.util.UUID;

public class Book {

    /**
     * ID of this book.
     */
    private UUID id;

    /**
     * Title of the book.
     */
    private String title;

    /**
     * Author of the book.
     */
    private String author;

    /**
     * Default constructor creates a new instance of Book with default values.
     */
    public Book() {

    }

    /**
     * Argument constructor creates a new instance of Book with given values.
     * @param id of the book
     * @param title of the book
     * @param author of the book
     */
    public Book(UUID id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    /**
     * Get the id.
     * @return the id
     */
    public UUID getId() {
        return id;
    }

    /**
     * Set the id.
     * @param id the id to set
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Get the title.
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title.
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the author.
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Set the author.
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

}

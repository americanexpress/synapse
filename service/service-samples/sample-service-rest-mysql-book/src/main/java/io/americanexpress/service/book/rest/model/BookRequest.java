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
package io.americanexpress.service.book.rest.model;

import io.americanexpress.synapse.service.rest.model.BaseServiceRequest;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

/**
 * {@code BookRequest} Parent request object.
 */
public class BookRequest implements BaseServiceRequest {

    /**
     * Default constructor
     */
    public BookRequest(){}

    /**
     * Constructor taking in title as string, author as string, and createdBy as string
     * @param title Title of the book.
     * @param author Author of the book.
     * @param createdBy When the BookEntity was created.
     */
    public BookRequest(String title, String author, String createdBy) {
        this.title = title;
        this.author = author;
        this.createdBy = createdBy;
    }

    /**
     * Constructor taking title and author
     * @param title Title of the book.
     * @param author Author of the book.
     */
    public BookRequest(String title, String author){
        this.title = title;
        this.author = author;
    }

    /**
     * The title of the book.
     */
    @NotBlank
    private String title;

    /**
     * The author of the book.
     */
    @NotBlank
    private String author;

    /**
     * A record of who is creating the book request.
     */
    private String createdBy;

    /**
     * Gets the title as string.
     * @return The title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title with provided string.
     * @param title of the book.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets author as string.
     * @return The author of the book.s
     */
    public String getAuthor() {
        return author;
    }

    /**
     * gets createdBy as string.
     * @return createdBy record of the book.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets createdBy with provided string.
     * @param createdBy Who is creating the record of the book.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Sets author with provided string.
     * @param author of the book.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Compares two objects.
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookRequest that = (BookRequest) o;
        return Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getAuthor(), that.getAuthor()) && Objects.equals(getCreatedBy(), that.getCreatedBy());
    }

    /**
     * Returns the hashcode of the object.
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAuthor(), getCreatedBy());
    }

    /**
     * Returns a string of Book Request.
     * @return A string representation of BookRequest.
     */
    @Override
    public String toString() {
        return "BookRequest{" +
                ", createdBy='" + createdBy + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

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

/**
 * {@code BookRequest} Parent request object.
 */
public class BookRequest implements BaseServiceRequest {

    /**
     * The ID of the book.
     */
    private Long id;

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
     * Default constructor.
     */
    public BookRequest(){}

    /**
     * Constructor taking title and author
     * @param title of the book.
     * @param author of the book.
     */
    public BookRequest(String title, String author) {
        this.title = title;
        this.author = author;
    }

    /**
     * Constructor taking title and author
     * @param title of the book.
     * @param author of the book.
     * @param createdBy who is creating the record of the book.
     */
    public BookRequest(String title, String author, String createdBy) {
        this.title = title;
        this.author = author;
        this.createdBy = createdBy;
    }

    /**
     * Constructor taking title and author.
     * @param id of the book.
     * @param title of the book.
     * @param author of the book.
     * @param createdBy who is creating the record of the book.
     */
    public BookRequest(Long id, String title, String author, String createdBy) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.createdBy = createdBy;
    }

    /**
     * Gets id as a long.
     * @return A long representation of id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id with provided long.
     * @param id of the book.
     */
    public void setId(Long id) {
        this.id = id;
    }

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
     * @return The author of the book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets author with provided string.
     * @param author of the book.
     */
    public void setAuthor(String author) {
        this.author = author;
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
     * Returns a string of Book Request.
     * @return A string representation of BookRequest.
     */
    @Override
    public String toString() {
        return "BookRequest{" +
                ", id='" + id + '\'' +
                ", tittle='" + title + '\'' +
                ", author='" + author + '\'' +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}

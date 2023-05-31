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

import io.americanexpress.synapse.service.reactive.rest.model.BaseServiceResponse;

import java.util.Objects;

/**
 * {@code ReadBookResponse} is the response used on the mono and poly read controllers.
 */
public class ReadBookResponse extends BaseServiceResponse {

    /**
     * The book title.
     */
    private String title;

    /**
     * The author of the book.
     */
    private String author;

    /**
     * Instantiates a new ReadBookResponse.
     */
    public ReadBookResponse() {
    }

    /**
     * Instantiates a new ReadBookResponse.
     *
     * @param title  the title
     * @param author the author
     */
    public ReadBookResponse(String title, String author) {
        this.title = title;
        this.author = author;
    }

    /**
     * Instantiates a new ReadBookResponse.
     *
     * @param id the identifier
     * @param title  the title
     * @param author the author
     */
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

    /**
     * Determines if one object is equal to another.
     *
     * @param o the object
     * @return true if equal, else false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReadBookResponse that = (ReadBookResponse) o;
        return Objects.equals(title, that.title) && Objects.equals(author, that.author);
    }

    /**
     * Returns a hash code value for the object.
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }
}

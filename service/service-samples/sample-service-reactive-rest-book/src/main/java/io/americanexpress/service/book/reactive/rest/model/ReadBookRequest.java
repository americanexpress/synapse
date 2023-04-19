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
package io.americanexpress.service.book.reactive.rest.model;

import io.americanexpress.synapse.service.reactive.rest.model.BaseServiceRequest;

import java.util.Objects;

/**
 * {@code ReadBookRequest} is the model used on the request of the Read Book Controller.
 */
public class ReadBookRequest implements BaseServiceRequest {

    /**
     * The book identifier.
     */
    private String identifier;

    /**
     * The book title.
     */
    private String title;

    /**
     * The author of the book.
     */
    private String author;

    /**
     * Instantiates a new Read book request.
     */
    public ReadBookRequest() {
    }

    /**
     * Instantiates a new ReadBookRequest.
     *
     * @param title  the title
     * @param author the author
     */
    public ReadBookRequest(String title, String author) {
        this.title = title;
        this.author = author;
    }

    /**
     * Instantiates a new ReadBookRequest.
     * @param identifier the identifier
     * @param title the title
     * @param author the author
     */
    public ReadBookRequest(String identifier, String title, String author) {
        this.identifier = identifier;
        this.title = title;
        this.author = author;
    }

    /**
     * Gets identifier.
     *
     * @return the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Gets identifier.
     *
     * @param identifier the identifier
     */
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
        ReadBookRequest that = (ReadBookRequest) o;
        return identifier == that.identifier && Objects.equals(title, that.title) && Objects.equals(author, that.author);
    }

    /**
     * Returns a hash code value for the object.
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(identifier, title, author);
    }

    /**
     * Returns a string representation of the object.
     * @return the string representation of the object
     */
    @Override
    public String toString() {
        return "ReadPolyBookRequest{" +
                "identifier=" + identifier +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

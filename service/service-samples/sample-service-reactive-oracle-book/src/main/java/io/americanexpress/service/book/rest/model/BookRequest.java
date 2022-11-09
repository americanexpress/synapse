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

import javax.validation.constraints.NotBlank;

/**
 * {@code UpdateBookService} Updates a book resource by request criteria.
 */
public class BookRequest implements BaseServiceRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String author;

    /**
     * Default constructor
     */
    public BookRequest(){}

    /**
     * Constructor taking title and author
     * @param title
     * @param author
     */
    public BookRequest(String title, String author){
        this.title = title;
        this.author = author;
    }

    /**
     * Gets the title as string
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title with provided string.
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets author as string.
     * @return
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets author with provided string.
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Returns a string of the obejct
     * @return
     */
    @Override
    public String toString() {
        return "BookRequest{" +
                ", tittle='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

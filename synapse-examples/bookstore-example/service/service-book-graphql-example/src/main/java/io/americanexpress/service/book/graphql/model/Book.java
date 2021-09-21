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
package io.americanexpress.service.book.graphql.model;

/**
 * {@code Book} class is the model that represents books.
 * @author Paolo Claudio
 *
 */
public class Book {

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
     * @param title of the book
     * @param author of the book
     */
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
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

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
package io.americanexpress.data.book.entity;

import io.americanexpress.synapse.data.mongodb.entity.BaseEntity;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * {@code BookEntity} class represents the domain of the books table.
 */
@Document("books")
public class BookEntity extends BaseEntity {

    /**
     * Title of book.
     */
    private String title;

    /**
     * Author of book.
     */
    private String author;

    /**
     * Number of copies of book.
     */
    private int numberOfCopies;

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
     * Gets number of copies.
     *
     * @return the number of copies
     */
    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    /**
     * Sets number of copies.
     *
     * @param numberOfCopies the number of copies
     */
    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

}

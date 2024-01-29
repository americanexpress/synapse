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

import io.americanexpress.synapse.data.db2.entity.BaseDb2Entity;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * {@code BookEntity} class represents the domain of the books table.
 * @author tisla4
 */
@Entity
@Table(name = "BOOK")
public class BookEntity extends BaseDb2Entity {

    /**
     * The title of the book.
     */
    private String title;

    /**
     * The author of the book.
     */
    private String author;

    /**
     * A brief description of the book.
     */
    private String description;

    /**
     * Get the title of the book.
     * @return the book's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of the book.
     * @param title the book's title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the author of the book.
     * @return the book's author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Set the author of the book.
     * @param author the book's author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Get the description of the book.
     * @return the book's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the book.
     * @param description the book's description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}

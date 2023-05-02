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

import io.americanexpress.synapse.data.redis.entity.BaseEntity;
import org.springframework.data.redis.core.RedisHash;

/**
 * {@code BookEntity} class represents the domain of the books table.
 */
@RedisHash("books")
public class BookEntity extends BaseEntity {

    /**
     * The title.
     */
    private String title;

    /**
     * The author.
     */
    private String author;

    /**
     * The number of copies.
     */
    private int numberOfCopies;

    public BookEntity(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public BookEntity() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }
}


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
package io.americanexpress.data.mysql.book.entity;

import io.americanexpress.synapse.data.mysql.entity.BaseEntity;
import org.hibernate.annotations.NaturalId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Objects;

/**
 * {@code BookEntity} example of a child module using BaseEntity
 */
@Entity
@Table(name = "book")
public class BookEntity extends BaseEntity {

    /**
     * The title of the book.
     */
    @Column(name = "title")
    @NaturalId(mutable = true)
    private String title;

    /**
     * The author of the book.
     */
    @Column(name = "author")
    private String author;

    /**
     * Gets the string representation of title of the book.
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book by the provided string.
     *
     * @param title of the book.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the string representation of author of the book.
     *
     * @return The string representation of author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the book by the provided string.
     *
     * @param author the author of the book.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Compares an object with another object of BookEntity.
     * @param o an object.
     * @return A boolean if the object is of BookEntity.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookEntity)) return false;
        if (!super.equals(o)) return false;
        BookEntity that = (BookEntity) o;
        return Objects.equals(getTitle(), that.getTitle());
    }

    /**
     * HashCode of BookEntity.
     * @return an integer representation of the hashcode of BookEntity.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTitle());
    }
}

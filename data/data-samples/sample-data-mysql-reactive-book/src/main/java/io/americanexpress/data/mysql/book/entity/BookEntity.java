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

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;

/**
 * {@code BookEntity} example of a child module using BaseEntity
 */
@Table
public class BookEntity {

    /**
     * The primary key of the bookEntity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    /**
     * Created Date Time of the bookEntity.
     */
    @CreatedDate
    @Column(name = "created_date_time")
    protected LocalDateTime createdDateTime;

    /**
     * Last Modified Date Time of the bookEntity.
     */
    @LastModifiedDate
    @Column(name = "last_modified_date_time")
    protected LocalDateTime lastModifiedDateTime;

    /**
     * Created By record of the bookEntity.
     */
    @CreatedBy
    @Column(name = "created_by")
    protected String createdBy;

    /**
     * The last modification of the bookEntity.
     */
    @LastModifiedBy
    @Column(name = "last_modified_by")
    protected String lastModifiedBy;

    /**
     * Version of the bookEntity.
     */
    @Version
    @Column(name = "version")
    protected Long version;

    /**
     * Title of the bookEntity.
     */
    @Column(name = "title")
    private String title;

    /**
     * Author of the bookEntity.
     */
    @Column(name = "author")
    private String author;

    /**
     * Gets title of the bookEntity.
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title of the bookEntity.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets author of the bookEntity.
     *
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets author of the bookEntity.
     *
     * @param author the author
     */
    public void setAuthor(String author) {
        this.author = author;
    }


    /**
     * Gets ID as a long representation.
     * @return ID as a long.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id with provided long value.
     * @param id of the bookEntity.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets createdDateTime as LocalDateTime.
     * @return CreatedDateTime of localDateTime.
     */
    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    /**
     * Sets createdDateTime with the provided LocalDateTime.
     * @param createdDateTime of the bookEntity.
     */
    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    /**
     * Gets lastModifiedDateTime as LocalDateTime.
     * @return LocalDateTime representation of lastModifiedDateTime.
     */
    public LocalDateTime getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    /**
     * Sets lastModifiedDateTime with provided localDateTime
     * @param lastModifiedDateTime of bookEntity.
     */
    public void setLastModifiedDateTime(LocalDateTime lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    /**
     * Gets createdBy as a String
     * @return A string representation of createdBy.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets createdBy with provided String.
     * @param createdBy of bookEntity.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets lastModifiedBy as a string.
     * @return String representation of lastModifiedBy.
     */
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * Sets lastModifiedBy with provided string.
     * @param lastModifiedBy as a string.
     */
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * Gets version as a long.
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Sets version with provided long.
     * @param version of a long.
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * A string representation of BookEntity.
     * @return A string of BookEntity.
     */
    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", createdDateTime=" + createdDateTime +
                ", lastModifiedDateTime=" + lastModifiedDateTime +
                ", createdBy='" + createdBy + '\'' +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", version=" + version +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

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

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * {@code BookEntity} example of a child module using BaseEntity
 */
@Table
public class BookEntity {

    /**
     * Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    /**
     * Created Date Time
     */
    @CreatedDate
    @Column(name = "created_date_time")
    protected LocalDateTime createdDateTime;

    /**
     * Last Modified Date Time
     */
    @LastModifiedDate
    @Column(name = "last_modified_date_time")
    protected LocalDateTime lastModifiedDateTime;

    /**
     * Created By
     */
    @CreatedBy
    @Column(name = "created_by")
    protected String createdBy;

    /**
     * Last Modified By
     */
    @LastModifiedBy
    @Column(name = "last_modified_by")
    protected String lastModifiedBy;

    /**
     * Version
     */
    @Version
    @Column(name = "version")
    protected Long version;

    /**
     * title
     */
    @Column(name = "title")
    private String title;

    /**
     * author
     */
    @Column(name = "author")
    private String author;

    /**
     * Gets title.
     * @return
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
     * Gets id as a long
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id with provided long value
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets createdDateTime as LocalDateTime
     * @return
     */
    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    /**
     * Sets createdDateTime with provided LocalDateTime
     * @param createdDateTime
     */
    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    /**
     * Gets lastModifiedDateTime as LocalDateTime
     * @return
     */
    public LocalDateTime getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    /**
     * Sets lastModifiedDateTime with provided localDateTime
     * @param lastModifiedDateTime
     */
    public void setLastModifiedDateTime(LocalDateTime lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    /**
     * Gets createdBy as a String
     * @return
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets createdBy with provided String.
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets lastModifiedBy as a string.
     * @return
     */
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * Sets lastModifiedBy with provided string.
     * @param lastModifiedBy
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
     * @param version
     */
    public void setVersion(Long version) {
        this.version = version;
    }

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

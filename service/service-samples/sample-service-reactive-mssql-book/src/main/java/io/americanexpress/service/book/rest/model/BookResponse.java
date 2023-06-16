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

import java.time.LocalDateTime;

/**
 * {@code BookResponse} Book response object.
 */
public class BookResponse extends BaseServiceResponse {

    /**
     * The title of the book.
     */
    private String title;

    /**
     * The author of the book.
     */
    private String author;

    /**
     * The createdDateTime of the book record.
     */
    private LocalDateTime createdDateTime;

    /**
     * The lastModifiedDateTime of the book record.
     */
    private LocalDateTime lastModifiedDateTime;

    /**
     * The createdBy of the book record.
     */
    private String createdBy;

    /**
     * The lastModifiedBy of the book record.
     */
    private String lastModifiedBy;

    /**
     * The version of the book record.
     */
    private String version;

    /**
     * Gets title as string.
     * @return The title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title with provided String
     * @param title of the book.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets author as string.
     * @return The author of the book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets author with provided string.
     * @param author of the book.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets createdDateTime.
     * @return The createdDateTime of the book record.
     */
    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    /**
     * Sets createdDateTime.
     * @param createdDateTime of the book record.
     */
    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    /**
     * Gets lastModifiedDateTime.
     * @return String representation of lastModifiedDateTime.
     */
    public LocalDateTime getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    /**
     * Sets lastModifiedDateTime.
     * @param lastModifiedDateTime of the book record.
     */
    public void setLastModifiedDateTime(LocalDateTime lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    /**
     * Gets createdBy as string.
     * @return CreatedBy the book record.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets createdBy with provided string.
     * @param createdBy as a string.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * gets lastModifiedBy as string.
     * @return lastModifiedBy as a string.
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
     * Gets version as string.
     * @return String representation of version.
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets version with provided string.
     * @param version of the book.
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * A string representation of BookResponse.
     * @return A string of bookResponse.
     */
    @Override
    public String toString() {
        return "BookRequest{" +
                ", tittle='" + title + '\'' +
                ", author='" + author + '\'' +
                ", createdDateTime='" + createdDateTime + '\'' +
                ", lastModifiedDateTime='" + lastModifiedDateTime + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}

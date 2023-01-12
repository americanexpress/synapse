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

import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;

import jakarta.validation.constraints.NotBlank;

/**
 * {@code BookResponse} Book response object.
 */
public class BookResponse extends BaseServiceResponse {

    /**
     * title
     */
    @NotBlank
    private String title;

    /**
     * author
     */
    @NotBlank
    private String author;

    /**
     * createdDateTime
     */
    private String createdDateTime;

    /**
     * lastModifiedDateTime
     */
    private String lastModifiedDateTime;

    /**
     * createdBy
     */
    private String createdBy;

    /**
     * lastModifiedBy
     */
    private String lastModifiedBy;

    /**
     * version
     */
    private String version;

    /**
     * Gets title as string.
     * @return the title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title with provided String.
     * @param title String representation of the title
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
     * Gets createdDateTime as String.
     * @return
     */
    public String getCreatedDateTime() {
        return createdDateTime;
    }

    /**
     * Sets createdDateTime with provided string.
     * @param createdDateTime
     */
    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    /**
     * Gets lastModifiedDateTime as string.
     * @return
     */
    public String getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    /**
     * Sets lastModifiedDateTime with provied string.
     * @param lastModifiedDateTime
     */
    public void setLastModifiedDateTime(String lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    /**
     * Gets createdBy as string.
     * @return
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets createdBy with provided string.
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * gets lastModifiedBy as string.
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
     * Gets version as string.
     * @return
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets version with provided string.
     * @param version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * prints object as string.
     * @return
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

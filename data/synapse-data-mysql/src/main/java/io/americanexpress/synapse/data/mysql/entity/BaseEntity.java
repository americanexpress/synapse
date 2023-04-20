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
package io.americanexpress.synapse.data.mysql.entity;


import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * {@code BaseEntity} class is the parent class for all the database entities.
 * All the common attributes are consolidated in this entity.
 *
 */
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity {

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
     * Gets id as a long.
     * @return the identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id with provided long value.
     * @param id the identifier
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets createdDateTime as LocalDateTime.
     * @return hte created date time
     */
    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    /**
     * Sets createdDateTime with provided LocalDateTime.
     * @param createdDateTime the created date time
     */
    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    /**
     * Gets lastModifiedDateTime as LocalDateTime.
     * @return the last modified date time
     */
    public LocalDateTime getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    /**
     * Sets lastModifiedDateTime with provided localDateTime
     * @param lastModifiedDateTime the last modified date time
     */
    public void setLastModifiedDateTime(LocalDateTime lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    /**
     * Gets createdBy as a string.
     * @return the created by
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets createdBy with provided String.
     * @param createdBy the created by
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets lastModifiedBy as a string.
     * @return the last modified by
     */
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * Sets lastModifiedBy with provided string.
     * @param lastModifiedBy the last modified by
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
     * @param version the version number
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * Compares object
     * @param o an object
     * @return a boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(createdDateTime, that.createdDateTime)
                && Objects.equals(lastModifiedDateTime, that.lastModifiedDateTime)
                && Objects.equals(createdBy, that.createdBy)
                && Objects.equals(lastModifiedBy, that.lastModifiedBy) && Objects.equals(version, that.version);
    }

    /**
     * Gets hashcode of object.
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, createdDateTime, lastModifiedDateTime, createdBy, lastModifiedBy, version);
    }

    /**
     * Builds object as a string.
     * @return the object as a string
     */
    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", createdDateTime=" + createdDateTime +
                ", lastModifiedDateTime=" + lastModifiedDateTime +
                ", createdBy='" + createdBy + '\'' +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", version=" + version +
                '}';
    }
}

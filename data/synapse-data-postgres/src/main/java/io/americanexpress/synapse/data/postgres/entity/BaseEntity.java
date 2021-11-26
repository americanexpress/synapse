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
package io.americanexpress.synapse.data.postgres.entity;

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
 * <code>BaseEntity</code> class is the parent class for all the database entities.
 * All the common attributes are consolidated in this entity.
 *
 * @author Gabriel Jimenez
 */
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity {

    /**
     * Identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long identifier;

    /**
     * Created Date Time
     */
    @CreatedDate
    @Column(name = "created_date_time", updatable = false)
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
     * Empty constructor, do not delete it. It is used by Spring Data.
     */
    public BaseEntity() {
    }


    /**
     * Gets the created date for this entry.
     *
     * @return the create date
     */
    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    /**
     * Sets the created date for this entry.
     *
     * @param createdDate the created date
     */
    public void setCreatedDateTime(LocalDateTime createdDate) {
        this.createdDateTime = createdDate;
    }

    /**
     * Gets the last modified date for this entry.
     *
     * @return the last modified date
     */
    public LocalDateTime getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    /**
     * Sets the last modified date for this entry.
     *
     * @param lastModifiedDateTime the last modified date
     */
    public void setLastModifiedDateTime(LocalDateTime lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    /**
     * Gets the identifier.
     *
     * @return the identifier
     */
    public Long getIdentifier() {
        return identifier;
    }

    /**
     * Sets the identifier.
     *
     * @param identifier the identifier
     */
    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    /**
     * Gets the created by which contains the username of the modifier.
     *
     * @return the greatedBy individual's username
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the createdBy's username.
     *
     * @param createdBy the createdBy's username to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the last modifiedBy's username.
     *
     * @return the lastModifiedBy's username
     */
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * Sets the lastModifiedBy's username.
     *
     * @param lastModifiedBy the last modifiedBy
     */
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * Gets the version number.
     *
     * @return the version number
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Sets the version number.
     *
     * @param version the version number to set
     */
    public void setVersion(Long version) {
        this.version = version;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity that = (BaseEntity) o;

        if (!Objects.equals(identifier, that.identifier)) return false;
        if (!Objects.equals(createdDateTime, that.createdDateTime))
            return false;
        if (!Objects.equals(lastModifiedDateTime, that.lastModifiedDateTime))
            return false;
        if (!Objects.equals(createdBy, that.createdBy)) return false;
        if (!Objects.equals(lastModifiedBy, that.lastModifiedBy))
            return false;
        return Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        int result = identifier != null ? identifier.hashCode() : 0;
        result = 31 * result + (createdDateTime != null ? createdDateTime.hashCode() : 0);
        result = 31 * result + (lastModifiedDateTime != null ? lastModifiedDateTime.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (lastModifiedBy != null ? lastModifiedBy.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "identifier=" + identifier +
                ", createdDateTime=" + createdDateTime +
                ", lastModifiedDate=" + lastModifiedDateTime +
                ", createdBy='" + createdBy + '\'' +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", version=" + version +
                '}';
    }
}

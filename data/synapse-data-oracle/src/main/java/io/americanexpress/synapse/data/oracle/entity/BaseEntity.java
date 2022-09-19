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
package io.americanexpress.synapse.data.oracle.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * {@code BaseOracleEntity} class is the parent class for all the database entities.
 * All the common attributes are consolidated in this entity.
 *
 * @author Gabriel Jimenez
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public LocalDateTime getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    public void setLastModifiedDateTime(LocalDateTime lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(id, createdDateTime, lastModifiedDateTime, createdBy, lastModifiedBy, version);
    }

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

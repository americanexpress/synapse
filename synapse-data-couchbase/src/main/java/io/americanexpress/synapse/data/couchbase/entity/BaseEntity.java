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
package io.americanexpress.synapse.data.couchbase.entity;

import com.openpojo.business.annotation.BusinessKey;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import javax.persistence.Column;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.StringJoiner;


/**
 * BaseEntity class is the parent class for all the database entities for data-audit-logs module.
 *
 * @author Gabriel Jimenez
 */
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    @Column
    @BusinessKey
    protected String identifier;

    @CreatedBy
    @Column
    @NotBlank
    @BusinessKey
    private String createdBy;

    @CreatedDate
    @Column
    @BusinessKey
    protected LocalDateTime createdDateTime;

    @LastModifiedBy
    @Column
    @NotBlank
    @BusinessKey
    private String lastModifiedBy;

    @LastModifiedDate
    @Column
    @BusinessKey
    protected LocalDateTime lastModifiedDateTime;

    @Version
    @Column
    @BusinessKey
    protected long version;

    @BusinessKey
    @Column
    protected int schemaVersion;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDateTime;
    }

    public void setCreatedDate(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDateTime;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public int getSchemaVersion() {
        return schemaVersion;
    }

    public void setSchemaVersion(int schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BaseEntity.class.getSimpleName() + "[", "]")
                .add("identifier='" + identifier + "'")
                .add("createdBy='" + createdBy + "'")
                .add("createdDateTime=" + createdDateTime)
                .add("lastModifiedBy='" + lastModifiedBy + "'")
                .add("lastModifiedDateTime=" + lastModifiedDateTime)
                .add("version=" + version)
                .add("schemaVersion=" + schemaVersion)
                .toString();
    }
}

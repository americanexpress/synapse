package io.americanexpress.synapse.data.cassandra.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class BaseEntity {

    @Id
    @PrimaryKey
    private UUID identifier;

    /**
     * Created Date Time
     */
    @CreatedDate
    protected LocalDateTime createdDateTime;

    /**
     * Last Modified Date Time
     */
    @LastModifiedDate
    protected LocalDateTime lastModifiedDateTime;

    /**
     * Created By
     */
    @CreatedBy
    protected String createdBy;

    /**
     * Last Modified By
     */
    @LastModifiedBy
    protected String lastModifiedBy;

    /**
     * Version
     */
    @Version
    protected Long version;

    /**
     * Empty constructor, do not delete it. It is used by Spring Data.
     */
    public BaseEntity() {
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
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

}

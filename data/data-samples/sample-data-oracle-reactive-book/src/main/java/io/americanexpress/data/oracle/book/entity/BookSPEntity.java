package io.americanexpress.data.oracle.book.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * {@code BookSPEntity} BookEntity representation of a stored procedure.
 */
@Entity
@Table(name = "BOOK_ENTITY")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "BookPackage.getBookData",
                procedureName = "book_package.get_book_data",
                parameters = @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class),
                resultSetMappings = "BookDataMapping"
        ),
        @NamedStoredProcedureQuery(
                name = "BookPackage.getBookByTitleAndAuthor",
                procedureName = "book_package.get_book_by_title_and_author",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "title", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "author", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class)
                },
                resultSetMappings = "BookDataMapping"
        ),
        @NamedStoredProcedureQuery(
                name = "BookPackage.getBookByTitle",
                procedureName = "book_package.get_book_by_title",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "title", type = String.class)
                },
                resultSetMappings = "BookDataMapping"
        ),
        @NamedStoredProcedureQuery(
                name = "BookPackage.updateBookTitle",
                procedureName = "book_package.update_book_title",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "new_title", type = String.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "BookPackage.deleteBookByTitle",
                procedureName = "book_package.delete_books_by_title",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "title", type = String.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "book_package.insert_book",
                procedureName = "book_package.insert_book",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "title", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "author", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "created_by", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "last_modified_by", type = String.class)
                },
                resultSetMappings = "BookDataMapping"
        ),
        @NamedStoredProcedureQuery(
                name = "BookPackage.processBookData",
                procedureName = "book_package.process_book_data",
                resultSetMappings = "BookDataMapping"
        )
})
@SqlResultSetMapping(
        name = "BookDataMapping",
        classes = @ConstructorResult(
                targetClass = BookSPEntity.class,
                columns = {
                        @ColumnResult(name = "ID", type = Long.class),
                        @ColumnResult(name = "Title", type = String.class),
                        @ColumnResult(name = "Author", type = String.class),
                        @ColumnResult(name = "Created_By", type = String.class),
                        @ColumnResult(name = "Last_Modified_By", type = String.class),
                        @ColumnResult(name = "Created_Date_Time", type = LocalDateTime.class),
                        @ColumnResult(name = "Last_Modified_Date_Time", type = LocalDateTime.class),
                        @ColumnResult(name = "Version", type = Long.class)
                }
        )
)
public class BookSPEntity {
    /**
     * Id of the BookEntity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Title of the BookEntity.
     */
    @Column(name = "title")
    private String title;

    /**
     * Author of the BookEntity.
     */
    @Column(name = "author")
    private String author;

    /**
     * CreatedBy Of the BookEntity.
     */
    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    /**
     * The Last Modified By of the BookEntity.
     */
    @LastModifiedBy
    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    /**
     * The Created Date Time of the BookEntity.
     */
    @CreatedDate
    @Column(name = "created_date_time")
    private LocalDateTime createdDateTime;

    /**
     * The Last Modified DateTime of the BookEntity.
     */
    @LastModifiedDate
    @Column(name = "last_modified_date_time")
    private LocalDateTime lastModifiedDateTime;

    /**
     * The version of the BookEntity.
     */
    @Version
    @Column(name = "version")
    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}

package io.americanexpress.data.oracle.book.entity;

import io.americanexpress.synapse.data.oracle.entity.BaseEntity;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "book")
public class BookEntity extends BaseEntity {

    @Column(name = "title")
    @NaturalId(mutable = true)
    private String title;

    @Column(name = "author")
    private String author;

    private String getTitle() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookEntity)) return false;
        if (!super.equals(o)) return false;
        BookEntity that = (BookEntity) o;
        return Objects.equals(getTitle(), that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTitle());
    }
}

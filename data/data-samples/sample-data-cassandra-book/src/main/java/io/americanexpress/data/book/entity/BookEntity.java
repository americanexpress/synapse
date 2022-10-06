package io.americanexpress.data.book.entity;

import io.americanexpress.synapse.data.cassandra.entity.BaseEntity;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("book")
public class BookEntity extends BaseEntity {

    private String title;

    private String author;

    public BookEntity(String title, String author) {
        this.title = title;
        this.author = author;
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
}

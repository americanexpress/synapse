package io.americanexpress.data.book.entity;


import io.americanexpress.synapse.data.redis.entity.BaseEntity;
import org.springframework.data.redis.core.RedisHash;

/**
 * {@code BookEntity} class represents the domain of the books table.
 */
@RedisHash("book")
public class BookEntity extends BaseEntity {

    private String title;

    private String author;

    private int numberOfCopies;

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

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }
}


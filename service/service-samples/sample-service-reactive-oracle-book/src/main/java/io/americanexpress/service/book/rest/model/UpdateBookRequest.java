package io.americanexpress.service.book.rest.model;

public class UpdateBookRequest extends BookRequest {
    private String author;

    private String title;

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "UpdateBookRequest{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}

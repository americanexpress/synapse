package io.americanexpress.service.book.rest.model;

import io.americanexpress.synapse.service.rest.model.BaseServiceRequest;

import javax.validation.constraints.NotBlank;

public class BookRequest implements BaseServiceRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String author;

    public BookRequest(){}

    public BookRequest(String title, String author){
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
    @Override
    public String toString() {
        return "BookRequest{" +
                ", tittle='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

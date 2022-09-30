package io.americanexpress.service.book.rest.model;

import io.americanexpress.synapse.service.rest.model.BaseServiceRequest;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class UpdateBookRequest implements BaseServiceRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @Min(0)
    private int numberOfCopies;

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

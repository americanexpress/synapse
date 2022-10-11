package io.americanexpress.service.book.rest.model;

import javax.validation.constraints.Min;

public class UpdateBookRequest extends BookRequest {

    @Min(0)
    private int numberOfCopies;

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }
}

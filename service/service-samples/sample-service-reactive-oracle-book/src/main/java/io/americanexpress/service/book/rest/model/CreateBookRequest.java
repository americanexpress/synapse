package io.americanexpress.service.book.rest.model;

public class CreateBookRequest extends BookRequest {
    public CreateBookRequest(String title, String author) {
        super(title, author);
    }

    public CreateBookRequest(){}
}

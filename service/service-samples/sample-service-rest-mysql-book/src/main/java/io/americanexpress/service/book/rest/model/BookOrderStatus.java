package io.americanexpress.service.book.rest.model;

public enum BookOrderStatus {
    NEW("New"),
    IN_PROCESS("In Process"),
    SHIPPED("Shipped"),
    DELIVERED("Delivered");

    private final String value;

    BookOrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}

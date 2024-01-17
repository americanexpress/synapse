package io.americanexpress.synapse.utilities.common.email;


public class TestModel {

    @EmailAddress
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

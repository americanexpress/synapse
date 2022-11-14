package io.americanexpress.synapse.utility.date;

public enum TimeZone {

    MST("America/Phoenix");

    private String value;

    TimeZone(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

package io.americanexpress.synapse.utility.date;

/**
 * {@code TimeZone} to represent different time zones.
 */
public enum TimeZone {

    /**
     * Mst time zone.
     */
    MST("America/Phoenix");

    private String value;

    TimeZone(String value){
        this.value = value;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }
}

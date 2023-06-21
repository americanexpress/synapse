package io.americanexpress.synapse.utility.date;

/**
 * {@code DateTimeFormat} represents type of date-time format.
 */
public enum DateTimeFormat {

    /**
     * Iso local date time sss date time format.
     */
    ISO_LOCAL_DATE_TIME_SSS("yyyy-MM-dd'T'HH:mm:ss.SSS", "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}$"),
    /**
     * Iso local date time ss date time format.
     */
    ISO_LOCAL_DATE_TIME_SS("yyyy-MM-dd'T'HH:mm:ss.SS", "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{2}$"),
    /**
     * Iso local date time date time format.
     */
    ISO_LOCAL_DATE_TIME("yyyy-MM-dd'T'HH:mm:ss", "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$"),
    /**
     * The Iso date time sss.
     */
    ISO_DATE_TIME_SSS("yyyy-MM-dd HH:mm:ss.SSS", "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}$"),
    /**
     * The Iso date time ss.
     */
    ISO_DATE_TIME_SS("yyyy-MM-dd HH:mm:ss.SS", "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}.\\d{2}$"),
    /**
     * The Iso date time.
     */
    ISO_DATE_TIME("yyyy-MM-dd HH:mm:ss", "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$"),
    /**
     * Iso instant date time format.
     */
    ISO_INSTANT("yyyy-MM-dd'T'HH:mm:ssZ", "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z$"),
    /**
     * The Iso date time s.
     */
    ISO_DATE_TIME_S("yyyy-MM-dd HH:mm:ss.S", "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d$");

    private final String value;

    private final String regularExpression;

    DateTimeFormat(String value, String regularExpression) {
        this.value = value;
        this.regularExpression = regularExpression;
    }

    /**
     * Returns date format of the ISO value selected in the ENUM.
     *
     * @return string of the ISO date format
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns the regular expression related the ISO date.
     *
     * @return string with the regular expression
     */
    public String getRegularExpression() {
        return regularExpression;
    }
}

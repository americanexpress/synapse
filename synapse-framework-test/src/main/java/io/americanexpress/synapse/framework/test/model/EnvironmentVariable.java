package io.americanexpress.synapse.framework.test.model;

/**
 * The enum Environment variable.
 */
public enum EnvironmentVariable {

    /**
     * Server env environment variable.
     */
    SPRING_PROFILES_ACTIVE("SPRING_PROFILES_ACTIVE"),
    /**
     * Local environment variable.
     */
    LOCAL("local"),
    /**
     * DEV environment variable.
     */
    DEV("dev"),

    /**
     * QA environment variable.
     */
    QA("qa"),
    /**
     * Production environment variable.
     */
    PROD("prod");

    private final String value;

    EnvironmentVariable(String value) {
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

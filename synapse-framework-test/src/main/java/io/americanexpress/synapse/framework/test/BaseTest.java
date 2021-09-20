package io.americanexpress.synapse.framework.test;

import io.americanexpress.synapse.framework.test.model.EnvironmentVariable;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {

    /**
     * Sets system props.
     */
    @BeforeAll
    public static void setSystemProps() {
        System.setProperty(EnvironmentVariable.SPRING_PROFILES_ACTIVE.getValue(), EnvironmentVariable.LOCAL.getValue());
    }


}

package io.americanexpress.synapse.service.rest.controller.helpers.model;

import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;

public class BaseServiceResponseTest extends BaseServiceResponse {

    /**
     * value
     */
    private String value;

    /**
     * Constructor
     * @param value
     * @param id
     */
    public BaseServiceResponseTest(String value, String id) {
        this.value = value;
        this.id = id;
    }

    /**
     * get value as string
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     * sets value with provided string
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * toString
     * @return
     */
    @Override
    public String toString() {
        return "BaseServiceResponseTest{" +
                "value='" + value + '\'' +
                '}';
    }
}

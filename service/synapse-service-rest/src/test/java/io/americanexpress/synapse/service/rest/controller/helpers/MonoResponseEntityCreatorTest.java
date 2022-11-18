package io.americanexpress.synapse.service.rest.controller.helpers;


import io.americanexpress.synapse.service.rest.controller.helpers.model.BaseServiceResponseTest;
import org.junit.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MonoResponseEntityCreatorTest {

    @Test
    public void test_create_responseEntity() {
        var response = MonoResponseEntityCreator.create(new BaseServiceResponseTest("test", "test"));
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("test", Objects.requireNonNull(response.getBody()).getValue());
        assertEquals("test", response.getBody().getId());
    }

}
package io.americanexpress.synapse.service.rest.controller.helpers;


import io.americanexpress.synapse.service.rest.controller.helpers.model.BaseServiceResponseTest;
import org.junit.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * {@code MonoResponseEntityCreatorTest} Mono Response Entity Creator Test
 */
public class MonoResponseEntityCreatorTest {

    /**
     * test successful create responseEntity
     */
    @Test
    public void create_givenServiceResponse_expectedResponseEntity() {
        var response = MonoResponseEntityCreator.create(new BaseServiceResponseTest("test", "test"));
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("test", Objects.requireNonNull(response.getBody()).getValue());
        assertEquals("test", response.getBody().getId());
    }

}

package io.americanexpress.synapse.service.rest.controller.helpers;

import io.americanexpress.synapse.service.rest.controller.helpers.model.BaseServiceResponseTest;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class PolyResponseEntityCreatorTest {

    @Test
    public void test_create_responseEntity() {
        var page = new PageImpl<>(List.of(new BaseServiceResponseTest("test", "test")));
        var httpServletRequest = new MockHttpServletResponse();
        var responseEntity = PolyResponseEntityCreator.create(page, httpServletRequest);
        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("test", responseEntity.getBody().get(0).getValue());
        assertEquals("test", responseEntity.getBody().get(0).getId());
    }
}
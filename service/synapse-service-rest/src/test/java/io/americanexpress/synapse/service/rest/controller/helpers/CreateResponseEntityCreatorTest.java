package io.americanexpress.synapse.service.rest.controller.helpers;

import io.americanexpress.synapse.service.rest.controller.helpers.model.BaseServiceResponseTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateResponseEntityCreatorTest {

    @BeforeAll
    public static void init() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }


    @Test
    public void test_create_responseEntity() {
        ResponseEntity<BaseServiceResponseTest> response = CreateResponseEntityCreator.create(new BaseServiceResponseTest("test", "test"));
        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

}
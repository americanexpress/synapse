package io.americanexpress.synapse.service.rest.controller.reactive.helpers;

import io.americanexpress.synapse.service.rest.controller.helpers.model.BaseServiceResponseTest;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ReactivePolyResponseEntityCreatorTest {

    @Test
    public void test_create_responseEntity() {
        var page = new PageImpl<>(List.of(new BaseServiceResponseTest("test", "test")));
        var httpServletRequest = new MockHttpServletResponse();
        var responseEntity = ReactivePolyResponseEntityCreator.create(page, httpServletRequest);
        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue());
        Objects.requireNonNull(responseEntity.getBody())
                .subscribe(results -> {
                    assertEquals("test", results.getValue());
                    assertEquals("test", results.getId());
                });
    }
}
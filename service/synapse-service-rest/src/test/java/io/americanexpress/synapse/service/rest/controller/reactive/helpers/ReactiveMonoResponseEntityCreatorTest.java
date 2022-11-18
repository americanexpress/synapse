package io.americanexpress.synapse.service.rest.controller.reactive.helpers;

import io.americanexpress.synapse.service.rest.controller.helpers.model.BaseServiceResponseTest;
import org.junit.Test;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ReactiveMonoResponseEntityCreatorTest {

    @Test
    public void test_create_responseEntity() {
        var response = ReactiveMonoResponseEntityCreator.create(Mono.just(new BaseServiceResponseTest("test", "test")));
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        Objects.requireNonNull(response.getBody())
                .subscribe(results -> {
                    assertEquals("test", results.getValue());
                    assertEquals("test", results.getId());
                });
    }
}
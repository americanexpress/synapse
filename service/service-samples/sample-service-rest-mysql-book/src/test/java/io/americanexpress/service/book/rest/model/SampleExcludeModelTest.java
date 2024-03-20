package io.americanexpress.service.book.rest.model;

import io.americanexpress.synapse.framework.test.model.BaseModelsTest;

public class SampleExcludeModelTest extends BaseModelsTest {
    public SampleExcludeModelTest() {
        excludeClasses(
                ReadBookResponse.class,
                UpdateBookRequest.class
        );
    }
}

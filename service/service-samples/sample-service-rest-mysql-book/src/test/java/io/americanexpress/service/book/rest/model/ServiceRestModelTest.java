package io.americanexpress.service.book.rest.model;

import io.americanexpress.synapse.framework.test.model.BaseModelsTest;

public class ServiceRestModelTest extends BaseModelsTest {

    public ServiceRestModelTest(){

        excludeClasses(
                ReadBookResponse.class, CreateBookResponse.class);
    }
}

package io.americanexpress.service.book.rest.model;

import io.americanexpress.synapse.framework.test.model.BaseModelsTest;
import nl.jqno.equalsverifier.Warning;

public class SampleWarningModelTest extends BaseModelsTest{
    public SampleWarningModelTest() {
        addWarningsToSuppress(
                Warning.NONFINAL_FIELDS,
                Warning.ALL_FIELDS_SHOULD_BE_USED
        );
    }
}

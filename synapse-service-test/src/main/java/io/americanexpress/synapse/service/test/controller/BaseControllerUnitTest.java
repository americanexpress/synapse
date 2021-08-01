package io.americanexpress.synapse.service.test.controller;

import io.americanexpress.synapse.framework.test.model.ProfileConstants;
import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;
import io.americanexpress.synapse.utilities.common.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.annotation.Profile;

/**
 * The type Base controller test.
 * This class is not meant to be extended directly.
 *
 * @param <O> the type parameter
 */
@Profile(ProfileConstants.TEST)
public abstract class BaseControllerUnitTest<O extends BaseServiceResponse> extends BaseControllerTest<O>  {

    /**
     * Gets sample json response file name.
     *
     * @return the sample json response file name
     */
    protected abstract String getSampleJsonResponseFileName();

    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();
        final String fileNameWithSampleJsonResponse = getSampleJsonResponseFileName();
        if (StringUtils.isNotBlank(fileNameWithSampleJsonResponse)) {
            response = objectMapper.readValue(IOUtils.readFileToAString(fileNameWithSampleJsonResponse), getResponseType());
        }
    }
}

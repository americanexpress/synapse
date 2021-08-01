package io.americanexpress.synapse.service.test.controller;

import io.americanexpress.synapse.framework.exception.ApplicationClientException;
import io.americanexpress.synapse.framework.exception.model.ErrorCode;
import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;
import io.americanexpress.synapse.service.rest.service.BaseDeleteService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

/**
 * The type Base delete controller test.
 *
 * @param <O> the type parameter
 * @param <S> the type parameter
 */
public abstract class BaseDeleteControllerUnitTest<O extends BaseServiceResponse, S extends BaseDeleteService> extends BaseControllerUnitTest<O> {

    /**
     * The Service.
     */
    @MockBean
    protected S service;

    //Empty this one only because it is the delete functionality that doesn't have any request json body.
    @Override
    protected String getSampleJsonRequestFileName() {
        return StringUtils.EMPTY;
    }

    /**
     * SUCCESS
     * Delete given good response from service expected successful response.
     *
     * @throws Exception the exception
     */
    @Test
    public void delete_givenGoodResponseFromService_expectedSuccessfulResponse() throws Exception {
        testEndpoint(getEndpoint() + "/1", HttpMethod.DELETE, HttpStatus.NO_CONTENT);
    }

    /**
     * 4XX
     * Delete given exception thrown from service call expected exception thrown in response.
     *
     * @throws Exception the exception
     */
    @Test
    public void delete_givenExceptionThrownFromServiceCall_expectedExceptionThrownInResponse() throws Exception {
        Mockito.doThrow(new ApplicationClientException(StringUtils.EMPTY, ErrorCode.GENERIC_4XX_ERROR)).when(service).delete(any(String.class));
        testEndpoint(getEndpoint() + "/1", HttpMethod.DELETE, HttpStatus.BAD_REQUEST);
    }

    /**
     * 5XX
     * Delete given empty response from service expected no content response.
     *
     * @throws Exception the exception
     */
    @Test
    public void delete_givenEmptyResponseFromService_expectedNoContentResponse() throws Exception {
        doThrow(NullPointerException.class).when(service).delete(any(String.class));
        testEndpoint(getEndpoint() + "/1", HttpMethod.DELETE, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

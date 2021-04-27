package com.americanexpress.synapse.service.test.controller;

import com.americanexpress.synapse.framework.exception.ApplicationClientException;
import com.americanexpress.synapse.framework.exception.ApplicationServerException;
import com.americanexpress.synapse.framework.exception.model.ErrorCode;
import com.americanexpress.synapse.service.rest.model.BaseServiceRequest;
import com.americanexpress.synapse.service.rest.model.BaseServiceResponse;
import com.americanexpress.synapse.service.rest.service.BaseUpdateService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

/**
 * The type Base update controller test.
 *
 * @param <O> the type parameter
 * @param <I> the type parameter
 * @param <S> the type parameter
 */
public abstract class BaseUpdateControllerUnitTest<O extends BaseServiceResponse, I extends BaseServiceRequest, S extends BaseUpdateService<I>> extends BaseControllerUnitTest<O> {

    /**
     * The Service.
     */
    @MockBean
    protected S service;

    @Override
    protected String getSampleJsonResponseFileName() {
        return StringUtils.EMPTY;
    }


    /**
     * SUCCESS
     * Update given void from service expected successful response.
     *
     * @throws Exception the exception
     */
    @Test
    public void update_givenVoidFromService_expectedSuccessfulResponse() throws Exception {
        testEndpoint(getEndpoint(), HttpMethod.PUT, HttpStatus.NO_CONTENT);
    }

    /**
     * 4XX
     * Read given npe thrown in service call expected exception thrown in response.
     *
     * @throws Exception the exception
     */
    @Test
    public void update_givenApplicationClientExceptionThrownInServiceCall_expectedExceptionThrownInResponse() throws Exception {
        Mockito.doThrow(new ApplicationClientException(StringUtils.EMPTY, ErrorCode.GENERIC_4XX_ERROR)).when(service).update(ArgumentMatchers.any());
        testEndpoint(getEndpoint(), HttpMethod.PUT, HttpStatus.BAD_REQUEST);
    }

    /**
     * 5XX
     * Update given application server exception thrown in service call expected exception thrown in response.
     *
     * @throws Exception the exception
     */
    @Test
    public void update_givenApplicationServerExceptionThrownInServiceCall_expectedExceptionThrownInResponse() throws Exception {
        final ClassNotFoundException classNotFoundException = new ClassNotFoundException();
        Mockito.doThrow(new ApplicationServerException(classNotFoundException)).when(service).update(ArgumentMatchers.any());
        testEndpoint(getEndpoint(), HttpMethod.PUT, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 5XX
     * Update given npe thrown in service call expected exception thrown in response.
     *
     * @throws Exception the exception
     */
    @Test
    public void update_givenNPEThrownInServiceCall_expectedExceptionThrownInResponse() throws Exception {
        Mockito.doThrow(new NullPointerException()).when(service).update(ArgumentMatchers.any());
        testEndpoint(getEndpoint(), HttpMethod.PUT, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

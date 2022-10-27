package io.americanexpress.synapse.service.test.controller;

import io.americanexpress.synapse.framework.exception.ApplicationClientException;
import io.americanexpress.synapse.framework.exception.ApplicationServerException;
import io.americanexpress.synapse.framework.exception.model.ErrorCode;
import io.americanexpress.synapse.service.rest.model.BaseServiceRequest;
import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;
import io.americanexpress.synapse.service.rest.service.BaseCreateService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

/**
 * The type Base create controller test.
 *
 * @param <O> the type parameter
 * @param <I> the type parameter
 * @param <S> the type parameter
 */
public abstract class BaseCreateMonoControllerUnitTest<O extends BaseServiceResponse, I extends BaseServiceRequest, S extends BaseCreateService<I, O>> extends BaseControllerUnitTest<O>{

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
     * Read given good response from service expected successful response.
     *
     * @throws Exception the exception
     */
    @Test
    public void create_givenGoodResponseFromService_expectedSuccessfulResponse() throws Exception {
        BDDMockito.given(service.create(ArgumentMatchers.any(), ArgumentMatchers.any())).willReturn(response);
        testEndpoint(getEndpoint(), HttpMethod.POST, HttpStatus.CREATED);
    }

    /**
     * 4XX
     * Read given application client exception thrown in service call expected exception thrown in response.
     *
     * @throws Exception the exception
     */
    @Test
    public void create_givenApplicationClientExceptionThrownInServiceCall_expectedExceptionThrownInResponse() throws Exception {
        BDDMockito.given(service.create(ArgumentMatchers.any(),ArgumentMatchers.any())).willThrow(new ApplicationClientException(StringUtils.EMPTY, ErrorCode.GENERIC_4XX_ERROR));
        testEndpoint(getEndpoint(), HttpMethod.POST, HttpStatus.BAD_REQUEST);
    }

    /**
     * 5XX - ApplicationServerException
     * Read given application server exception thrown in service call expected exception thrown in response.
     *
     * @throws Exception the exception
     */
    @Test
    public void create_givenApplicationServerExceptionThrownInServiceCall_expectedExceptionThrownInResponse() throws Exception {
        final ClassNotFoundException classNotFoundException = new ClassNotFoundException();
        BDDMockito.given(service.create(ArgumentMatchers.any(), ArgumentMatchers.any())).willThrow(new ApplicationServerException(classNotFoundException));
        testEndpoint(getEndpoint(), HttpMethod.POST, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 5XX - NPE
     * Read given npe thrown in service call expected exception thrown in response.
     *
     * @throws Exception the exception
     */
    @Test
    public void create_givenNPEThrownInServiceCall_expectedExceptionThrownInResponse() throws Exception {
        BDDMockito.given(service.create(ArgumentMatchers.any(),ArgumentMatchers.any())).willThrow(new NullPointerException());
        testEndpoint(getEndpoint(), HttpMethod.POST, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

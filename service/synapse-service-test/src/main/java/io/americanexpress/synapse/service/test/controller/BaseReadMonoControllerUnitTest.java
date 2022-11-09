package io.americanexpress.synapse.service.test.controller;

import io.americanexpress.synapse.framework.exception.ApplicationClientException;
import io.americanexpress.synapse.framework.exception.ApplicationServerException;
import io.americanexpress.synapse.framework.exception.model.ErrorCode;
import io.americanexpress.synapse.service.rest.model.BaseServiceRequest;
import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;
import io.americanexpress.synapse.service.rest.service.BaseReadMonoService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

/**
 * The type Base read mono controller test.
 *
 * @param <I> the type parameter
 * @param <O> the type parameter
 * @param <S> the type parameter
 */
public abstract class BaseReadMonoControllerUnitTest<O extends BaseServiceResponse, I extends BaseServiceRequest, S extends BaseReadMonoService<I, O>> extends BaseControllerUnitTest<O> {

    /**
     * The Service.
     */
    @MockBean
    protected S service;

    //Since no request in read mono then this one we just return and empty String
    @Override
    protected String getSampleJsonRequestFileName() {
        return StringUtils.EMPTY;
    }

    /**
     * SUCCESS
     * Read given good response from service expected successful response.
     *
     * @throws Exception the exception
     */
    @Test
    public void read_givenGoodResponseFromService_expectedSuccessfulResponse() throws Exception {
        BDDMockito.given(service.read(ArgumentMatchers.any(), ArgumentMatchers.any())).willReturn(response);
        testEndpoint(getEndpoint(), HttpMethod.POST);
    }

    /**
     * NO_CONTENT
     * Read given empty response from service expected no content response.
     *
     * @throws Exception the exception
     */
    @Test
    public void read_givenEmptyResponseFromService_expectedNoContentResponse() throws Exception {
        BDDMockito.given(service.read(ArgumentMatchers.any(), ArgumentMatchers.any())).willReturn(null);
        testEndpoint(getEndpoint(), HttpMethod.POST, HttpStatus.NO_CONTENT);
    }

    /**
     * 4XX
     * Read given application client exception thrown in service call expected exception thrown in response.
     *
     * @throws Exception the exception
     */
    @Test
    public void read_givenApplicationClientExceptionThrownInServiceCall_expectedExceptionThrownInResponse() throws Exception {
        BDDMockito.given(service.read(ArgumentMatchers.any(), ArgumentMatchers.any())).willThrow(new ApplicationClientException(StringUtils.EMPTY, ErrorCode.GENERIC_4XX_ERROR));
        testEndpoint(getEndpoint(), HttpMethod.POST, HttpStatus.BAD_REQUEST);
    }

    /**
     * 5XX - ApplicationServerException
     * Read given application server exception thrown in service call expected exception thrown in response.
     *
     * @throws Exception the exception
     */
    @Test
    public void read_givenApplicationServerExceptionThrownInServiceCall_expectedExceptionThrownInResponse() throws Exception {
        final ClassNotFoundException classNotFoundException = new ClassNotFoundException();
        BDDMockito.given(service.read(ArgumentMatchers.any(), ArgumentMatchers.any())).willThrow(new ApplicationServerException(classNotFoundException));
        testEndpoint(getEndpoint(), HttpMethod.POST, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 5XX - NPE
     * Read given npe thrown in service call expected exception thrown in response.
     *
     * @throws Exception the exception
     */
    @Test
    public void read_givenNPEThrownInServiceCall_expectedExceptionThrownInResponse() throws Exception {
        BDDMockito.given(service.read(ArgumentMatchers.any(), ArgumentMatchers.any())).willThrow(new NullPointerException());
        testEndpoint(getEndpoint(), HttpMethod.POST, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

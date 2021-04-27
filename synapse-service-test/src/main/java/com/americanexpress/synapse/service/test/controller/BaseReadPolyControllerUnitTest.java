package com.americanexpress.synapse.service.test.controller;

import com.americanexpress.synapse.framework.exception.ApplicationClientException;
import com.americanexpress.synapse.framework.exception.ApplicationServerException;
import com.americanexpress.synapse.framework.exception.model.ErrorCode;
import com.americanexpress.synapse.service.rest.model.BaseServiceRequest;
import com.americanexpress.synapse.service.rest.model.BaseServiceResponse;
import com.americanexpress.synapse.service.rest.service.BaseReadPolyService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Base read poly controller test.
 *
 * @param <I> the type parameter
 * @param <O> the type parameter
 * @param <S> the type parameter
 */
public abstract class BaseReadPolyControllerUnitTest<O extends BaseServiceResponse, I extends BaseServiceRequest, S extends BaseReadPolyService<I, O>> extends BaseControllerUnitTest<O> {

    /**
     * The Service.
     */
    @MockBean
    protected S service;

    /**
     * SUCCESS
     * Read given good response from service expected successful response.
     *
     * @throws Exception the exception
     */
    @Test
    public void read_givenGoodResponseFromService_expectedSuccessfulResponse() throws Exception {
        List<O> responses = new ArrayList<>();
        responses.add(response);
        Page<O> pagedResponse = new PageImpl<>(responses);
        BDDMockito.given(service.read(ArgumentMatchers.any())).willReturn(pagedResponse);
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
        BDDMockito.given(service.read(ArgumentMatchers.any())).willReturn(null);
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
        BDDMockito.given(service.read(ArgumentMatchers.any())).willThrow(new ApplicationClientException(StringUtils.EMPTY, ErrorCode.GENERIC_4XX_ERROR));
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
        BDDMockito.given(service.read(ArgumentMatchers.any())).willThrow(new ApplicationServerException(classNotFoundException));
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
        BDDMockito.given(service.read(ArgumentMatchers.any())).willThrow(new NullPointerException());
        testEndpoint(getEndpoint(), HttpMethod.POST, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

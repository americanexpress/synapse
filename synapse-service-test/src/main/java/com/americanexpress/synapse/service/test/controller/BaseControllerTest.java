package com.americanexpress.synapse.service.test.controller;

import com.americanexpress.synapse.service.rest.model.BaseServiceResponse;
import com.americanexpress.synapse.utilities.common.io.IOUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * The type Base controller test.
 * This class is not meant to be extended directly.
 *
 * @param <O> the type parameter
 */
@ExtendWith(SpringExtension.class)
public abstract class BaseControllerTest<O extends BaseServiceResponse> {

    /**
     * Gets endpoint.
     *
     * @return the endpoint
     */
    protected abstract String getEndpoint();

    /**
     * The Response.
     */
    protected O response;

    /**
     * The Object mapper.
     */
    @Autowired
    protected ObjectMapper objectMapper;

    /**
     * The Mock mvc.
     */
    protected MockMvc mockMvc;

    /**
     * The Json request.
     */
    protected String jsonRequest;

    /**
     * The Web application context.
     */
    @Autowired
    protected WebApplicationContext webApplicationContext;

    /**
     * Gets sample json request file name.
     *
     * @return the sample json request file name
     */
    protected abstract String getSampleJsonRequestFileName();

    /**
     * Test endpoint result actions.
     *
     * @param endPoint the end point
     * @return the result actions
     * @throws Exception the exception
     */
    protected ResultActions testEndpoint(final String endPoint) throws Exception {
        return sendRequestAndAssert(endPoint, HttpMethod.POST, HttpStatus.OK, null);
    }

    /**
     * Test endpoint result actions.
     *
     * @param endPoint         the end point
     * @param responseExpected the response expected
     * @return the result actions
     * @throws Exception the exception
     */
    protected ResultActions testEndpoint(final String endPoint, final String responseExpected) throws Exception {
        return sendRequestAndAssert(endPoint, HttpMethod.POST, HttpStatus.OK, responseExpected);
    }

    /**
     * Test endpoint result actions.
     *
     * @param endPoint   the end point
     * @param httpMethod the http method
     * @return the result actions
     * @throws Exception the exception
     */
    protected ResultActions testEndpoint(final String endPoint, final HttpMethod httpMethod) throws Exception {
        return sendRequestAndAssert(endPoint, httpMethod, HttpStatus.OK, null);
    }

    /**
     * Test endpoint result actions.
     *
     * @param endPoint         the end point
     * @param httpMethod       the http method
     * @param responseExpected the response expected
     * @return the result actions
     * @throws Exception the exception
     */
    protected ResultActions testEndpoint(final String endPoint, final HttpMethod httpMethod, final String responseExpected) throws Exception {
        return sendRequestAndAssert(endPoint, httpMethod, HttpStatus.OK, responseExpected);
    }

    /**
     * Test endpoint result actions.
     *
     * @param endPoint           the end point
     * @param httpStatusExpected the http status expected
     * @return the result actions
     * @throws Exception the exception
     */
    protected ResultActions testEndpoint(final String endPoint, final HttpStatus httpStatusExpected) throws Exception {
        return sendRequestAndAssert(endPoint, HttpMethod.POST, httpStatusExpected, null);
    }

    /**
     * Test endpoint result actions.
     *
     * @param endPoint           the end point
     * @param httpMethod         the http method
     * @param httpStatusExpected the http status expected
     * @return the result actions
     * @throws Exception the exception
     */
    protected ResultActions testEndpoint(final String endPoint, final HttpMethod httpMethod, final HttpStatus httpStatusExpected) throws Exception {
        return sendRequestAndAssert(endPoint, httpMethod, httpStatusExpected, null);
    }

    /**
     * Test endpoint result actions.
     *
     * @param endPoint           the end point
     * @param httpStatusExpected the http status expected
     * @param responseExpected   the response expected
     * @return the result actions
     * @throws Exception the exception
     */
    protected ResultActions testEndpoint(final String endPoint, final HttpStatus httpStatusExpected, final String responseExpected) throws Exception {
        return sendRequestAndAssert(endPoint, HttpMethod.POST, httpStatusExpected, responseExpected);
    }

    /**
     * Test endpoint result actions.
     *
     * @param endPoint           the end point
     * @param httpMethod         the http method
     * @param httpStatusExpected the http status expected
     * @param responseExpected   the response expected
     * @return the result actions
     * @throws Exception the exception
     */
    protected ResultActions testEndpoint(final String endPoint, final HttpMethod httpMethod, final HttpStatus httpStatusExpected, final String responseExpected) throws Exception {
        return sendRequestAndAssert(endPoint, httpMethod, httpStatusExpected, responseExpected);
    }

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @BeforeEach
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).alwaysDo(MockMvcResultHandlers.print()).build();
    }

    private ResultActions sendRequestAndAssert(final String endPoint, final HttpMethod httpMethod, final HttpStatus httpStatusExpected, final String responseExpected) throws Exception {

        final String fileNameWithSampleJsonRequest = getSampleJsonRequestFileName();
        if (StringUtils.isNotBlank(fileNameWithSampleJsonRequest)) {
            jsonRequest = IOUtils.readFileToAString(fileNameWithSampleJsonRequest);
        }
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = null;

        if (HttpMethod.POST.equals(httpMethod)) {
            mockHttpServletRequestBuilder = MockMvcRequestBuilders.post(endPoint);
        } else if (HttpMethod.PUT.equals(httpMethod)) {
            mockHttpServletRequestBuilder = MockMvcRequestBuilders.put(endPoint);
        } else if (HttpMethod.PATCH.equals(httpMethod)) {
            mockHttpServletRequestBuilder = MockMvcRequestBuilders.patch(endPoint);
        } else if (HttpMethod.DELETE.equals(httpMethod)) {
            mockHttpServletRequestBuilder = MockMvcRequestBuilders.delete(endPoint);
        } else if (HttpMethod.GET.equals(httpMethod)) {
            mockHttpServletRequestBuilder = MockMvcRequestBuilders.get(endPoint);
        }

        assert mockHttpServletRequestBuilder != null;
        mockHttpServletRequestBuilder.contentType(MediaType.APPLICATION_JSON_VALUE);

        if (StringUtils.isNotBlank(jsonRequest)) {
            mockHttpServletRequestBuilder.content(jsonRequest);
        }

        final ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder).andDo(MockMvcResultHandlers.print());

        if (httpStatusExpected != null) {
            if (HttpStatus.OK.equals(httpStatusExpected)) {
                resultActions.andExpect(MockMvcResultMatchers.status().isOk());
            } else if (HttpStatus.CREATED.equals(httpStatusExpected)) {
                resultActions.andExpect(MockMvcResultMatchers.status().isCreated());
            } else if (HttpStatus.BAD_REQUEST.equals(httpStatusExpected)) {
                resultActions.andExpect(MockMvcResultMatchers.status().is4xxClientError());
            } else if (HttpStatus.INTERNAL_SERVER_ERROR.equals(httpStatusExpected)) {
                resultActions.andExpect(MockMvcResultMatchers.status().is5xxServerError());
            } else if (HttpStatus.FORBIDDEN.equals(httpStatusExpected)) {
                resultActions.andExpect(MockMvcResultMatchers.status().isForbidden());
            } else if (HttpStatus.UNAUTHORIZED.equals(httpStatusExpected)) {
                resultActions.andExpect(MockMvcResultMatchers.status().isUnauthorized());
            } else if (HttpStatus.NO_CONTENT.equals(httpStatusExpected)) {
                resultActions.andExpect(MockMvcResultMatchers.status().isNoContent());
            } else {
                Assertions.fail();
            }
        }

        if (StringUtils.isNotBlank(responseExpected)) {
            resultActions.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(responseExpected)));
        }

        return resultActions;
    }

    /**
     * Gets response type.
     *
     * @return the response type
     */
    protected Class<O> getResponseType() {
        final Type type = getClass().getGenericSuperclass();
        return ((Class<O>) ((ParameterizedType) type).getActualTypeArguments()[0]);
    }
}

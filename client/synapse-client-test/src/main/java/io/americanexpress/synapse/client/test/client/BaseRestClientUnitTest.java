/*
 * Copyright 2020 American Express Travel Related Services Company, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.americanexpress.synapse.client.test.client;

import io.americanexpress.synapse.client.rest.client.BaseRestClient;
import io.americanexpress.synapse.client.rest.factory.BaseClientHttpHeadersFactory;
import io.americanexpress.synapse.client.rest.model.BaseClientRequest;
import io.americanexpress.synapse.client.rest.model.BaseClientResponse;
import io.americanexpress.synapse.client.rest.model.QueryParameter;
import io.americanexpress.synapse.framework.exception.ApplicationClientException;
import io.americanexpress.synapse.framework.exception.model.ErrorCode;
import io.americanexpress.synapse.framework.test.CommonAssertionMessages;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.ResponseActions;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

/**
 * {@code BaseRestClientUnitTest} is the extensible class for unit testing rest clients.
 *
 * @param <I> input request type
 * @param <O> output response type
 * @param <H> httpHeadersFactory used to set the HTTP headers for each web service call
 * @param <C> the client
 */
public abstract class BaseRestClientUnitTest<I extends BaseClientRequest,
        O extends BaseClientResponse,
        H extends BaseClientHttpHeadersFactory<I>,
        C extends BaseRestClient<I, O, H>> extends BaseRestClientTest<I, O, H, C> {

    public static final String CLIENT_RESPONSE = "Client response {}";
    protected O clientResponse;

    protected HttpHeaders headers;

	protected MockRestServiceServer mockServer;

    protected ResponseActions responseActions;

    @BeforeEach
    void init() throws Exception {
        this.clientRequest = mockDefaultClientRequest();
        this.clientResponse = mockDefaultClientResponse();
        this.headers = getDefaultClientHeaders();
        mockServer = getMockServer();
        responseActions = getResponseActions(mockServer, url);
    }

    @Test
    void callMonoService_givenValidClientRequest_expectedNonNullClientResponse() throws JsonProcessingException {
        responseActions.andRespond(withStatus(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(mapper.writeValueAsString(clientResponse)));
        O actual = restClient.callMonoService(headers, clientRequest);
        mockServer.verify();
        assertNotNull(actual, CommonAssertionMessages.RESPONSE_IS_NULL);
        logger.debug(CLIENT_RESPONSE, actual);
    }

    @Test
    void callMonoService_givenClientRequestWithPathVariable_expectedNonNullClientResponse() throws JsonProcessingException {

        // Create a mock server to test only this path variable URI
        // since it requires changing the expectations of the mockServer
        // that occurs @BeforeEach unit test method
        // (the @BeforeEach unit test method expects the mockServer on the base URL
        // and any additional .expect* will be added to the mockServer)

        // Arrange this path variable mock server's expectations
        MockRestServiceServer pathVariableMockServer = getMockServer();

        ResponseActions pathVariableResponseActions = getResponseActions(pathVariableMockServer, url + "/" + mockPathVariable());

        pathVariableResponseActions.andRespond(withStatus(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(mapper.writeValueAsString(clientResponse)));

        // Act on the path variable mock server
        O actual = restClient.callMonoService(headers, clientRequest, mockPathVariable());

        // Assert that the expectations have been met
        pathVariableMockServer.verify();
        assertNotNull(actual, CommonAssertionMessages.RESPONSE_IS_NULL);
        logger.debug(CLIENT_RESPONSE, actual);
    }

    @Test
    void callMonoService_givenClientRequestWithQueryParameter_expectedNonNullClientResponse() throws JsonProcessingException {
    	
        // Create a mock server to test only this path variable URI
        // since it requires changing the expectations of the mockServer
        // that occurs @BeforeEach unit test method
        // (the @BeforeEach unit test method expects the mockServer on the base URL
        // and any additional .expect* will be added to the mockServer)

        // Arrange this path variable mock server's expectations
        MockRestServiceServer queryParameterMockServer = getMockServer();

        ResponseActions pathVariableResponseActions = getResponseActions(queryParameterMockServer, url + "?name=bob");

        pathVariableResponseActions.andRespond(withStatus(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(mapper.writeValueAsString(clientResponse)));

        // Act on the path variable mock server
        O actual = restClient.callMonoService(headers, clientRequest, mockQueryParameter());
        queryParameterMockServer.verify();
        assertNotNull(actual, CommonAssertionMessages.RESPONSE_IS_NULL);
        logger.debug(CLIENT_RESPONSE, actual);
    }

    @Test
    void callMonoService_givenClientRequestWithPathVariableAndQueryParameter_expectedNonNullClientResponse() throws JsonProcessingException {
    	
        // Create a mock server to test only this path variable URI
        // since it requires changing the expectations of the mockServer
        // that occurs @BeforeEach unit test method
        // (the @BeforeEach unit test method expects the mockServer on the base URL
        // and any additional .expect* will be added to the mockServer)

        // Arrange this path variable mock server's expectations
        MockRestServiceServer queryParameterMockServer = getMockServer();

        ResponseActions pathVariableResponseActions = getResponseActions(queryParameterMockServer, url + "/" + mockPathVariable() + "?name=bob");

        pathVariableResponseActions.andRespond(withStatus(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(mapper.writeValueAsString(clientResponse)));

        // Act on the path variable mock server
        O actual = restClient.callMonoService(headers, clientRequest, mockQueryParameter(), mockPathVariable());
        queryParameterMockServer.verify();
        assertNotNull(actual, CommonAssertionMessages.RESPONSE_IS_NULL);
        logger.debug(CLIENT_RESPONSE, actual);
    }

    @Test
    void callMonoService_givenClientError_expectedApplicationClientException() throws IOException {
        responseActions.andRespond(withStatus(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(mapper.writeValueAsString(clientResponse)));
        Assertions.assertThrows(ApplicationClientException.class, this::callMonoServiceAndAssertErrorResponse, CommonAssertionMessages.EXCEPTION_NOT_THROWN);
    }

    @Test
    void callMonoService_givenServerError_expectedApplicationClientException() throws IOException {
        responseActions.andRespond(withStatus(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(mapper.writeValueAsString(clientResponse)));
        Assertions.assertThrows(ApplicationClientException.class, this::callMonoServiceAndAssertErrorResponse, CommonAssertionMessages.EXCEPTION_NOT_THROWN);
    }
    
    private MockRestServiceServer getMockServer() {
        return MockRestServiceServer
            .bindTo(restClient.getRestTemplate())
            .ignoreExpectOrder(true)
            .bufferContent()
            .build();
    }
    
    private ResponseActions getResponseActions(MockRestServiceServer mockServer, String url) {
        URI uri = URI.create(url);
        return mockServer.expect(ExpectedCount.once(), requestTo(uri))
        	.andExpect(method(restClient.getHttpMethod()));
    }
    
    private List<QueryParameter> mockQueryParameter() {
        List<QueryParameter> queryParameters = new ArrayList<>();
        QueryParameter queryParameter = new QueryParameter("name", "bob");
        queryParameters.add(queryParameter);
        return queryParameters;
    }
    
    private String mockPathVariable() {
        return "11111111111";
    }
    
    private void callMonoServiceAndAssertErrorResponse() {
        try {
            restClient.callMonoService(headers, clientRequest);
            mockServer.verify();
        } catch (ApplicationClientException applicationClientException) {
            assertEquals(ErrorCode.GENERIC_4XX_ERROR, applicationClientException.getErrorCode());
            throw applicationClientException;
        }
    }
    
    protected abstract I mockDefaultClientRequest();

    protected abstract O mockDefaultClientResponse();

    protected abstract HttpHeaders getDefaultClientHeaders() throws Exception;
}

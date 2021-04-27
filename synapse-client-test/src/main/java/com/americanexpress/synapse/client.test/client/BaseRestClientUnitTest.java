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
package com.americanexpress.synapse.client.test.client;

import com.americanexpress.synapse.client.rest.client.BaseRestClient;
import com.americanexpress.synapse.client.rest.factory.BaseClientHttpHeadersFactory;
import com.americanexpress.synapse.client.rest.model.BaseClientRequest;
import com.americanexpress.synapse.client.rest.model.BaseClientResponse;
import com.americanexpress.synapse.client.rest.model.ClientHeaders;
import com.americanexpress.synapse.client.rest.model.QueryParameter;
import com.americanexpress.synapse.client.test.model.MockClientResponse;
import com.americanexpress.synapse.framework.exception.ApplicationClientException;
import com.americanexpress.synapse.framework.exception.ApplicationServerException;
import com.americanexpress.synapse.framework.exception.model.ErrorCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.ResponseActions;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

public abstract class BaseRestClientUnitTest<I extends BaseClientRequest,
        O extends BaseClientResponse,
        H extends BaseClientHttpHeadersFactory<I>,
        C extends BaseRestClient<I, O, H>> extends BaseRestClientTest<I, O, H, C> {

    protected MockRestServiceServer mockServer;

    protected O response;

    protected ClientHeaders clientHeaders;

    protected abstract I mockDefaultClientRequest();

    protected abstract O mockDefaultClientResponse();

    protected abstract ClientHeaders getDefaultClientHeaders() throws Exception;

    protected ResponseActions responseAction;

    private List<QueryParameter> mockQueryParameter() {
        List<QueryParameter> queryParameters = new ArrayList<>();
        QueryParameter queryParameter = new QueryParameter("name", "bob");
        queryParameters.add(queryParameter);
        return queryParameters;
    }

    private String mockPathVariable() {
        return "11111111111";
    }

    private MockRestServiceServer getMockServer() {
        return MockRestServiceServer
                .bindTo(client.getRestTemplate())
                .ignoreExpectOrder(true)
                .bufferContent()
                .build();
    }

    private ResponseActions getResponseActions(MockRestServiceServer mockServer, String url) {
        URI uri = URI.create(url);
        return mockServer.expect(ExpectedCount.once(), requestTo(uri))
                .andExpect(method(client.getHttpMethod()));
    }

    private static final String RESPONSE_IS_NULL = "Response is null.";


    @BeforeEach
    public void init() throws Exception {
        this.request = mockDefaultClientRequest();
        this.response = mockDefaultClientResponse();
        this.clientHeaders = getDefaultClientHeaders();
        mockServer = getMockServer();
        responseAction = getResponseActions(mockServer, url);
    }

    protected void callServiceAndAssertErrorResponse() {
        try {
            client.callService(clientHeaders, request, MockClientResponse.class);
            mockServer.verify();
        } catch (ApplicationClientException e) {
            assertEquals(ErrorCode.GENERIC_4XX_ERROR, e.getErrorCode());
            throw e;
        }
    }

    @Test
    public void callService_clean() throws JsonProcessingException {
        responseAction.andRespond(withStatus(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(mapper.writeValueAsString(response)));
        O actual = client.callService(clientHeaders, request, MockClientResponse.class);
        mockServer.verify();
        assertNotNull(actual, RESPONSE_IS_NULL);
        logger.debug("Client response {}", actual);
    }

    @Test
    public void callService_cleanPathVariable() throws JsonProcessingException {

        // Create a mock server to test only this path variable URI
        // since it requires changing the expectations of the mockServer
        // that occurs @BeforeEach unit test method
        // (the @BeforeEach unit test method expects the mockServer on the base URL
        // and any additional .expect* will be added to the mockServer)

        // Arrange this path variable mock server's expectations
        MockRestServiceServer pathVariableMockServer = getMockServer();

        ResponseActions pathVariableResponseActions = getResponseActions(pathVariableMockServer, url + "/" + mockPathVariable());

        pathVariableResponseActions.andRespond(withStatus(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(mapper.writeValueAsString(response)));

        // Act on the path variable mock server
        O actual = client.callService(clientHeaders, request, MockClientResponse.class, mockPathVariable());

        // Assert that the expectations have been met
        pathVariableMockServer.verify();
        assertNotNull(actual, RESPONSE_IS_NULL);
        logger.debug("Client response {}", actual);
    }

    @Test
    public void callService_cleanQueryParameter() throws JsonProcessingException {
        // Create a mock server to test only this path variable URI
        // since it requires changing the expectations of the mockServer
        // that occurs @BeforeEach unit test method
        // (the @BeforeEach unit test method expects the mockServer on the base URL
        // and any additional .expect* will be added to the mockServer)

        // Arrange this path variable mock server's expectations
        MockRestServiceServer queryParameterMockServer = getMockServer();

        ResponseActions pathVariableResponseActions = getResponseActions(queryParameterMockServer, url + "?name=bob");

        pathVariableResponseActions.andRespond(withStatus(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(mapper.writeValueAsString(response)));

        // Act on the path variable mock server
        O actual = client.callService(clientHeaders, request, MockClientResponse.class, mockQueryParameter());
        queryParameterMockServer.verify();
        assertNotNull(actual, RESPONSE_IS_NULL);
        logger.debug("Client response {}", actual);
    }

    @Test
    public void callService_cleanPathVariableAndQueryParameter() throws JsonProcessingException {
        // Create a mock server to test only this path variable URI
        // since it requires changing the expectations of the mockServer
        // that occurs @BeforeEach unit test method
        // (the @BeforeEach unit test method expects the mockServer on the base URL
        // and any additional .expect* will be added to the mockServer)

        // Arrange this path variable mock server's expectations
        MockRestServiceServer queryParameterMockServer = getMockServer();

        ResponseActions pathVariableResponseActions = getResponseActions(queryParameterMockServer, url + "/" + mockPathVariable() + "?name=bob");

        pathVariableResponseActions.andRespond(withStatus(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(mapper.writeValueAsString(response)));

        // Act on the path variable mock server
        O actual = client.callService(clientHeaders, request, MockClientResponse.class, mockQueryParameter(), mockPathVariable());
        queryParameterMockServer.verify();
        assertNotNull(actual, RESPONSE_IS_NULL);
        logger.debug("Client response {}", actual);
    }

    @Test
    public void callService_clientError() throws IOException {
        responseAction.andRespond(withStatus(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(mapper.writeValueAsString(response)));
        Assertions.assertThrows(ApplicationClientException.class, this::callServiceAndAssertErrorResponse);
    }

    @Test
    public void callService_serverError() throws IOException {
        responseAction.andRespond(withStatus(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(mapper.writeValueAsString(response)));
        Assertions.assertThrows(ApplicationServerException.class, this::callServiceAndAssertErrorResponse);
    }
}

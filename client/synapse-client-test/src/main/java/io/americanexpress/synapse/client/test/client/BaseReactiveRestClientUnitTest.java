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

import com.fasterxml.jackson.core.JsonProcessingException;
import io.americanexpress.synapse.client.rest.client.BaseReactiveRestClient;
import io.americanexpress.synapse.client.rest.factory.BaseClientHttpHeadersFactory;
import io.americanexpress.synapse.client.rest.model.BaseClientRequest;
import io.americanexpress.synapse.client.rest.model.BaseClientResponse;
import io.americanexpress.synapse.framework.exception.ApplicationServerException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * {@code BaseReactiveRestClientUnitTest} is the base test class for reactive client unit tests.
 *
 * @param <I> the input type parameter
 * @param <O> the output type parameter
 * @param <H> httpHeadersFactory used to set the HTTP headers for each web service call
 * @param <C> the client type parameter
 */
public abstract class BaseReactiveRestClientUnitTest<I extends BaseClientRequest,
        O extends BaseClientResponse,
        H extends BaseClientHttpHeadersFactory<I>,
        C extends BaseReactiveRestClient<I, O, H>> extends BaseClientTest {

    /**
     * The Client.
     */
    @Autowired
    C client;

    /**
     * Call mono service given valid request expected valid response.
     */
    @Test
    void callMonoService_givenValidRequest_expectedValidResponse() {
        WebClient webClient = WebClient.builder()
                .exchangeFunction(clientRequest ->
                {
                    try {
                        return Mono.just(ClientResponse.create(HttpStatus.OK)
                                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                .body(mapper.writeValueAsString(mockDefaultClientResponse())).build());
                    } catch (JsonProcessingException exception) {
                        throw new ApplicationServerException(exception);
                    }
                }).build();
        client.setWebClient(webClient);

        Mono<O> clientResponse = client.callMonoService(mockClientHeaders(), mockDefaultClientRequest(), mockPathVariables());

        StepVerifier.create(clientResponse)
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }

    /**
     * Mock default client request .
     *
     * @return the
     */
    public abstract I mockDefaultClientRequest();

    /**
     * Mock default client response o.
     *
     * @return the o
     */
    public abstract O mockDefaultClientResponse();

    /**
     * Mock client headers http headers.
     *
     * @return the http headers
     */
    public abstract HttpHeaders mockClientHeaders();

    /**
     * Mock path variables string.
     *
     * @return the string
     */
    public abstract String mockPathVariables();
}

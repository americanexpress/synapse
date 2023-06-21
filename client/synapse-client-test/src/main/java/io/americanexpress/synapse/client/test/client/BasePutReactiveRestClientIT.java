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

import io.americanexpress.synapse.client.rest.client.BasePutReactiveRestClient;
import io.americanexpress.synapse.client.rest.factory.BaseClientHttpHeadersFactory;
import io.americanexpress.synapse.client.rest.model.BaseClientRequest;
import io.americanexpress.synapse.client.rest.model.BaseClientResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * {@code BasePutReactiveRestClientIT} is the extensible class for IT testing PUT reactive rest clients.
 *
 * @param <I> the input type
 * @param <O> the output type
 * @param <H> httpHeadersFactory used to set the HTTP headers for each web service call
 * @param <C> the reactive client type
 */
@ExtendWith(SpringExtension.class)
public abstract class BasePutReactiveRestClientIT<I extends BaseClientRequest,
        O extends BaseClientResponse,
        H extends BaseClientHttpHeadersFactory<I>,
        C extends BasePutReactiveRestClient<I, O, H>> extends BaseReactiveRestClientIT<I, O, H, C> {

    /**
     * Call mono service given valid request expected success response.
     */
    @Test
    void callMonoService_givenValidRequest_expectedSuccessResponse() {
        Mono<O> response = client.callMonoService(getDefaultClientHeaders(), mockDefaultClientRequest());
        StepVerifier.create(response)
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }

    /**
     * Call mono service given invalid id expected error.
     */
    @Test
    void callMonoService_givenInvalidId_expectedError() {
        Mono<O> response = client.callMonoService(getDefaultClientHeaders(), mockInvalidClientRequest());
        StepVerifier.create(response)
                .expectError()
                .verify();
    }

}

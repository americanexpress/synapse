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
package io.americanexpress.sample.client.reactive.football.client;

import io.americanexpress.sample.client.reactive.football.config.FootballReactiveClientTestConfig;
import io.americanexpress.sample.client.reactive.football.factory.FootballReactiveClientHeadersFactory;
import io.americanexpress.sample.client.reactive.football.model.FootBallClientRequest;
import io.americanexpress.sample.client.reactive.football.model.FootballClientResponse;
import io.americanexpress.synapse.client.test.client.BaseGetReactiveRestClientIT;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * {@code FootballGetReactiveRestClientIT} tests the {@link FootballGetReactiveRestClient}.
 *
 * @author eperjust
 */
@ContextConfiguration(classes = FootballReactiveClientTestConfig.class)
@ExtendWith(SpringExtension.class)
class FootballGetReactiveRestClientIT extends BaseGetReactiveRestClientIT<FootBallClientRequest, FootballClientResponse, FootballReactiveClientHeadersFactory, FootballGetReactiveRestClient> {
    @Override
    protected String mockPathVariables() {
        return null;
    }

    @Override
    protected HttpHeaders getDefaultClientHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    @Override
    protected FootBallClientRequest mockDefaultClientRequest() {
        return null;
    }

    @Override
    protected FootBallClientRequest mockInvalidClientRequest() {
        return null;
    }
}

package io.americanexpress.sample.client.reactive.football.client;

import io.americanexpress.sample.client.reactive.football.model.FootBallRequest;
import io.americanexpress.sample.client.reactive.football.model.FootballResponse;
import io.americanexpress.synapse.client.rest.client.BaseGetReactiveRestClient;
import io.americanexpress.synapse.client.rest.client.ReactiveRestClient;
import io.americanexpress.synapse.client.rest.handler.BaseReactiveRestResponseErrorHandler;

/**
 * {@code FootballReactiveClient} contains configurations for reactive football client.
 *
 * @author eperjust
 */
@ReactiveRestClient
public class FootballReactiveClient extends BaseGetReactiveRestClient<FootBallRequest, FootballResponse, FootballReactiveClientHeadersFactory> {
    /**
     * Argument constructor creates a new instance of BaseReactiveRestClient with given values.
     *
     * @param httpHeadersFactory               HTTP headers factory used to produce the custom HTTP headers required to consume the back end service
     * @param reactiveRestResponseErrorHandler used to handle errors from the reactive REST client
     */
    protected FootballReactiveClient(FootballReactiveClientHeadersFactory httpHeadersFactory, BaseReactiveRestResponseErrorHandler reactiveRestResponseErrorHandler) {
        super(httpHeadersFactory, reactiveRestResponseErrorHandler);
    }
}
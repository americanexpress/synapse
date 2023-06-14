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
package io.americanexpress.sample.client.reactive.football.config;

import io.americanexpress.sample.client.reactive.football.client.FootballReactiveClient;
import io.americanexpress.sample.client.reactive.football.handler.FootballReactiveResponseErrorHandler;
import io.americanexpress.synapse.client.rest.config.BaseReactiveRestClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.ProxyProvider;

/**
 * {@code FootballReactiveClientConfig} contains configurations for football client.
 *
 * @author eperjust
 */
@Configuration
@ComponentScan("io.americanexpress.sample.client.reactive.football")
@PropertySource("classpath:client-football.properties")
public class FootballReactiveClientConfig extends BaseReactiveRestClientConfig {

    /**
     * The client proxy host information.
     */
    @Value("${client.proxy.host}")
    private String clientProxyHost;

    /**
     * The client proxy port information.
     */
    @Value("${client.proxy.port}")
    private int clientProxyPort;

    /**
     * Reactive client for making call to weather api.
     */
    private final FootballReactiveClient footballReactiveClient;

    /**
     * Reactive response error handler for football client.
     */
    private final FootballReactiveResponseErrorHandler footballReactiveResponseErrorHandler;

    /**
     * Instantiates a new Weather reactive client config.
     *
     * @param footballReactiveClient               the football reactive client
     * @param footballReactiveResponseErrorHandler the football reactive response error handler
     */
    public FootballReactiveClientConfig(FootballReactiveClient footballReactiveClient, FootballReactiveResponseErrorHandler footballReactiveResponseErrorHandler) {
        this.footballReactiveClient = footballReactiveClient;
        this.footballReactiveResponseErrorHandler = footballReactiveResponseErrorHandler;
    }

    /**
     * This will initialize the default webclient
     *
     * @param destinationUrl of the provider
     */
    @Value("${client.url}")
    @Override
    protected void initialize(String destinationUrl) {
        initializeClient(destinationUrl, footballReactiveClient);
    }

    /**
     * Generate the default client connector to include the proxy configurations
     *
     * @return The client http connector.
     */
    @Override
    protected ReactorClientHttpConnector defaultClientConnector() {
        HttpClient httpClient = HttpClient.create()
                .proxy(proxy -> proxy.type(ProxyProvider.Proxy.HTTP)
                        .host(this.clientProxyHost)
                        .port(this.clientProxyPort));
        return new ReactorClientHttpConnector(httpClient);
    }
}
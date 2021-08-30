package io.americanexpress.synapse.client.rest.config;

import io.americanexpress.synapse.client.rest.client.BaseReactiveRestClient;
import io.americanexpress.synapse.client.rest.handler.BaseRestResponseErrorHandler;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * {@code BaseReactiveRestClientConfig} class is used to set the configurations for the base reactive REST client.
 * Use @import in child config classes to inherit these base configurations.
 *
 * @author Paolo Claudio
 */
@Configuration
public abstract class BaseReactiveRestClientConfig extends BaseClientConfig {
	
    /**
     * Initialize the client.
     *
     * @param destinationUrl             of the provider
     * @param reactiveRestClient         used to connect to the provider
     * @param restResponseErrorHandler   used to handler errors from the provider
     */
    @SuppressWarnings("rawtypes")
    protected void initializeClient(String destinationUrl, BaseReactiveRestClient reactiveRestClient, BaseRestResponseErrorHandler restResponseErrorHandler) {

        // Set the destination URL for the client
        reactiveRestClient.setUrl(destinationUrl);
        
        // Set the web client for the reactive REST client
        WebClient webClient = defaultWebClient(destinationUrl);
        reactiveRestClient.setWebClient(webClient);
    }
    
    /**
     * Generate the default web client.
     * @param destinationUrl of the provider
     * @return the default web client
     */
    public WebClient defaultWebClient(String destinationUrl) {    	
    	 WebClient webClient = WebClient.builder()
        	.codecs(clientCodecConfigurer -> clientCodecConfigurer.customCodecs()
        		.register(getMessageConverters()))
        	.baseUrl(destinationUrl)
        	.build();	 
    	 return webClient;
    }
}

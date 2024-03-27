package io.americanexpress.sample.client.graphql.book.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.americanexpress.synapse.client.graphql.model.GraphQLClientRequest;
import io.americanexpress.synapse.client.rest.factory.BaseClientHttpHeadersFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

/**
 * {@code BookClientHeadersFactory} used to create client headers for calling book graphql api.
 */
@Component
public class BookClientHeadersFactory extends BaseClientHttpHeadersFactory<GraphQLClientRequest> {

    /**
     * Initialize the client http headers factory.
     *
     * @param mapper the mapper
     */
    protected BookClientHeadersFactory(ObjectMapper mapper) {
        super(mapper);
    }

    /**
     * Create client headers.
     *
     * @param headers the headers from service
     * @param request the request
     * @param url the url
     * @return the client headers
     */
    @Override
    public HttpHeaders create(HttpHeaders headers, GraphQLClientRequest request, String url) {
        return new HttpHeaders();
    }
}

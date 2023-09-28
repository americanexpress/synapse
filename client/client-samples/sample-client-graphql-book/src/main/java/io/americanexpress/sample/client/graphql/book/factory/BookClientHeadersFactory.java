package io.americanexpress.sample.client.graphql.book.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.americanexpress.synapse.client.graphql.model.GraphQLClientRequest;
import io.americanexpress.synapse.client.rest.factory.BaseClientHttpHeadersFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class BookClientHeadersFactory extends BaseClientHttpHeadersFactory<GraphQLClientRequest> {

    /**
     * Initialize the client http headers factory.
     *
     * @param mapper
     */
    protected BookClientHeadersFactory(ObjectMapper mapper) {
        super(mapper);
    }

    @Override
    public HttpHeaders create(HttpHeaders clientHeaders, GraphQLClientRequest request, String url) {
        return new HttpHeaders();
    }
}

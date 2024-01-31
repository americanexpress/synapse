package io.americanexpress.sample.client.graphql.book.client;

import io.americanexpress.sample.client.graphql.book.config.BookReactiveGraphqlClientTestConfig;
import io.americanexpress.sample.client.graphql.book.model.BookResponse;
import io.americanexpress.synapse.client.graphql.model.GraphQLClientRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ContextConfiguration(classes = BookReactiveGraphqlClientTestConfig.class)
@ExtendWith(SpringExtension.class)
class BookReactiveGraphqlClientIT {

    @Autowired
    private BookReactiveGraphqlClient bookReactiveGraphqlClient;

    @Test
    void callMonoService_givenValidRequest_expectedValidResponse() {
        var request = new GraphQLClientRequest();
        request.setQuery("{getBookReactively(id: \"c2ab9a0c-e5d8-4271-a377-a23250ee3a9e\") {id title author} }");

        Mono<BookResponse> responseMono = bookReactiveGraphqlClient.callMonoService(new HttpHeaders(), request);
        StepVerifier.create(responseMono)
                .consumeNextWith(bookResponse -> {
                    Assertions.assertNotNull(bookResponse.getData());
                    Assertions.assertEquals("c2ab9a0c-e5d8-4271-a377-a23250ee3a9e", bookResponse.getData().getBook().getId());
                })
                .verifyComplete();
    }

    @Test
    void callMonoService_givenInValidRequest_expectedNoData() {
        var request = new GraphQLClientRequest();
        request.setQuery("{getBookReactively(id: \"invalidId\") {id title author} }");

        Mono<BookResponse> responseMono = bookReactiveGraphqlClient.callMonoService(new HttpHeaders(), request);
        StepVerifier.create(responseMono)
                .consumeNextWith(bookResponse -> {
                    Assertions.assertNull(bookResponse.getData().getBook());
                })
                .verifyComplete();
    }
}

package io.americanexpress.sample.client.graphql.book.client;

import io.americanexpress.sample.client.graphql.book.factory.BookClientHeadersFactory;
import io.americanexpress.sample.client.graphql.book.handler.BookReactiveResponseErrorHandler;
import io.americanexpress.sample.client.graphql.book.model.BookOperation;
import io.americanexpress.sample.client.graphql.book.model.BookResponse;
import io.americanexpress.synapse.client.graphql.client.BaseReactiveGraphQLClient;
import io.americanexpress.synapse.client.graphql.client.ReactiveGraphQLClient;

/**
 * {@code BookReactiveGraphqlClient} is used to call the Book Graphql API (from sample-service-graphql-book).
 */
@ReactiveGraphQLClient
public class BookReactiveGraphqlClient extends BaseReactiveGraphQLClient<BookOperation, BookResponse, BookClientHeadersFactory> {

    /**
     * Argument constructor creates a new instance of BaseReactiveGraphQLClient with given values.
     *
     * @param httpHeadersFactory               HTTP headers factory used to produce the custom HTTP headers required to consume the back end service
     * @param bookReactiveResponseErrorHandler used to handle errors from the reactive REST client
     */
    protected BookReactiveGraphqlClient(BookClientHeadersFactory httpHeadersFactory, BookReactiveResponseErrorHandler bookReactiveResponseErrorHandler) {
        super(httpHeadersFactory, bookReactiveResponseErrorHandler);
    }
}

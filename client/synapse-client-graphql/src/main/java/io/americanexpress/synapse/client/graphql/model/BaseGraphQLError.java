package io.americanexpress.synapse.client.graphql.model;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;
import java.util.Map;

/**
 * {@code BaseGraphQLError} contains details for graphql error.
 */
public class BaseGraphQLError implements GraphQLError {

    /**
     * Message.
     */
    private String message;

    /**
     * List of locations where the exception was thrown.
     */
    private List<SourceLocation> locations;

    /**
     * Path of the GraphQL request that caused the exception.
     */
    private List<Object> paths;

    /**
     * Error code and error message.
     */
    private Map<String, Object> extension;

    /**
     * Error type.
     */
    private ErrorClassification errorType;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return locations;
    }

    @Override
    public ErrorClassification getErrorType() {
        return errorType;
    }
}

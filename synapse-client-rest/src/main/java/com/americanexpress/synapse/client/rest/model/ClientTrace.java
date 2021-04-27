package com.americanexpress.synapse.client.rest.model;

import java.util.Objects;

/**
 * <code>ClientTrace</code> class is the model used for the tracing elements in the client headers.
 *
 * @author Paolo Claudio
 */
public class ClientTrace {

    /**
     * Correlation ID.
     */
    private String correlationId;


    /**
     * Default constructor creates a new instance of ClientTrace with default values.
     */
    public ClientTrace() {

        // Fields are set via set methods
    }

    /**
     * Get the correlationId.
     *
     * @return the correlationId
     */
    public String getCorrelationId() {
        return correlationId;
    }

    /**
     * Set the correlationId.
     *
     * @param correlationId the correlationId to set
     */
    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }


    @Override
    public String toString() {
        return "ClientTrace{" +
                "correlationId='" + correlationId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientTrace that = (ClientTrace) o;

        return Objects.equals(correlationId, that.correlationId);
    }

    @Override
    public int hashCode() {
        return correlationId != null ? correlationId.hashCode() : 0;
    }
}

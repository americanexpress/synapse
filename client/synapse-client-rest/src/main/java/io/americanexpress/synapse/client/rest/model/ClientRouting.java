package io.americanexpress.synapse.client.rest.model;

import java.util.Objects;

/**
 * <code>ClientRouting</code> class is the model for the routing elements used in the client headers.
 *
 * @author Paolo Claudio
 */
public class ClientRouting {


    /**
     * Client Identifier.
     */
    private String clientIdentifier;


    /**
     * Default constructor creates a new instance of ClientRouting with default values.
     */
    public ClientRouting() {

        // Fields are set via set methods
    }

    /**
     * Gets the client identifier.
     *
     * @return the client identifier.
     */
    public String getClientIdentifier() {
        return clientIdentifier;
    }

    /**
     * Sets the client identifier.
     *
     * @param clientIdentifier the client identifier to set.
     */
    public void setClientIdentifier(String clientIdentifier) {
        this.clientIdentifier = clientIdentifier;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientRouting that = (ClientRouting) o;

        return Objects.equals(clientIdentifier, that.clientIdentifier);
    }

    @Override
    public int hashCode() {
        return clientIdentifier != null ? clientIdentifier.hashCode() : 0;
    }

    /**
     * Return the string representation of this object.
     *
     * @return the string representation of this object
     */
    @Override
    public String toString() {
        return "ClientRouting{" +
                "clientIdentifier='" + clientIdentifier + '\'' +
                '}';
    }
}

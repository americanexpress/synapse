package io.americanexpress.synapse.service.imperative.model;

/**
 * {@code BaseDeleteServiceRequest} class specifies the prototypes for all delete service requests.
 *
 * @author Luis Diaz
 */
public class BaseDeleteServiceRequest implements BaseServiceRequest {

    /**
     * Unique identifier for the resource.
     */
    private String id;

    /**
     * Get the id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Set the id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }
}

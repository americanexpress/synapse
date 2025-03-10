package io.americanexpress.synapse.client.elasticsearch.model;

/**
 * {@code BaseElasticSearchData} is the base class for all ElasticSearch data.
 *
 * @author sshre31
 */
public abstract class BaseElasticSearchData {

    /**
     * The id of the entity.
     */
    private String id;

    /**
     * Gets the entity's id.
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the entity's id.
     * @param id the id of this entity
     */
    public void setId(String id) {
        this.id = id;
    }
}

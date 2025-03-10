package io.americanexpress.sample.client.elasticsearch.product.model;

import io.americanexpress.synapse.client.elasticsearch.model.BaseElasticSearchData;

/**
 * {@code Product} represents a product.
 *
 * @author sshre31
 */
public class Product extends BaseElasticSearchData {

    /**
     * Name of the product.
     */
    private String name;

    /**
     * Description of the product.
     */
    private String description;

    /**
     * Get the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the description.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}

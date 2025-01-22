package io.americanexpress.sample.client.elasticsearch.product.model;

import java.util.List;
import java.util.UUID;

/**
 * {@code Product} represents a product.
 *
 * @author sshre31
 */
public class Product {

    /**
     * ID of this product.
     */
    private UUID id;

    /**
     * Name of the product.
     */
    private String name;

    /**
     * Description of the product.
     */
    private String description;

    /**
     * Test of the product.
     */
    private List<String> test;

    /**
     * Default constructor creates a new instance of Product with default values.
     */
    public Product(UUID id, String name, String description, List<String> test) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * Get the id.
     *
     * @return the id
     */
    public UUID getId() {
        return id;
    }

    /**
     * Set the id.
     *
     * @param id the id to set
     */
    public void setId(UUID id) {
        this.id = id;
    }

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

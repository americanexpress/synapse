package io.americanexpress.sample.client.elasticsearch.product.client;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import io.americanexpress.sample.client.elasticsearch.product.model.Product;
import io.americanexpress.synapse.client.elasticsearch.client.BaseUpdateElasticSearchDocumentClient;

/**
 * {@code UpdateProductElasticSearchDocumentClient}
 *
 * @author sshre31
 */
public class UpdateProductElasticSearchDocumentClient extends BaseUpdateElasticSearchDocumentClient<Product> {

    /**
     * Index name.
     */
    private static final String INDEX_NAME = "product";

    /**
     * Create an instance of BaseUpdateElasticSearchDocumentClient with the specified parameters.
     *
     * @param elasticsearchClient elasticsearchClient
     * @param clazz clazz
     */
    public UpdateProductElasticSearchDocumentClient(ElasticsearchClient elasticsearchClient, Class<Product> clazz) {
        super(elasticsearchClient, INDEX_NAME, clazz);
    }
}

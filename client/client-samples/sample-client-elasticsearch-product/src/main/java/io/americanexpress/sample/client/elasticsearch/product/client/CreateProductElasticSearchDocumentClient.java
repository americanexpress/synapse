package io.americanexpress.sample.client.elasticsearch.product.client;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import io.americanexpress.sample.client.elasticsearch.product.model.Product;
import io.americanexpress.synapse.client.elasticsearch.client.BaseCreateElasticSearchDocumentClient;
import org.springframework.stereotype.Component;

/**
 * {@code CreateProductElasticSearchDocumentClient} creates a document in ElasticSearch.
 *
 * @author sshre31
 */
@Component
public class CreateProductElasticSearchDocumentClient extends BaseCreateElasticSearchDocumentClient<Product> {

    /**
     * Index name.
     */
    private static final String INDEX_NAME = "product";

    /**
     * Create an instance of CreateProductElasticSearchDocumentClient with the specified parameters.
     *
     * @param elasticsearchClient elasticsearchClient
     */
    public CreateProductElasticSearchDocumentClient(ElasticsearchClient elasticsearchClient) {
        super(elasticsearchClient, INDEX_NAME, Product.class);
    }
}

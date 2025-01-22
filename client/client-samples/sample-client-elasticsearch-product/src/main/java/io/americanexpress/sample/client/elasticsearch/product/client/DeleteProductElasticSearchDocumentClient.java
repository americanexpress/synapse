package io.americanexpress.sample.client.elasticsearch.product.client;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import io.americanexpress.sample.client.elasticsearch.product.model.Product;
import io.americanexpress.synapse.client.elasticsearch.client.BaseDeleteElasticSearchDocumentClient;
import org.springframework.stereotype.Component;

/**
 * {@code DeleteProductElasticSearchDocumentClient} deletes a document from ElasticSearch.
 *
 * @author sshre31
 */
@Component
public class DeleteProductElasticSearchDocumentClient extends BaseDeleteElasticSearchDocumentClient<Product> {

    /**
     * Index name.
     */
    private static final String INDEX_NAME = "product";

    /**
     * Create an instance of BaseDeleteElasticSearchDocumentClient with the specified parameters.
     *
     * @param elasticsearchClient elasticsearchClient
     * @param clazz clazz
     */
    public DeleteProductElasticSearchDocumentClient(ElasticsearchClient elasticsearchClient, Class<Product> clazz) {
        super(elasticsearchClient, INDEX_NAME, clazz);
    }
}

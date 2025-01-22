package io.americanexpress.sample.client.elasticsearch.product.client;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import io.americanexpress.sample.client.elasticsearch.product.model.Product;
import io.americanexpress.synapse.client.elasticsearch.client.BaseReadElasticSearchDocumentClient;
import org.springframework.stereotype.Component;

/**
 * {@code ReadProductElasticSearchDocumentClient} reads a document from ElasticSearch.
 *
 * @author sshre31
 */
@Component
public class ReadProductElasticSearchDocumentClient extends BaseReadElasticSearchDocumentClient<Product> {

    /**
     * Index name.
     */
    private static final String INDEX_NAME = "product";

    /**
     * Create an instance of BaseReadElasticSearchDocumentClient with the specified parameters.
     *
     * @param elasticsearchClient elasticsearchClient
     * @param clazz clazz
     */
    public ReadProductElasticSearchDocumentClient(ElasticsearchClient elasticsearchClient, Class<Product> clazz) {
        super(elasticsearchClient, INDEX_NAME, clazz);
    }
}

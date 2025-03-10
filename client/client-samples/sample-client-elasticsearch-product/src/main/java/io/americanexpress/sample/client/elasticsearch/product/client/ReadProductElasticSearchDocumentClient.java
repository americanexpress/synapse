package io.americanexpress.sample.client.elasticsearch.product.client;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import io.americanexpress.sample.client.elasticsearch.product.model.Product;
import io.americanexpress.synapse.client.elasticsearch.client.BaseReadElasticSearchDocumentSearchClient;
import org.springframework.stereotype.Component;
import static io.americanexpress.sample.client.elasticsearch.product.config.ProductElasticSearchConfig.INDEX_NAME;

/**
 * {@code ReadProductElasticSearchDocumentClient} reads a document from ElasticSearch.
 *
 * @author sshre31
 */
@Component
public class ReadProductElasticSearchDocumentClient extends BaseReadElasticSearchDocumentSearchClient<Product> {

    /**
     * Create an instance of BaseReadElasticSearchDocumentSearchClient with the specified parameters.
     *
     * @param elasticsearchClient elasticsearchClient
     */
    public ReadProductElasticSearchDocumentClient(ElasticsearchClient elasticsearchClient) {
        super(elasticsearchClient, INDEX_NAME);
    }
}

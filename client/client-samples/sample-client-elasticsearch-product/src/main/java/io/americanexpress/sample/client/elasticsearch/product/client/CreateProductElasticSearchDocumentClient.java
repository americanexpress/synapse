package io.americanexpress.sample.client.elasticsearch.product.client;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import io.americanexpress.sample.client.elasticsearch.product.model.Product;
import io.americanexpress.synapse.client.elasticsearch.client.BaseCreateElasticSearchDocumentSearchClient;
import org.springframework.stereotype.Component;
import static io.americanexpress.sample.client.elasticsearch.product.config.ProductElasticSearchConfig.INDEX_NAME;

/**
 * {@code CreateProductElasticSearchDocumentClient} creates a document in ElasticSearch.
 *
 * @author sshre31
 */
@Component
public class CreateProductElasticSearchDocumentClient extends BaseCreateElasticSearchDocumentSearchClient<Product> {

    /**
     * Create an instance of CreateProductElasticSearchDocumentClient with the specified parameters.
     *
     * @param elasticsearchClient elasticsearchClient
     */
    public CreateProductElasticSearchDocumentClient(ElasticsearchClient elasticsearchClient) {
        super(elasticsearchClient, INDEX_NAME);
    }
}

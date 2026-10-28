package io.americanexpress.sample.client.elasticsearch.product.client;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import io.americanexpress.sample.client.elasticsearch.product.model.Product;
import io.americanexpress.synapse.client.elasticsearch.client.BaseDeleteElasticSearchDocumentSearchClient;
import org.springframework.stereotype.Component;
import static io.americanexpress.sample.client.elasticsearch.product.config.ProductElasticSearchConfig.INDEX_NAME;

/**
 * {@code DeleteProductElasticSearchDocumentClient} deletes a document from ElasticSearch.
 *
 * @author sshre31
 */
@Component
public class DeleteProductElasticSearchDocumentClient extends BaseDeleteElasticSearchDocumentSearchClient<Product> {

    /**
     * Create an instance of BaseDeleteElasticSearchDocumentSearchClient with the specified parameters.
     *
     * @param elasticsearchClient elasticsearchClient
     */
    public DeleteProductElasticSearchDocumentClient(ElasticsearchClient elasticsearchClient) {
        super(elasticsearchClient, INDEX_NAME);
    }
}

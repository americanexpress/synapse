package io.americanexpress.sample.client.elasticsearch.product.client;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import io.americanexpress.sample.client.elasticsearch.product.model.Product;
import io.americanexpress.synapse.client.elasticsearch.client.BaseUpdateElasticSearchDocumentSearchClient;
import org.springframework.stereotype.Component;
import static io.americanexpress.sample.client.elasticsearch.product.config.ProductElasticSearchConfig.INDEX_NAME;

/**
 * {@code UpdateProductElasticSearchDocumentClient}
 *
 * @author sshre31
 */
@Component
public class UpdateProductElasticSearchDocumentClient extends BaseUpdateElasticSearchDocumentSearchClient<Product> {

    /**
     * Create an instance of BaseUpdateElasticSearchDocumentSearchClient with the specified parameters.
     *
     * @param elasticsearchClient elasticsearchClient
     */
    public UpdateProductElasticSearchDocumentClient(ElasticsearchClient elasticsearchClient) {
        super(elasticsearchClient, INDEX_NAME);
    }
}

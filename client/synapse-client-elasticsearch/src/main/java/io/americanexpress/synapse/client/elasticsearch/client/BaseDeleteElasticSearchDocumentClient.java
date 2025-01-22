package io.americanexpress.synapse.client.elasticsearch.client;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import java.io.IOException;

/**
 * {@code BaseDeleteElasticSearchDocumentClient} deletes a document in ElasticSearch.
 *
 * @author sshre31
 */
public abstract class BaseDeleteElasticSearchDocumentClient<T> extends BaseElasticClient<T> {

    /**
     * Create an instance of BaseDeleteElasticSearchDocumentClient with the specified parameters.
     *
     * @param elasticsearchClient elasticsearchClient
     * @param indexName indexName
     * @param clazz clazz
     */
    public BaseDeleteElasticSearchDocumentClient(ElasticsearchClient elasticsearchClient, String indexName,
                                                 Class<T> clazz) {
        super(elasticsearchClient, indexName, clazz);
    }

    /**
     * Delete the document in ElasticSearch.
     *
     * @param id the identifier
     * @throws IOException if an error occurs while deleting the document
     */
    public void deleteById(String id) throws IOException {
        this.client.delete(d -> d
                .index(this.indexName)
                .id(id)
        );
    }
}

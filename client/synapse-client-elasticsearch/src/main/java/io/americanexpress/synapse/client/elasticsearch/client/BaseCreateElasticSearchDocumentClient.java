package io.americanexpress.synapse.client.elasticsearch.client;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import java.io.IOException;

/**
 * {@code BaseCreateElasticSearchDocumentClient} creates a document in ElasticSearch.
 *
 * @author sshre31
 */
public abstract class BaseCreateElasticSearchDocumentClient<T> extends BaseElasticClient<T> {

    /**
     * Create an instance of BaseCreateElasticSearchDocumentClient with the specified parameters.
     *
     * @param elasticsearchClient elasticsearchClient
     * @param indexName indexName
     * @param clazz clazz
     */
    public BaseCreateElasticSearchDocumentClient(ElasticsearchClient elasticsearchClient,
                                                 String indexName, Class clazz) {
        super(elasticsearchClient, indexName, clazz);
    }

    /**
     * Save the document in ElasticSearch.
     *
     * @param id the identifier
     * @param document the document to save
     * @throws IOException if an error occurs while saving the document
     */
    public void save(String id, T document) throws IOException {
        this.client.index(i -> i
                .index(this.indexName)
                .id(id)
                .document(document)
        );
    }
}

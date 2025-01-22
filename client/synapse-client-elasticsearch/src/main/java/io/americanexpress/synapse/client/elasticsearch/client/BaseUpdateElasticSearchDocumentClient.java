package io.americanexpress.synapse.client.elasticsearch.client;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import java.io.IOException;

/**
 * {@code BaseUpdateElasticSearchDocumentClient}
 *
 * @author sshre31
 */
public abstract class BaseUpdateElasticSearchDocumentClient<T> extends BaseElasticClient<T> {

    /**
     * Create an instance of BaseUpdateElasticSearchDocumentClient with the specified parameters.
     *
     * @param elasticsearchClient elasticsearchClient
     * @param indexName indexName
     * @param clazz clazz
     */
    public BaseUpdateElasticSearchDocumentClient(ElasticsearchClient elasticsearchClient, String indexName,
                                                 Class<T> clazz) {
        super(elasticsearchClient, indexName, clazz);
    }

    /**
     * Update the document in ElasticSearch.
     *
     * @param id the identifier
     * @param document the document to update
     * @throws IOException if an error occurs while updating the document
     */
    public void update(String id, T document) throws IOException {
        client.update(u -> u
                .index(indexName)
                .id(id)
                .doc(document), clazz
        );
    }
}

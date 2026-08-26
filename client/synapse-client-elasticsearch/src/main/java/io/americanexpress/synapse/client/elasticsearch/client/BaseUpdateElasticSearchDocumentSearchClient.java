package io.americanexpress.synapse.client.elasticsearch.client;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import io.americanexpress.synapse.client.elasticsearch.model.BaseElasticSearchData;
import java.io.IOException;

/**
 * {@code BaseUpdateElasticSearchDocumentSearchClient}
 *
 * @author sshre31
 */
public abstract class BaseUpdateElasticSearchDocumentSearchClient<T extends BaseElasticSearchData> extends BaseElasticSearchClient<T> {

    /**
     * Create an instance of BaseUpdateElasticSearchDocumentSearchClient with the specified parameters.
     *
     * @param elasticsearchClient elasticsearchClient
     * @param indexName indexName
     */
    protected BaseUpdateElasticSearchDocumentSearchClient(ElasticsearchClient elasticsearchClient, String indexName) {
        super(elasticsearchClient, indexName);
    }

    /**
     * Update the document in ElasticSearch.
     *
     * @param document the document to update
     * @throws IOException if an error occurs while updating the document
     */
    public void update(T document) throws IOException {
        elasticsearchClient.update(u -> u
                .index(indexName)
                .id(document.getId())
                .doc(document),
                this.documentType
        );
    }

    /**
     * Upsert the document in ElasticSearch.
     *
     * @param document the document to update
     * @throws IOException if an error occurs while upserting the document
     */
    public void upsert(T document) throws IOException {
        elasticsearchClient.update(u -> u
                .index(indexName)
                .id(document.getId())
                .upsert(document),
                this.documentType
        );
    }
}

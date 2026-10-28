package io.americanexpress.synapse.client.elasticsearch.client;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import io.americanexpress.synapse.client.elasticsearch.model.BaseElasticSearchData;
import java.io.IOException;

/**
 * {@code BaseDeleteElasticSearchDocumentSearchClient} deletes a document in ElasticSearch.
 *
 * @author sshre31
 */
public abstract class BaseDeleteElasticSearchDocumentSearchClient<T extends BaseElasticSearchData> extends BaseElasticSearchClient<T> {

    /**
     * Create an instance of BaseDeleteElasticSearchDocumentSearchClient with the specified parameters.
     *
     * @param elasticsearchClient elasticsearchClient
     * @param indexName indexName
     */
    protected BaseDeleteElasticSearchDocumentSearchClient(ElasticsearchClient elasticsearchClient, String indexName) {
        super(elasticsearchClient, indexName);
    }

    /**
     * Delete the document in ElasticSearch.
     *
     * @param id the identifier
     * @throws IOException if an error occurs while deleting the document
     */
    public void deleteById(String id) throws IOException {
        this.elasticsearchClient.delete(d -> d
                .index(this.indexName)
                .id(id)
        );
    }

    /**
     * Delete the document in ElasticSearch.
     *
     * @param document the document to delete
     * @throws IOException if an error occurs while deleting the document
     */
    public void delete(T document) throws IOException {
        this.elasticsearchClient.delete(d -> d
                .index(this.indexName)
                .id(document.getId())
        );
    }
}

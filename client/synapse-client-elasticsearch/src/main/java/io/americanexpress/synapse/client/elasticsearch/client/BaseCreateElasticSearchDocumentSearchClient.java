package io.americanexpress.synapse.client.elasticsearch.client;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import io.americanexpress.synapse.client.elasticsearch.model.BaseElasticSearchData;
import io.americanexpress.synapse.framework.exception.ApplicationClientException;
import io.americanexpress.synapse.framework.exception.model.ErrorCode;
import io.micrometer.common.util.StringUtils;
import java.io.IOException;

/**
 * {@code BaseCreateElasticSearchDocumentSearchClient} creates a document in ElasticSearch.
 *
 * @author sshre31
 */
public abstract class BaseCreateElasticSearchDocumentSearchClient<T extends BaseElasticSearchData> extends BaseElasticSearchClient<T> {

    /**
     * Create an instance of BaseCreateElasticSearchDocumentSearchClient with the specified parameters.
     *
     * @param elasticsearchClient elasticsearchClient
     * @param indexName indexName
     */
    public BaseCreateElasticSearchDocumentSearchClient(ElasticsearchClient elasticsearchClient,
                                                       String indexName) {
        super(elasticsearchClient, indexName);
    }

    /**
     * Save the document in ElasticSearch.
     *
     * @param document the document to save
     * @throws IOException if an error occurs while saving the document
     */
    public void save(T document) throws IOException {
        if (StringUtils.isBlank(document.getId())) {
            throw new ApplicationClientException("Document ID is required.", ErrorCode.GENERIC_4XX_ERROR);
        }

        this.elasticsearchClient.index(i -> i
                .index(this.indexName)
                .id(document.getId())
                .document(document)
        );
    }
}

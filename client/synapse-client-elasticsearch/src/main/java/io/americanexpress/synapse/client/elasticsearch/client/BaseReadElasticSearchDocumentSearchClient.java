package io.americanexpress.synapse.client.elasticsearch.client;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetResponse;
import io.americanexpress.synapse.client.elasticsearch.model.BaseElasticSearchData;
import java.io.IOException;
import org.springframework.data.domain.Page;

/**
 * {@code BaseReadElasticSearchDocumentSearchClient} reads a document in ElasticSearch.
 *
 * @author sshre31
 */
public abstract class BaseReadElasticSearchDocumentSearchClient<T extends BaseElasticSearchData> extends BaseElasticSearchClient<T> {

    /**
     * Create an instance of BaseReadElasticSearchDocumentSearchClient with the specified parameters.
     *
     * @param elasticsearchClient elasticsearchClient
     * @param indexName indexName
     */
    protected BaseReadElasticSearchDocumentSearchClient(ElasticsearchClient elasticsearchClient, String indexName) {
        super(elasticsearchClient, indexName);
    }

    /**
     * Find the document by the identifier.
     *
     * @param id the identifier
     * @return the document
     * @throws IOException if an error occurs while finding the document
     */
    public T findById(String id) throws IOException {
        var response = elasticsearchClient.get(g -> g
                .index(indexName)
                .id(id), this.documentType
        );
        return response.found() ? response.source() : null;
    }

    /**
     * Find all the documents.
     *
     * @return the list of documents
     * @throws IOException if an error occurs while finding the documents
     */
    public Page<T> findAll(int page, int size) throws IOException {
        var response = elasticsearchClient.search(s -> s
                        .index(indexName)
                        .query(q -> q.matchAll(m -> m))
                        .from(page)
                        .size(size),
                this.documentType
        );

        return renderPageResults(response, page, size);
    }

    /**
     * Find the document by the key.
     *
     * @param key the key
     * @param value the value
     * @throws IOException if an error occurs while finding the documents
     */
    public Page<T> searchByKey(String key, String value, int page, int size) throws IOException {
        var response = elasticsearchClient.search(s -> s
                .index(indexName)
                .query(q -> q.match(t -> t
                        .field(key)
                        .query(value)
                ))
                .from(page)
                .size(size), this.documentType
        );

        return renderPageResults(response, page, size);
    }

    /**
     * Check if the document exists.
     *
     * @param id the identifier
     * @return true if the document exists, false otherwise
     * @throws IOException if an error occurs while checking if the document exists
     */
    public boolean doesExists(String id) throws IOException {
        GetResponse<T> response = elasticsearchClient.get(g -> g
                .index(indexName)
                .id(id), this.documentType
        );
        return response.found();
    }
}

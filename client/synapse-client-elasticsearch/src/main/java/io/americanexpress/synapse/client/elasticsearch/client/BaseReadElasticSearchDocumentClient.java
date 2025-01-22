package io.americanexpress.synapse.client.elasticsearch.client;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code BaseReadElasticSearchDocumentClient} reads a document in ElasticSearch.
 *
 * @author sshre31
 */
public abstract class BaseReadElasticSearchDocumentClient<T> extends BaseElasticClient<T> {

    /**
     * Create an instance of BaseReadElasticSearchDocumentClient with the specified parameters.
     *
     * @param elasticsearchClient elasticsearchClient
     * @param indexName indexName
     * @param clazz clazz
     */
    public BaseReadElasticSearchDocumentClient(ElasticsearchClient elasticsearchClient, String indexName,
                                               Class<T> clazz) {
        super(elasticsearchClient, indexName, clazz);
    }

    /**
     * Find the document by the identifier.
     *
     * @param id the identifier
     * @return the document
     * @throws IOException if an error occurs while finding the document
     */
    public T findById(String id) throws IOException {
        GetResponse<T> response = client.get(g -> g
                .index(indexName)
                .id(id), clazz
        );
        return response.found() ? response.source() : null;
    }

    /**
     * Find all the documents.
     *
     * @return the list of documents
     * @throws IOException if an error occurs while finding the documents
     */
    public List<T> findAll() throws IOException {
        SearchResponse<T> response = client.search(s -> s
                .index(indexName)
                .query(q -> q.matchAll(m -> m)), clazz
        );
        List<T> results = new ArrayList<>();
        for (Hit<T> hit : response.hits().hits()) {
            results.add(hit.source());
        }
        return results;
    }
}

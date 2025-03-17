package io.americanexpress.synapse.client.elasticsearch.client;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import io.americanexpress.synapse.client.elasticsearch.model.BaseElasticSearchData;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code BaseElasticSearchClient} is the base class for all ElasticSearch clients.
 *
 * @author sshre31
 */
public abstract class BaseElasticSearchClient<T extends BaseElasticSearchData> {

    /**
     * Elasticsearch client.
     */
    protected final ElasticsearchClient elasticsearchClient;

    /**
     * Index name.
     */
    protected final String indexName;

    /**
     * Class of the document.
     */
    protected Class<T> documentType;

    /**
     * Create an instance of BaseElasticSearchClient with the specified parameters.
     *
     * @param elasticsearchClient elasticsearchClient
     * @param indexName indexName
     */
    protected BaseElasticSearchClient(ElasticsearchClient elasticsearchClient, String indexName) {
        this.elasticsearchClient = elasticsearchClient;
        this.indexName = indexName;
        initialize();
    }

    /**
     * Initialize the document type.
     */
    @SuppressWarnings("unchecked")
    private void initialize() {
        var parameterizedType = ((ParameterizedType) getClass().getGenericSuperclass());
        this.documentType = (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

    /**
     * Render the results from the search response.
     *
     * @param response the search response
     * @return the list of results
     */
    List<T> renderResults(SearchResponse<T> response) {
        List<T> results = new ArrayList<>();
        for (Hit<T> hit : response.hits().hits()) {
            results.add(hit.source());
        }
        return results;
    }
}

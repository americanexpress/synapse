package io.americanexpress.synapse.client.elasticsearch.client;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import io.americanexpress.synapse.client.elasticsearch.model.BaseElasticSearchData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return Optional.ofNullable(response)
                .map(SearchResponse::hits)
                .map(HitsMetadata::hits)
                .orElseGet(ArrayList::new)
                .stream()
                .map(Hit::source)
                .toList();
    }

    /**
     * Render the page results from the search response.
     *
     * @param response the search response
     * @param page the page number
     * @param size the page size
     * @return the page of results
     */
    Page<T> renderPageResults(SearchResponse<T> response, int page, int size) {
        var hitsMetadata = Optional.ofNullable(response)
                .map(SearchResponse::hits)
                .orElse(null);

        long totalHits = Optional.ofNullable(hitsMetadata)
                .map(HitsMetadata::total)
                .map(TotalHits::value)
                .orElse(0L);

        List<T> results = Optional.ofNullable(hitsMetadata)
                .map(HitsMetadata::hits)
                .orElseGet(ArrayList::new)
                .stream()
                .map(Hit::source)
                .toList();

        var pageable = PageRequest.of(page, size);
        return new PageImpl<>(results, pageable, totalHits);
    }
}

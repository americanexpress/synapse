package io.americanexpress.synapse.client.elasticsearch.client;

import co.elastic.clients.elasticsearch.ElasticsearchClient;

/**
 * {@code BaseElasticClient} is the base class for all ElasticSearch clients.
 *
 * @author sshre31
 */
public abstract class BaseElasticClient<T> {

    /**
     * Elasticsearch client.
     */
    protected final ElasticsearchClient client;

    /**
     * Index name.
     */
    protected final String indexName;

    /**
     * Class of the document.
     */
    protected final Class<T> clazz;

    /**
     * Create an instance of BaseElasticClient with the specified parameters.
     *
     * @param elasticsearchClient elasticsearchClient
     * @param indexName indexName
     * @param clazz clazz
     */
    public BaseElasticClient(ElasticsearchClient elasticsearchClient, String indexName, Class<T> clazz) {
        this.client = elasticsearchClient;
        this.indexName = indexName;
        this.clazz = clazz;
    }
}

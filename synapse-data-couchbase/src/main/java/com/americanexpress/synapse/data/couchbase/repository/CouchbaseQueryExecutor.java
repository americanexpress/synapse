/*
 * Copyright 2020 American Express Travel Related Services Company, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.americanexpress.synapse.data.couchbase.repository;

import com.americanexpress.synapse.data.couchbase.converters.CouchbaseDataConverter;
import com.americanexpress.synapse.data.couchbase.converters.CouchbaseDataConverterWithReflection;
import com.americanexpress.synapse.data.couchbase.converters.DataConverter;
import com.americanexpress.synapse.data.couchbase.converters.SyncGatewayDataConverter;
import com.americanexpress.synapse.data.couchbase.converters.SyncGatewayDataConverterWithReflection;
import com.americanexpress.synapse.data.couchbase.exceptions.NonUniqueResultException;
import com.americanexpress.synapse.data.couchbase.parameter.ClauseType;
import com.americanexpress.synapse.data.couchbase.parameter.Parameter;
import com.americanexpress.synapse.data.couchbase.parameter.Parameters;
import com.americanexpress.synapse.framework.exception.ApplicationServerException;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.cluster.ClusterInfo;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.Statement;
import com.couchbase.client.java.query.dsl.Expression;
import com.couchbase.client.java.query.dsl.Sort;
import com.couchbase.client.java.query.dsl.path.AsPath;
import com.couchbase.client.java.query.dsl.path.FromPath;
import com.couchbase.client.java.query.dsl.path.GroupByPath;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.couchbase.client.java.query.Select.select;
import static com.couchbase.client.java.query.dsl.Expression.i;

/**
 * The type Couchbase query executor.
 *
 * @param <T> the type parameter
 */
public class CouchbaseQueryExecutor<T> {

    private final XLogger logger = XLoggerFactory.getXLogger(this.getClass());

    private final ClusterInfo couchbaseClusterInfo;

    private final Bucket couchbaseClientBucket;

    private final ObjectMapper objectMapper;

    private DataConverter<T> converter;

    @Value("${couchbase-query-executor.with-sync-gateway:false}")
    private Boolean withSyncGateway;

    @Value("${couchbase-query-executor.use-default-id-fields:true}")
    private Boolean useDefaultIdFields;

    private static final String IGNORE_CASE_ORDER = "_ignorecase";

    private static final String AS_ID = ").id AS id ";

    /**
     * Instantiates a new Couchbase query executor.
     *
     * @param objectMapper the object mapper
     */
    public CouchbaseQueryExecutor(final ClusterInfo couchbaseClusterInfo, final Bucket couchbaseClientBucket, final ObjectMapper objectMapper) {
        this.couchbaseClusterInfo = couchbaseClusterInfo;
        this.couchbaseClientBucket = couchbaseClientBucket;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    private void init() {
        if (Boolean.TRUE.equals(withSyncGateway)) {
            if (Boolean.TRUE.equals(useDefaultIdFields)) {
                converter = new SyncGatewayDataConverter<>(objectMapper);
            } else {
                converter = new SyncGatewayDataConverterWithReflection<>(objectMapper);
            }
        } else {
            if (Boolean.TRUE.equals(useDefaultIdFields)) {
                converter = new CouchbaseDataConverter<>(objectMapper);
            } else {
                converter = new CouchbaseDataConverterWithReflection<>(objectMapper);
            }
        }
    }

    /**
     * Gets converter.
     *
     * @return the converter
     */
    public DataConverter<T> getConverter() {
        return converter;
    }

    /**
     * Sets converter.
     *
     * @param converter the converter
     */
    public void setConverter(DataConverter<T> converter) {
        this.converter = converter;
    }

    private CouchbaseTemplate createTemplate() {
        try {
            return new CouchbaseTemplate(this.couchbaseClusterInfo, this.couchbaseClientBucket);
        } catch (Exception exception) {
            throw new ApplicationServerException(exception);
        }
    }

    /**
     * Find one optional.
     *
     * @param params the params
     * @param clazz  the clazz
     * @return the optional
     */
    public Optional<T> findOne(Parameters params, Class<T> clazz) {
        List<T> documents = find(params, clazz);
        return asOptional(documents, params);
    }

    /**
     * Find page.
     *
     * @param params   the params
     * @param pageable the pageable
     * @param clazz    the clazz
     * @return the page
     */
    public Page<T> find(Parameters params, Pageable pageable, Class<T> clazz) {
        CouchbaseTemplate template = createTemplate();

        Statement query = createQueryStatement(params, pageable);

        N1qlQuery queryWithParameter = N1qlQuery.parameterized(query, params.toJsonArray());
        logger.debug("Query with parameters = {}", queryWithParameter);

        List<T> data = convertToDataList(template.findByN1QLProjection(queryWithParameter, LinkedHashMap.class), clazz);
        Integer count = 0;
        if (!CollectionUtils.isEmpty(data)) {
            count = count(params);
        }

        return new PageImpl<>(data, pageable, count);
    }

    /**
     * Find list.
     *
     * @param params the params
     * @param clazz  the clazz
     * @return the list
     */
    public List<T> find(Parameters params, Class<T> clazz) {
        CouchbaseTemplate template = createTemplate();

        Statement query = createQueryStatement(params);

        logger.debug("Query = {}", query);

        logger.debug("Params = {}", params);

        N1qlQuery queryWithParameter = N1qlQuery.parameterized(query, params.toJsonArray());

        logger.debug("Query with parameters = {}", queryWithParameter);

        return convertToDataList(template.findByN1QLProjection(queryWithParameter, LinkedHashMap.class), clazz);
    }

    private List<T> convertToDataList(List<LinkedHashMap> queriedList, Class<T> clazz) {
        logger.debug("Results {}", queriedList);
        return queriedList.stream()
                .map(hashMap -> converter.apply(hashMap, clazz))
                .collect(Collectors.toList());
    }

    /**
     * Count integer.
     *
     * @param params the params
     * @return the integer
     */
    public Integer count(Parameters params) {
        CouchbaseTemplate template = createTemplate();

        Statement query = createCountStatement(params);
        N1qlQuery queryWithParams = N1qlQuery.parameterized(query, params.toJsonArray());
        List list = template.findByN1QLProjection(queryWithParams, Object.class);
        LinkedHashMap countMap = null;
        if (!CollectionUtils.isEmpty(list)) {
            countMap = (LinkedHashMap) list.get(0);
        }

        Integer count = 0;
        if (!CollectionUtils.isEmpty(countMap)) {
            count = (Integer) countMap.get("count");
        }


        return count;
    }


    /**
     * Sum integer.
     *
     * @param params the params
     * @param field  the field
     * @return the integer
     */
    public Integer sum(Parameters params, String field) {
        CouchbaseTemplate template = createTemplate();

        Statement query = createSumStatement(params, field);
        N1qlQuery queryWithParams = N1qlQuery.parameterized(query, params.toJsonArray());
        final LinkedHashMap sumMap = ((LinkedHashMap) template.findByN1QLProjection(queryWithParams, Object.class).get(0));

        return ((Integer) sumMap.get("sum"));
    }

    private Statement createCountStatement(Parameters params) {
        Expression bucketName = i(this.couchbaseClientBucket.name());
        return count(bucketName)
                .from(bucketName)
                .where(composeWhere(bucketName, params));
    }

    private Statement createSumStatement(Parameters params, String field) {
        Expression bucketName = i(this.couchbaseClientBucket.name());
        return sum(bucketName, field).from(bucketName).where(composeWhere(bucketName, params))
                .groupBy(meta(bucketName));

    }

    private Statement createQueryStatement(Parameters params, Pageable pageable) {
        Expression bucketName = i(this.couchbaseClientBucket.name());
        AsPath asPath = selectWithMeta(bucketName).from(bucketName);
        GroupByPath groupByPath = asPath;
        //This is to support also when no parameters are passed. Code is ugly I know, it needs to be refactored when we have time.
        if (params != null && !CollectionUtils.isEmpty(params.getParameters())) {
            groupByPath = asPath.where(composeWhere(bucketName, params));
        }
        groupByPath.orderBy(fromPageable(pageable))
                .limit(pageable.getPageSize())
                .offset((int) pageable.getOffset());

        return groupByPath;
    }

    private Statement createQueryStatement(Parameters params) {
        Expression bucketName = i(this.couchbaseClientBucket.name());
        return selectWithMeta(bucketName)
                .from(bucketName)
                .where(composeWhere(bucketName, params));
    }

    private FromPath count(Expression bucketName) {
        return select("count(*) as count");
    }

    private FromPath sum(Expression bucketName, String field) {
        return select("sum(" + field + ") as sum, meta(" + bucketName + AS_ID);
    }

    private FromPath selectWithMeta(Expression bucketName) {
        return select(bucketName + " as data, meta(" + bucketName + AS_ID);
    }

    private String meta(Expression bucketName) {
        return "meta(" + bucketName + ").id";
    }

    private Expression composeWhere(Expression bucketName, Parameters params) {
        List<Expression> expressions = params.toExpressions();

        Expression previousExpression;
        Expression currentExpression = null;
        int i = 0;
        List<Parameter> parameters = params.getParameters();
        for (Expression expression : expressions) {
            Parameter<T> parameter = parameters.get(i);

            previousExpression = currentExpression;
            if (i != 0) {
                if (ClauseType.OR.equals(parameter.getClauseTypeWithPreviousExpression())) {
                    currentExpression = previousExpression.or(expression);
                } else {
                    currentExpression = previousExpression.and(expression);
                }
            } else {
                currentExpression = expression;
            }
            i++;
        }

//        Expression lastExpression = (x("meta(" + bucketName + ").id NOT LIKE \"_sync:%\""));
//
//        currentExpression.and(lastExpression);

        return currentExpression;

    }

    private String lowerCase(String input) {
        return "LOWER(" + input + ")";
    }

    private Sort[] fromPageable(Pageable pageable) {
        List<Sort> orderBy = new ArrayList<>();
        pageable.getSort().forEach(pageableOrder -> {
            org.springframework.data.domain.Sort.Direction direction = pageableOrder.getDirection();
            if (direction == org.springframework.data.domain.Sort.Direction.ASC) {
                if (pageableOrder.getProperty().endsWith(IGNORE_CASE_ORDER)) {
                    String property = pageableOrder.getProperty().substring(0, pageableOrder.getProperty().length() - IGNORE_CASE_ORDER.length());
                    orderBy.add(Sort.asc(lowerCase(property)));
                } else {
                    orderBy.add(Sort.asc(pageableOrder.getProperty()));
                }
            } else if (direction == org.springframework.data.domain.Sort.Direction.DESC) {
                if (pageableOrder.getProperty().endsWith(IGNORE_CASE_ORDER)) {
                    String property = pageableOrder.getProperty().substring(0, pageableOrder.getProperty().length() - IGNORE_CASE_ORDER.length());
                    orderBy.add(Sort.desc(lowerCase(property)));
                } else {
                    orderBy.add(Sort.desc(pageableOrder.getProperty()));
                }
            }
        });
        return orderBy.toArray(new Sort[0]);
    }

    private Optional<T> asOptional(List<T> documents, Parameters params) {
        if (documents.isEmpty()) {
            return Optional.empty();
        }
        if (documents.size() == 1) {
            return Optional.of(documents.get(0));
        }
        throw new NonUniqueResultException(params);
    }
}

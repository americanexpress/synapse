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
package com.americanexpress.synapse.data.couchbase.builder;


import com.americanexpress.synapse.data.couchbase.parameter.ExpressionMethods;
import com.americanexpress.synapse.data.couchbase.parameter.Parameter;
import com.americanexpress.synapse.data.couchbase.parameter.Parameters;
import com.americanexpress.synapse.data.couchbase.parameter.ParameterType;

import java.util.List;
import java.util.StringJoiner;


/**
 * The type On path.
 */
public class OnPath extends BasePath {

    /**
     * Instantiates a new On path.
     *
     * @param key        the key
     * @param parameters the parameters
     */
    public OnPath(String key, Parameters parameters) {
        super(key, parameters);
    }

    /**
     * Checks if the given key's value is null
     */
    public void isNull() {
        parameters.add(new Parameter<String>(key, value, ExpressionMethods::isNull, ParameterType.IS_NULL));
    }

    /**
     * Checks if the given key's value is not null
     */
    public void isNotNull() {
        parameters.add(new Parameter<String>(key, value, ExpressionMethods::isNotNull, ParameterType.IS_NOT_NULL));
    }

    /**
     * Checks if the given key's value is missing or null
     */
    public void isMissing() {
        parameters.add(new Parameter<String>(key, value, ExpressionMethods::isMissing, ParameterType.IS_MISSING));
    }
    /**
     * Checks if the given key's value is not missing
     */
    public void isNotMissing() {
        parameters.add(new Parameter<String>(key, value, ExpressionMethods::isNotMissing, ParameterType.IS_NOT_MISSING));
    }
    
    /**
     * Checks if the given key's value is missing or null
     */
    public void isNullOrMissing() {
        parameters.add(new Parameter<String>(key, value, ExpressionMethods::isNullOrMissing, ParameterType.IS_MISSING_OR_NULL));
    }

    /**
     * Checks whether the document's value on the path is equal to the value
     *
     * @param value the value
     * @return the expression path
     */
    public ExpressionPath isNot(String value) {
        this.expressionMethod = ExpressionMethods::notEquals;
        this.value = value;
        this.type = ParameterType.NOT_EQUALS;
        return new ExpressionPath(this);
    }

    /**
     * Is expression path.
     *
     * @param value the value
     * @return the expression path
     */
    public ExpressionPath is(String value) {
        this.expressionMethod = ExpressionMethods::equals;
        this.value = value;
        this.type = ParameterType.EQUALS;
        return new ExpressionPath(this);
    }

    public ExpressionPath and(String value) {
        this.expressionMethod = ExpressionMethods::equals;
        this.value = value;
        this.type = ParameterType.EQUALS;
        return new ExpressionPath(this);
    }

    /**
     * Contains expression path.
     *
     * @param value the value
     * @return the expression path
     */
    public ExpressionPath contains(String value) {
        this.expressionMethod = ExpressionMethods::contains;
        this.value = value;
        this.type = ParameterType.CONTAINS;
        return new ExpressionPath(this);
    }

    /**
     * In expression path.
     *
     * @param value the value
     * @return the expression path
     */
    public ExpressionPath in(List<?> value) {
        this.expressionMethod = ExpressionMethods::in;
        this.value = value;
        this.type = ParameterType.IN;
        return new ExpressionPath(this);
    }

    /**
     * Checks whether the document's value on the path is greater than or equal to the value
     *
     * @param value the value
     * @return the expression path
     */
    public ExpressionPath from(Long value) {
        this.expressionMethod = ExpressionMethods::greaterThanOrEquals;
        this.value = value;
        this.type = ParameterType.GREATER_THAN_OR_EQUALS;
        return new ExpressionPath(this);
    }

    /**
     * Checks whether the document's value on the path is less than or equal to the value
     *
     * @param value the value
     * @return the expression path
     */
    public ExpressionPath to(Long value) {
        this.expressionMethod = ExpressionMethods::lessThanOrEquals;
        this.value = value;
        this.type = ParameterType.LESS_THAN_OR_EQUALS;
        return new ExpressionPath(this);
    }
    
    /**
     * Checks whether the document's value on the path is greater than or equal to the value
     *
     * @param value the value
     * @return the expression path
     */
    public ExpressionPath from(String lowerValue) {
        this.expressionMethod = ExpressionMethods::betweenStart;
        this.value = lowerValue;
        this.type = ParameterType.BETWEEN_START;
        return new ExpressionPath(this);
    }

    /**
     * Checks whether the document's value on the path is less than or equal to the value
     *
     * @param value the value
     * @return the expression path
     */
    public ExpressionPath to(String upperValue) {
        this.expressionMethod = ExpressionMethods::betweenEnd;
        this.value = upperValue;
        this.type = ParameterType.BETWEEN_END;
        return new ExpressionPath(this);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OnPath.class.getSimpleName() + "[", "]")
                .add("key='" + key + "'")
                .add("value='" + value + "'")
                .add("type=" + type)
                .add("expressionMethod=" + expressionMethod)
                .add("condition=" + condition)
                .add("parameters=" + parameters)
                .toString();
    }
}

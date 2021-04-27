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
package com.americanexpress.synapse.data.couchbase.parameter;

import com.couchbase.client.java.query.dsl.Expression;

import java.util.StringJoiner;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * The type Parameter.
 *
 * @param <T> the type parameter
 */
public class Parameter<T> {

    private final String key;
    private final Object value;
    private final Predicate<Object> condition;
    private final BiFunction<String, String, Expression> expressionMethod;
    private final Function<Object, T> parser;
    private final ParameterType type;
    private final ClauseType clauseTypeWithPreviousExpression;


    /**
     * Instantiates a new Parameter.
     *
     * @param key              the key
     * @param value            the value
     * @param condition        the condition
     * @param expressionMethod the expression method
     * @param parser           the parser
     * @param type             the type
     */
    public Parameter(String key, Object value, Predicate<Object> condition, BiFunction<String, String, Expression> expressionMethod, Function<Object, T> parser, ParameterType type) {
        this.key = key;
        this.value = value;
        this.condition = condition;
        this.expressionMethod = expressionMethod;
        this.parser = parser;
        this.type = type;
        this.clauseTypeWithPreviousExpression = null;
    }

    /**
     * Instantiates a new Parameter.
     *
     * @param key              the key
     * @param value            the value
     * @param expressionMethod the expression method
     * @param type             the type
     */
    public Parameter(String key, Object value, BiFunction<String, String, Expression> expressionMethod, ParameterType type) {
        this.key = key;
        this.value = value;
        this.type = type;
        this.condition = null;
        this.expressionMethod = expressionMethod;
        this.parser = null;
        this.clauseTypeWithPreviousExpression = null;
    }

    /**
     * Instantiates a new Parameter with a null parser
     *
     * @param key              the key
     * @param value            the value
     * @param condition        the condition
     * @param expressionMethod the expression method
     * @param type             the type
     */
    public Parameter(String key, Object value, Predicate<Object> condition, BiFunction<String, String, Expression> expressionMethod, ParameterType type, ClauseType clauseTypeWithPreviousExpression) {
        this.key = key;
        this.value = value;
        this.condition = condition;
        this.expressionMethod = expressionMethod;
        this.parser = null;
        this.type = type;
        this.clauseTypeWithPreviousExpression = clauseTypeWithPreviousExpression;
    }

    /**
     * Instantiates a new Parameter with a null parser
     *
     * @param key              the key
     * @param value            the value
     * @param condition        the condition
     * @param expressionMethod the expression method
     * @param type             the type
     */
    public Parameter(String key, Object value, Predicate<Object> condition, BiFunction<String, String, Expression> expressionMethod, ParameterType type) {
        this.key = key;
        this.value = value;
        this.condition = condition;
        this.expressionMethod = expressionMethod;
        this.parser = null;
        this.type = type;
        this.clauseTypeWithPreviousExpression = null;
    }

    /**
     * Gets key.
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public T getValue() {
//        return parse(value);
        return (T) value;
    }

    /**
     * If condition boolean.
     *
     * @return the boolean
     */
    public boolean ifCondition() {
        if (condition == null) {
            return true;
        } else {
            return condition.test(value);
        }
    }

    /**
     * To expression expression.
     *
     * @return the expression
     */
    public Expression toExpression() {
        String queryKey = getQueryKey();//Sample 'attribute_name_EQUALS'
        //key sample 'attribute_name'
        //Here is where the the in or is expressionMethod is applied to connect the key to the queryKey resulting in attribute_name = $attribute_name_EQUALS for example
        Expression expression = expressionMethod.apply(key, queryKey);//

        return expression;
    }

    /**
     * To expression expression.
     *
     * @return the expression
     */
    public Expression toExpression(String queryKey) {
        //key sample 'attribute_name'
        //Here is where the the in or is expressionMethod is applied to connect the key to the queryKey resulting in attribute_name = $attribute_name_EQUALS for example
        Expression expression = expressionMethod.apply(key, queryKey);//

        return expression;
    }

    private T parse(String value) {
        if (parser != null) {
            return parser.apply(value);
        } else {
            return (T) value;
        }
    }

    public ClauseType getClauseTypeWithPreviousExpression() {
        return clauseTypeWithPreviousExpression;
    }

    /**
     * Gets query key.
     *
     * @return the query key
     */
    public String getQueryKey() {
        if (key != null) {
            return key.replaceAll("[\\\\.]", "_") + "_" + type.name();
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Parameter.class.getSimpleName() + "[", "]")
                .add("key='" + key + "'")
                .add("value='" + value + "'")
                .add("condition=" + condition)
                .add("expressionMethod=" + expressionMethod)
                .add("parser=" + parser)
                .add("type=" + type)
                .toString();
    }
}

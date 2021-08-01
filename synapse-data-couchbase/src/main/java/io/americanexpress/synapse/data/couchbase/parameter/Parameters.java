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
package io.americanexpress.synapse.data.couchbase.parameter;

import io.americanexpress.synapse.data.couchbase.builder.OnPath;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.query.dsl.Expression;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * The type Parameters.
 */
public class Parameters {

    private List<Parameter> parameters = new ArrayList<>();

    public List<Parameter> getParameters() {
        return parameters;
    }

    /**
     * On on path.
     *
     * @param key the key
     * @return the on path
     */
    public OnPath on(String key) {
        return new OnPath(key, this);
    }

    /**
     * To expressions list.
     *
     * @return the list
     */
    public List<Expression> toExpressions() {
        List<Expression> expressions = new ArrayList<>();
        int i = 1;
        for (Parameter parameter : parameters) {
            if (parameter.ifCondition()) {
                Expression expression = parameter.toExpression("" + i);
                expressions.add(expression);
            }
            i++;
        }
        return expressions;

    }

    /**
     * To json object json object.
     *
     * @return the json object
     */
    public JsonArray toJsonArray() {
        JsonArray jsonArray = JsonArray.create();
        for (Parameter parameter : parameters) {
            if (parameter.ifCondition()) {
                Object value = parameter.getValue();
                if (value instanceof List) {
                    JsonArray sub = JsonArray.from((List<?>) value);
                    jsonArray.add(sub);
                } else {
                    jsonArray.add(parameter.getValue());
                }
            }
        }
        return jsonArray;
    }

    /**
     * Add.
     *
     * @param <T>       the type parameter
     * @param parameter the parameter
     */
    public <T> void add(Parameter<T> parameter) {
        parameters.add(parameter);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Parameters.class.getSimpleName() + "[", "]")
                .add("parameters=" + parameters)
                .toString();
    }
}

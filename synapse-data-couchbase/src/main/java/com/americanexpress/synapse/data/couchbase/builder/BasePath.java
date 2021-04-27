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

import com.americanexpress.synapse.data.couchbase.parameter.ParameterType;
import com.americanexpress.synapse.data.couchbase.parameter.Parameters;
import com.couchbase.client.java.query.dsl.Expression;

import java.util.function.BiFunction;
import java.util.function.Predicate;

/**
 * The type Base path.
 *
 * @author Alexei Morgado
 */
class BasePath {
    /**
     * The Key.
     */
    String key;
    /**
     * The Value.
     */
    Object value;
    /**
     * The Type.
     */
    ParameterType type;
    /**
     * The Expression method.
     */
    BiFunction<String, String, Expression> expressionMethod;
    /**
     * The Condition.
     */
    Predicate<Object> condition;
    /**
     * The Parameters.
     */
    Parameters parameters;

    /**
     * Instantiates a new Base path.
     *
     * @param key        the key
     * @param parameters the parameters
     */
    BasePath(String key, Parameters parameters) {
        this.key = key;
        this.parameters = parameters;
    }

    /**
     * Instantiates a new Base path.
     *
     * @param other the other
     */
    BasePath(BasePath other) {
        this.key = other.key;
        this.value = other.value;
        this.expressionMethod = other.expressionMethod;
        this.condition = other.condition;
        this.parameters = other.parameters;
        this.type = other.type;
    }
}
